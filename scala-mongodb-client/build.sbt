import _root_.sbt.Keys._

name := "scala-mongodb-client"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "2.12.3"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked")
