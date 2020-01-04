import io.Source
import java.net.URL
import FinanceFunctions._

object StockAnalyzer {

  def extractPrices(data: Seq[HistoricalPriceRecord]): Seq[Double] = data.map(_.adjClose)

  def fetchHistoricalData(url: URL): Seq[HistoricalPriceRecord] =
    Source.fromURL(url, "UTF-8").getLines().drop(1).map(HistoricalPriceRecord.apply).toSeq.sorted

//  def buildURL(ticker: String) =
//    new URL("http://ichart.finance.yahoo.com/table.csv?s="+ticker+"&a=00&b=1&c=2012&d=11&e=31&f=2012&g=d&ignore=.csv")
//
//  def buildURL(ticker: String, year: Int) =
//    new URL("http://ichart.finance.yahoo.com/table.csv?s="+ticker+"&a=00&b=1&c="+year+"&d=11&e=31&f="+year+1+"&g=d&ignore=.csv")
//
//  def showPrices(ticker: String) {
//    println(extractPrices(fetchHistoricalData(buildURL(ticker))) mkString "\n")
//  }

  def readStockList: List[String] = {
    Source.fromFile("/Users/eolander/scala-finance/src/main/resources/stockList.txt").getLines().toList
  }

  //def apply(sym: String*): List[(String, Double)] = apply(sym)

  def apply(args: String*): Seq[(String, Double)] = {
//    val tickers = List("GAS", "ABT","ARG", "LNT", "BGS", "BTI")
//    val tickers = List("MO", "BMY", "FSC", "PEP", "SIRI")
// val tickers = List("BGS", "COG", "ABT", "FSC", "PEP", "SIRI", "MO", "BMY")
//val tickers = List("PNY", "DGAS", "NWN", "RGCO", "SWX", "OKE", "CHK") //gas
//val tickers = List("FCX", "AUY", "SVLC") //metals
//val tickers = List("FCSCX", "FCTLX")
    //val tickers = List("TICC", "TGP", "TNH")
   // val tickers = List("DCIX", "NRCI", "TWO", "APSA")
  //  val tickers = List("AHSAX", "RERFX", "RGACX", "FSCTX", "WSTYX", "JIGFX", "EKWAX", "FLSTX") //41st
    //val tickers = List("PNW", "ALJ", "BGS", "ABT")
   // val tickers = List("INTC", "MSFT", "ADBE", "CA", "HPQ")
//val tickers = List("ABX", "WPRT", "CLNE")
//val tickers = List("GLW", "LNG", "SPY", "LZB")
//val tickers = List("CPB", "SO", "WAG", "BGS")
    val list = for {
      sym <- args

    } yield (sym, sharpeRatio(extractPrices(fetchHistoricalData(YahooFinance.lastSixMonths(sym)))))
   // println(sharpeRatio(extractPrices(fetchHistoricalData(buildURL("ABT")))))
   // println(list.map(_._2).mkString("\n"))
    list.sortBy(p => -p._2).foreach(p=>println("%s %.3f".format(p._1, p._2)))
//    showPrices("ABT")
    list
  }
}

//GAS, ABT,ARG, LNT, BGS, BTI
