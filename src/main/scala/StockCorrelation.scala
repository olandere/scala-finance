import io.Source
import java.net.URL
import FinanceFunctions._

object StockCorrelation {

  def extractPrices(data: Seq[HistoricalPriceRecord]): Seq[Double] = data.map(_.adjClose)

  def fetchHistoricalData(url: URL): Seq[HistoricalPriceRecord] =
    Source.fromURL(url, "UTF-8").getLines().drop(1).map(HistoricalPriceRecord.apply).toSeq.sorted

  def allPairs(l: Seq[String]): Seq[(String, String)] = {
    if (l.isEmpty) {
      Nil
    } else {
      (for {x <- l.tail} yield {
        (l.head, x)
      }) ++ allPairs(l.tail)
    }
  }

  def returns(sym : String) =
    dailyReturn(extractPrices(fetchHistoricalData(YahooFinance.lastYear(sym))))

  def apply(args: String*) = {
   //val tickers = List("AHSAX", "RERFX", "RGACX", "FSCTX", "WSTYX", "JIGFX", "EKWAX", "FLSTX", "SPY")
   val tickers = List("MO", "BMY", "ABT", "ABX", "SPY", "INTC", "MSFT", "LNG", "GLW")
   // val tickers = List("AAPL", "ABT", "ACN", "AEP", "ALL", "AMGN", "AMZN", "APC", "AXP", "BA", "BAC", "BAX", "BHI", "BK", "BMY", "BRK.B", "CAT", "C", "CL", "CMCSA", "COF", "COP", "COST", "CPB", "CSCO", "CVS", "CVX", "DD", "DELL", "DIS", "DOW", "DVN", "EBAY", "EMC", "EXC", "F", "FCX", "FDX", "GD", "GE", "GILD", "GOOG", "GS", "HAL", "HD", "HNZ", "HON", "HPQ", "IBM", "INTC", "JNJ", "JPM", "KFT", "KO", "LLY", "LMT", "LOW", "MA", "MCD", "MDT", "MET", "MMM", "MO", "MON", "MRK", "MS", "MSFT", "NKE", "NOV", "NSC", "NWSA", "NYX", "ORCL", "OXY", "PEP", "PFE", "PG", "PM", "QCOM", "RF", "RTN", "SBUX", "SLB", "HSH", "SO", "SPG", "T", "TGT", "TWX", "TXN", "UNH", "UPS", "USB", "UTX", "VZ", "WAG", "WFC", "WMB", "WMT", "XOM")

    val list = for {
      (sym1, sym2) <- allPairs(args)
    } yield (sym1, sym2, correlationCoef(returns(sym1), returns(sym2)))
    list.sortBy(p => p._3).foreach(p=> println("%s %s %.3f".format(p._1, p._2, p._3)))
    list
//    val sym1 = dailyReturn(extractPrices(fetchHistoricalData(YahooFinance.lastYear("FLSTX"))))
//    val sym2 = dailyReturn(extractPrices(fetchHistoricalData(YahooFinance.lastYear("EKWAX"))))
//    println(correlationCoef(sym1, sym2))
  }
}
