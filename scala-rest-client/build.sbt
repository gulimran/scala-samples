import _root_.sbt.Keys._

name := "scala-rest-client"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies += "commons-logging" % "commons-logging" % "1.1.1"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked")
    