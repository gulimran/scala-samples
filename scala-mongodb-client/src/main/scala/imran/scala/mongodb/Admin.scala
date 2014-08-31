package imran.scala.mongodb

trait Admin extends ReadOnly {
  def drop: Unit = mongodbJavaCollection drop
  def dropIndexes = mongodbJavaCollection dropIndexes
}
