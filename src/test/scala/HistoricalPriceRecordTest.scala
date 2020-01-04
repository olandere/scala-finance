import org.scalatest._

class HistoricalPriceRecordTest extends FlatSpec with Matchers {

  "apply" should "convert a string of data to a HistoricalPriceRecord" in {
    val hpr = HistoricalPriceRecord("2011-12-30,126.02,126.33,125.50,125.51,95599000,123.65")
    hpr.open should equal (126.02)
    hpr.high should equal (126.33)
    hpr.low should equal (125.50)
    hpr.close should equal (125.51)
    hpr.volume should equal (95599000)
    hpr.adjClose should equal (123.65)
  }

  "price data" should "be ordered by date" in {
    val hpr1 = HistoricalPriceRecord("2011-12-30,126.02,126.33,125.50,125.51,95599000,123.65")
    val hpr2 = HistoricalPriceRecord("2011-12-21,123.93,124.36,122.75,124.17,194230900,122.34")

    hpr2 < hpr1 should equal (true)
    hpr2 > hpr1 should equal (false)
    hpr1 > hpr2 should equal (true)
    hpr1 < hpr2 should equal (false)

    val list = List(hpr1, hpr2).sorted
    list.head should equal (hpr2)
  }

}
