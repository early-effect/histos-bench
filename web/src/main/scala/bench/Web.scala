package bench

object Web:
  def main(args: Array[String]): Unit =
    println(args.length + Core.value)
