import java.time.LocalDate

case class HistoricalPriceRecord(date: LocalDate,
                                 open: Double,
                                 high: Double,
                                 low: Double,
                                 close: Double,
                                 volume: Long,
                                 adjClose: Double) extends Ordered[HistoricalPriceRecord] {

  def compare(that: HistoricalPriceRecord) = {
    this.date compareTo that.date
  }
}

object HistoricalPriceRecord {

  def apply(line: String): HistoricalPriceRecord = {

 //   implicit def stringToDouble(str: String): Double = str.toDouble
//    implicit def stringToInt(str: String): Int = str.toInt

    val fields = line.split(",")
    apply(LocalDate.parse(fields(0)), fields(1).toDouble, fields(2).toDouble, fields(3).toDouble,
          fields(4).toDouble, fields(5).toLong, fields(6).toDouble)
  }
}
