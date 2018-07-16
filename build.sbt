name := "libritos"

version := "0.1"

scalaVersion := "2.12.6"
libraryDependencies ++= Seq ("org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
  "com.outworkers"  %% "phantom-dsl" % phantomVersion)