import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport.*

MyVersions.settings

organization         := "rocks.earlyeffect"
organizationName     := "Early Effect"
organizationHomepage := Some(uri("https://www.earlyeffect.rocks"))
version              := "0.0.1-SNAPSHOT"

publish / skip  := true
publishArtifact := false

scalacOptions ++= Seq("-deprecation", "-feature", "-Wunused:all")

zipxJavaVersion := JdkVersion("25")
zipxCapabilities += Capability.test.withNodeVersion(NodeVersion("25"))

val scala3 = (MyVersions.scala: String)

lazy val core = (projectMatrix in file("core"))
  .settings(name := "core")
  .jvmPlatform(scalaVersions = Seq(scala3))
  .jsPlatform(scalaVersions = Seq(scala3))

lazy val service = project
  .in(file("service"))
  .dependsOn(core.jvm(scala3))
  .settings(name := "service")

lazy val app = project
  .in(file("app"))
  .dependsOn(service)
  .settings(name := "app")

lazy val web = project
  .in(file("web"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(core.js(scala3))
  .settings(
    name := "web",
    scalaJSUseMainModuleInitializer := true,
    MyVersions.webTest,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
  )

lazy val idle = project
  .in(file("idle"))
  .settings(name := "idle")

lazy val root = project
  .in(file("."))
  .aggregate((core.projectRefs ++ Seq[ProjectReference](service, app, web, idle))*)
  .settings(
    name := "histos-bench",
    // histos.scala is the other build file. It is not an sbt source.
    sourcesInBase := false,
  )
