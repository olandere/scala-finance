import java.time.LocalDate

import cache.CacheManager
import java.net.URL

object YahooFinance {

  val url = "http://ichart.finance.yahoo.com/table.csv?s=%S&a=%d&b=%d&c=%d&d=%d&e=%d&f=%d&g=d&ignore=.csv"

  def lastYear(ticker: String) = {
    val now = LocalDate.now()
    CacheManager.checkCache(ticker, new URL(url.format(ticker, now.getMonthValue - 1, now.getDayOfMonth, now.getYear-1,
                                                       now.getMonthValue - 1, now.getDayOfMonth, now.getYear)))
  }

  def lastSixMonths(ticker: String) = {
	val now = LocalDate.now()
    val past = LocalDate.now().minusMonths(6)
    CacheManager.checkCache(ticker, new URL(url.format(ticker, past.getMonthValue - 1, past.getDayOfMonth, past.getYear,
                                                       now.getMonthValue - 1, now.getDayOfMonth, now.getYear)))
  }

  def ytd(ticker: String) = {
    val now = LocalDate.now()
    CacheManager.checkCache(ticker, new URL(url.format(ticker, 0, 1, now.getYear,
                                                       now.getMonthValue - 1, now.getDayOfMonth, now.getYear)))
  }
}


