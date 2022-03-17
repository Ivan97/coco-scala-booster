import Dependencies.Compiles.commons
import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {

  object Versions {
    //project
    val scala = "2.13.8"
    val project = "0.1.0-SNAPSHOT"
    // deps
    val guava = "31.1-jre"
    val hutool = "5.7.22"
    val commons_lang3 = "3.12.0"
    val commons_collections4 = "4.4"
    val gson = "2.9.0"
    val logging = "3.9.4"
    val logback = "1.2.11"
    val lombok = "1.18.22"
  }

  object Compiles {
    lazy val commons: Seq[ModuleID] = Seq(
      "com.google.guava" % "guava" % Versions.guava,
      "cn.hutool" % "hutool-all" % Versions.hutool,
      "org.apache.commons" % "commons-lang3" % Versions.commons_lang3,
      "org.apache.commons" % "commons-collections4" % Versions.commons_collections4,
      "com.google.code.gson" % "gson" % Versions.gson,
      "com.typesafe.scala-logging" %% "scala-logging" % Versions.logging,
      "ch.qos.logback" % "logback-classic" % Versions.logback,
      "org.projectlombok" % "lombok" % Versions.lombok
    )
  }

  val core = libraryDependencies ++= commons
}