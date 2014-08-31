package imran.scala.mongodb

import com.mongodb.MongoClient

class DBClient(val host:String, val port:Int) {

  private val mongo = new MongoClient(host, port)
  def this() = this("localhost", 27017)

  def version = mongo.getVersion
  def dropDatabase(name:String) = mongo.dropDatabase(name)
  def db(name:String) = DB(mongo.getDB(name))
}
