package imran.scala.mongodb

import com.mongodb.BasicDBObject

object Main {

  def main(args: Array[String]) {

    def client = new DBClient
    def db = client db "testdb"

    for (name <- db.collectionNames) println("Default collection name: "+name)

    val col = db.readOnlyCollection("test")
    println("Read only collection name: "+col.name)

    val deletableCol = db.adminitrableCollection("test")
    deletableCol.drop

    val doc = new BasicDBObject
    doc.put("name", "MongoDB")
    doc.put("type", "database")
    doc.put("count", 1)

    val info = new BasicDBObject
    info.put("driver", "java")
    info.put("version", 2.12)
    doc.put("info", info)

    val updatableCol = db.updatableCollection("test")
    updatableCol save doc

    println("Updatable collection findOne: "+updatableCol.findOne)

    updatableCol remove doc

    println("Updatable collection after remove findOne: "+updatableCol.findOne)

    for (i <- 1 to 100)
      updatableCol save new BasicDBObject("i", i)

    val query = new BasicDBObject("i", 71)

    val cursor = col find query

    while (cursor hasNext)
      println("Updatable collection cursor: "+cursor.next)
  }
}
