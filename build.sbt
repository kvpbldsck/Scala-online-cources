ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "course-hw",
    idePackagePrefix := Some("org.kvpbldsck")
  )
