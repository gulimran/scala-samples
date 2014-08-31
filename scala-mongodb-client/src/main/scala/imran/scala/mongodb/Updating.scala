package imran.scala.mongodb

import com.mongodb.DBObject

trait Updating extends ReadOnly {

  def save(doc: DBObject): Unit = mongodbJavaCollection save doc
  def remove(doc: DBObject): Unit = mongodbJavaCollection remove doc
}
