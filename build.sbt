import Dependencies.Versions

lazy val commonSettings = Seq(
  organization := "tech.iooo.coco",
  version := Versions.project,
  scalaVersion := Versions.scala,
  exportJars := true,
  trackInternalDependencies := TrackLevel.TrackIfMissing,
  Dependencies.core
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(lang)


lazy val lang = (project in file("booster-lang"))
  .settings(
    commonSettings,
    idePackagePrefix := Some("tech.iooo.coco"),
    name := "booster-lang"
  )

javacOptions ++= Seq("-encoding", "UTF-8")
javaOptions in run += "-Xmx1G"