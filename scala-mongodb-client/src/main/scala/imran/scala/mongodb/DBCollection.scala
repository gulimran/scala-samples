package imran.scala.mongodb

import com.mongodb.{DBCollection => MongoDBCollection}

class DBCollection(override val mongodbJavaCollection: MongoDBCollection)
  extends ReadOnly {
}
