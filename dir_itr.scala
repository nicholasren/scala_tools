/**
 * iterate a directory and apply a function to its all files under it
 */

import java.io.File

def forall( root : File, func : File => Unit):Unit = {
  val children = root.listFiles
  children.filter{ f => f.isFile }.foreach {
     f => func(f) 
  }

  children.filter{ f => f.isDirectory}.foreach{
    f=> forall(f, func)
  }
}


val root = new File(".")

forall(root, (f:File) => println(f))
	
