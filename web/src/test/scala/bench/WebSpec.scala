package bench

import zio.test.*

object WebSpec extends ZIOSpecDefault:
  def spec = suite("web")(
    test("reads core") {
      assertTrue(Core.value == 1)
    }
  )
