import zipx.*

/** Typed catalog. sbt-zipx is not a row: generate emits it from the loaded plugin.
  */
object MyVersions extends ZipxVersions:
  val sbt: SbtVersion     = SbtVersion("2.1.0-RC1")
  val scala: ScalaVersion = ScalaVersion("3.9.0")

  val zio           = Lib("dev.zio", "zio", "2.1.27")
  val zioTest       = zio.mod("zio-test")
  val zioTestSbt    = zio.mod("zio-test-sbt")
  val scalaJavaTime = Lib("io.github.cquiroz", "scala-java-time", "2.7.0")

  val scalajs  = Plugin("org.scala-js", "sbt-scalajs", "1.22.0")
  val scalafmt = Plugin("org.scalameta", "sbt-scalafmt", "2.6.2")

  def webTest = library(scalaJavaTime.test, zioTest.test, zioTestSbt.test)
end MyVersions
