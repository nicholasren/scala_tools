/**
 * iterate a directory and print all file names
 */

import java.io.File

val dir  = new File(".")
val files_name = dir.listFiles().map{f => f.getName}

files_name foreach println 
	