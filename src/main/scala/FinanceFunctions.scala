import annotation.tailrec

object FinanceFunctions {

  def mean(data: Seq[Double]): Double =
    if (data.isEmpty) 0
    else data.sum / data.length

  //todo: better name as this could cover any time span
  def dailyReturn(prices: Seq[Double]) = 0.0 +: ((prices.tail zip prices) map {
    case (p1, p2) => (p1 / p2) - 1
  })

  def averageReturn(prices: Seq[Double]) = mean(dailyReturn(prices))

  def futureValue(principal: Double, interestRate: Double, time: Double) = principal * math.pow(1 + interestRate, time)

  def presentValue(futureVal: Double, interestRate: Double, time: Double) = futureVal / math.pow(1 + interestRate, time)

  def effectiveAnnualRate(interestRate: Double, compoundingPeriods: Int) = math.pow(1 + interestRate/compoundingPeriods, compoundingPeriods) - 1

  def square(x: Double) = x * x

  def variance(data: Seq[Double]): Double = {
    val correction = data.length/(data.length - 1.0)
    val avg = mean(data)
    mean(data.map(x => square(x - avg)))*correction
  }

  def stdDev(data: Seq[Double]): Double = {
    math.sqrt(variance(data))
  }

  def sharpeRatio(prices: Seq[Double]) = {
    val dailyRtns = dailyReturn(prices)
    math.sqrt(prices.length)*mean(dailyRtns)/stdDev(dailyRtns)
  }

  def covariance(x: Seq[Double], y: Seq[Double]): Double = {
    @tailrec
    def helper(xs: Seq[Double], ys: Seq[Double], n: Int, mx: Double, my: Double, covar: Double): Double = {
      if (xs.isEmpty || ys.isEmpty) covar / n.toDouble
      else helper(xs.tail, ys.tail, n+1, mx, my, covar + (x.head-mx)*(y.head-my))
    }
    helper(x, y, 0, mean(x), mean(y), 0)
  }

  def correlationCoef(x: Seq[Double], y: Seq[Double]): Double = {
    @tailrec
    def helper(xs: Seq[Double], ys: Seq[Double], n: Int, sx: Double, sy: Double, sxy: Double, sx2: Double, sy2: Double): Double =
      if (xs.isEmpty) (n*sxy - sx*sy) / math.sqrt((n*sx2-sx*sx)*(n*sy2-sy*sy))
      else {
        val x = xs.head
        val y = ys.head
        helper(xs.tail, ys.tail, n+1, sx+x, sy+y, sxy+x*y, sx2+x*x, sy2+y*y)
      }
    require(x.nonEmpty && y.nonEmpty && x.length <= y.length)
    helper(x, y, 0, 0, 0, 0, 0, 0)
  }

  def beta = 1

}
