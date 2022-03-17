import Dependencies.Versions

lazy val commonSettings = Seq(
  organization := "tech.iooo.coco",
  version := Versions.project,
  scalaVersion := Versions.scala,
  exportJars := true,
  trackInternalDependencies := TrackLevel.TrackIfMissing,
  Dependencies.core
)

lazy val root = Project(id = "coco-scala-booster", base = file("."))
  .settings(commonSettings)
  .aggregate(lang)

lazy val commons = (project in file("booster-commons"))
  .settings(
    commonSettings,
    idePackagePrefix := Some("tech.iooo.coco"),
    name := "booster-commons"
  )

lazy val lang = (project in file("booster-lang"))
  .settings(
    commonSettings,
    idePackagePrefix := Some("tech.iooo.coco"),
    name := "booster-lang"
  ).dependsOn(commons)

lazy val akka = (project in file("booster-akka"))
  .settings(
    commonSettings,
    Dependencies.akka,
    idePackagePrefix := Some("tech.iooo.coco"),
    name := "booster-akka"
  ).dependsOn(commons)

javacOptions ++= Seq("-encoding", "UTF-8")
javaOptions in run += "-Xmx1G"