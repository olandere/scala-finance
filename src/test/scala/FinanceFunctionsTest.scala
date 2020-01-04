
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
    val prices = ResourceLoader.load("/spy.csv").tail.map(HistoricalPriceRecord.apply).sorted.map(_.adjClose)
    FinanceFunctions.stdDev(FinanceFunctions.dailyReturn(prices))
    println(FinanceFunctions.averageReturn(prices))
    val ratio = FinanceFunctions.sharpeRatio(prices)
    println(ratio)
    (ratio*1000).toInt/1000.0 should equal (0.152)
  }

  "correlationCoef" should "return -0.989" in {
    math.round(FinanceFunctions.correlationCoef(List(8.0, 4.0, 5.0, -1.0, 1.0, 2.0, 6.0), List(-2.0, 2.0, 1.0, 6.0, 4.0, 3.0, -1.0))*1000)/1000.0 should equal (-0.989)
  }

  "covariance" should "return -17.082" in {
    math.round(FinanceFunctions.covariance(List(8.0, 4.0, 5.0, -1.0, 1.0, 2.0, 6.0), List(-2.0, 2.0, 1.0, 6.0, 4.0, 3.0, -1.0))*1000)/1000.0 should equal (-17.082)
  }

}
