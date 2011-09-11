
/**
 * Created by IntelliJ IDEA.
 * User: reaapi
 * Date: 9/11/11
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */

object RegexpParser {
  def main(args: Array[String]) {
    val matched = RegexpParser.matches("g$", "abcdefg")
    print(matched)
  }

  def matches(regexp: String, text: String): Boolean = {

    if (regexp.charAt(0) == '^') {
      return match_here(regexp.substring(1), text)
    }

    //match regexp on text incrementally
    var idx = 0
    do {
      if (match_here(regexp, text.substring(idx))) {
        return true
      }
      idx = idx + 1

    } while (idx < text.length())

    return false
  }

  /**
   * fist char matched : first char of regexp is '.' or equals to first char of text
   */
  def fist_char_matched(regexp: String, text: String): Boolean = {
    (regexp.charAt(0) == '.' || regexp.charAt(0) == text.charAt(0))
  }

  def match_here(regexp: String, text: String): Boolean = {

    //if regexp is empty, matched
    if (regexp.length() == 0) {
      return true
    }

    //if regexp looks like 'c*regexp', match star
    if (regexp.length() > 1 && regexp.charAt(1) == '*') {
      return match_star(regexp.charAt(0), regexp.substring(2), text)
    }

    //if regexp equals '$', expect we're at the end of text
    if (regexp.equals("$")) {
      return text.length() == 0
    }

    //if first char matched
    //move pointer of regexp and text to next char, start a new match
    if (text.length() > 0 && fist_char_matched(regexp, text)) {
      return match_here(regexp.substring(1), text.substring(1))
    }
    return false;
  }

  /**
   * match a text looks like 'c*regexp'
   */
  def match_star(c: Char, regexp: String, text: String): Boolean = {
    var idx = 0;
    do {
      if (match_here(regexp, text.substring(idx))) {
        return true
      }
      idx = idx + 1
    } while (text.length() != idx && (text.charAt(idx).equals(c) || c.equals('.')))

    return false
  }
}
