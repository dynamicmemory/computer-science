lazy val root = (project in file(".")).
  settings(
    name := "future promise actor",
    version := "2022.0",
    scalaVersion := "3.1.0"
  )

libraryDependencies ++= Seq(
//  "com.typesafe.akka" %% "akka-actor" % "2.5.3",
  ("com.typesafe.play" %% "play-ahc-ws-standalone" % "2.2.0-M1") cross CrossVersion.for3Use2_13,
  ("com.typesafe.play" %% "play-ws-standalone-json" % "2.2.0-M1") cross CrossVersion.for3Use2_13,
  "org.scalactic" %% "scalactic" % "3.2.11",
  "org.scalatest" %% "scalatest" % "3.2.11" % "test"
)
