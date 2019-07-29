package Sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class Simple2 extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:3000")

  val payload =
    """
         {
          "postId": 1,
          "name": "my comment",
          "email": "test@user.habr",
          "body": "Author is cool. Some text. Hello world!"
          }
      """

  val scn = scenario("sample 1")
    .exec(http("main page").get("/")).pause(1 second, 2 seconds)
    .forever(randomSwitch(
      66.4 -> exec(http("posts").get("/posts")).pause(1 second, 2 seconds),
      33.3 -> exec(http("comment").post("/comments").body(StringBody(payload)).asJson).pause(1 second, 2 seconds)
    ))

  setUp(scn.inject(rampUsers(10) during (10 seconds), nothingFor(100 seconds)))
    .pauses(normalPausesWithStdDevDuration(2 seconds))
    .maxDuration(1 minute).protocols(httpProtocol)
}