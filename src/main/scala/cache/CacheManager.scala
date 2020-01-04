package cache

import java.io.{FileWriter, File}
import java.net.URL
import io.Source

object CacheManager {

  val cacheDir = new File(sys.props.getOrElse("tickercachedir", "/tmp/cache"))

  def using[A <: {def close(): Unit}, B](param: A)(f: A => B): B =
    try { f(param) } finally { param.close() }

  def checkCache(ticker: String, url: URL): URL = {
    val cacheFile = new File(cacheDir, ticker+".csv")
    if (cacheFile.exists()) cacheFile.toURI.toURL
    else {
      using (new FileWriter(cacheFile)) {
        fileWriter => fileWriter.write(Source.fromURL(url, "UTF-8").getLines().mkString("\n"))
      }
      cacheFile.toURI.toURL
    }
  }

}
