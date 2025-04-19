lazy val root = (project in file(".")).
  settings(
    name := "VectorsAndExtensions",
    version := "2022.0",
    scalaVersion := "3.1.0"
  )

libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
testFrameworks += new TestFramework("munit.Framework")

libraryDependencies += ("org.typelevel"  %% "squants"  % "1.8.3")
