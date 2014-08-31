package imran.scala.mongodb

import com.mongodb.{DBCollection => MongoDBCollection, DBObject}

trait ReadOnly {

  val mongodbJavaCollection: MongoDBCollection

  def name = mongodbJavaCollection getName
  def fullName = mongodbJavaCollection getFullName
  def find(doc: DBObject) = mongodbJavaCollection find doc
  def findOne(doc: DBObject) = mongodbJavaCollection findOne doc
  def findOne = mongodbJavaCollection findOne
  def getCount = mongodbJavaCollection getCount

}
