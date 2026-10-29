import histos.*

object build extends HistosBuild:
  def graph =
    val scala3     = scala"3.9.0"
    val javaTime   = dep"io.github.cquiroz::scala-java-time:2.7.0".on(Js)
    val zioTest    = dep"dev.zio::zio-test:2.1.27"
    val zioTestSbt = dep"dev.zio::zio-test-sbt:2.1.27"
    val core       = module("core", Jvm, Js)(scala3)
    val service    = module("service", Jvm)(scala3).dependsOn(core)
    val app        = module("app", Jvm)(scala3).dependsOn(service)
    val web        = module("web", Js)(scala3)
      .dependsOn(core)
      .main(MainClass("bench.Web"))
      .testDependencies(javaTime, zioTest, zioTestSbt)
    val idle = module("idle", Jvm)(scala3)
    Projects.runnerTools(RunnerTools(Some(NodeFloor(25)), None, None))(
      core,
      service,
      app,
      web,
      idle,
    )
