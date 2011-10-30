val left_times = 5

var left_peaches = 0

var found = false
var i = 0

while(!found){
  left_peaches  = reduce_one_and_take_20_percents(i ,left_times)
  if(left_peaches > 0){
    found = true
    println("sum: "+ i)
    println("left: "+ left_peaches)
  }
  i = i + 1
}


def reduce_one_and_take_20_percents(peaches:Int, left_times:Int):Int = {
  
  if(left_times == 0){
    return peaches
  }
  
  if(peaches % 5 == 1) {
    return reduce_one_and_take_20_percents((peaches - 1) / 5 * 4, left_times - 1)
  }
  return 0 
}