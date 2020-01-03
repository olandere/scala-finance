lazy val root = (project in file(".")).
  settings(
    name := "scalaFinance",
    version := "1.0",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.0",
      "org.typelevel" %% "spire" % "0.17.0-M1"
    )
  )