package lectures.part1basics

object DefaultArgs extends App{

  def trFact(n: Int, acc: Int = 1): Int = {  // if you have a default value, then you can set it the function
    if (n <= 1) acc
    else trFact(n - 1, n * acc)
  }

  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int, height: Int): Unit = println("saving the image")

  // leading parameter should be put in the button
  // for example, savePicture(200, 200) will have an error or:
  savePicture(format="jpg", 200, 200)

}
