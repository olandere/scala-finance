object FinanceFunctions {

  def mean(data: List[Double]): Double =
    if (data.isEmpty) 0
    else data.sum / data.length

  //todo: better name as this could cover any time span
  def dailyReturn(prices: List[Double]) = 0.0 :: ((prices.tail zip prices) map {
    case (p1, p2) => (p1 / p2) - 1
  })

  def averageReturn(prices: List[Double]) = mean(dailyReturn(prices))

  def square(x: Double) = x * x

  def stdDev(data: List[Double]): Double = {
    val avg = mean(data)
    math.sqrt(mean(data.map(x => square(x - avg))))
  }

  def sharpeRatio(prices: List[Double]) = {
    val dailyRtns = dailyReturn(prices)
    math.sqrt(prices.length)*mean(dailyRtns)/stdDev(dailyRtns)
  }

}
