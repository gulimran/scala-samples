package imran.scala.mongodb

import com.mongodb.{DB => MongoDB}
import scala.collection.JavaConversions.JSetWrapper

object DB {
  def apply(mongodb: MongoDB) = new DB(mongodb)
}

class DB private(val mongodb: MongoDB) {

  private def collection(name: String) = mongodb getCollection name

  def readOnlyCollection(name: String)
      = new DBCollection(collection(name))
  def adminitrableCollection(name: String)
      = new DBCollection(collection(name)) with Admin
  def updatableCollection(name: String)
      = new DBCollection(collection(name)) with Updating

  def collectionNames = {
    for (name <- new JSetWrapper(mongodb.getCollectionNames))
    yield name
  }
}
