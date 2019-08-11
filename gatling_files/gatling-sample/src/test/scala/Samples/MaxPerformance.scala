package Sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class MaxPerformance extends Simulation {

  val httpProtocol = http
    .baseUrl("http://nas:8888")

  val scn = scenario("max performance")
    .forever(exec(http("simple get")
      .get("/"))
      .pause(1 second, 2 seconds))

  setUp(scn.inject(rampUsers(10000) during (30 seconds), nothingFor(2 minutes)))
    .pauses(normalPausesWithStdDevDuration(2 seconds))
    .maxDuration(160 seconds).protocols(httpProtocol)
}