# histos-bench

One graph, two builds. [histos.scala](histos.scala) and [build.sbt](build.sbt) (sbt 2, zipx) name the same modules. No Mill. No Native.

| Module | Platforms | Depends on |
| --- | --- | --- |
| `core` | JVM and Scala.js | |
| `service` | JVM | `core` |
| `app` | JVM | `service` |
| `web` | Scala.js | `core` |
| `idle` | JVM | |

`core` is both platforms because a Scala.js module cannot depend on a JVM-only upstream. `web` links `bench.Web`. Its test runs on Node. `idle` is outside that cone. On the sbt side the JS row of `core` is the project `coreJS`.

Scala is 3.9.0. Both tools link with Scala.js 1.22, and that release has no Scala 3.10 compiler.

`./histos` runs the snapshot pinned in [.histos/launcher.lock](.histos/launcher.lock). It does not install a JDK or Node. The JDK must be 21 or newer, and the Node test needs Node 25 or newer on `PATH`.

The timed comparison against sbt and zipx is not in this tree yet.
