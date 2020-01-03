
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FinanceFunctionsTest extends AnyFlatSpec with Matchers {

  "mean" should "return 0 for empty list" in {
    FinanceFunctions.mean(Nil) should equal (0)
  }

  "mean" should "return 2 for List(1,2,3)" in {
    FinanceFunctions.mean(List(1,2,3)) should equal (2)
  }

  "stdDev" should "do something" in {
    val prices = ResourceLoader.load("/spy.txt").map(s => s.toDouble)
    FinanceFunctions.stdDev(FinanceFunctions.dailyReturn(prices))
    println(FinanceFunctions.averageReturn(prices))
    (FinanceFunctions.sharpeRatio(prices)*1000).toInt/1000.0 should equal (0.152)
  }

}
