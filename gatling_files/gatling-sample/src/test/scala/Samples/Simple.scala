package Sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class Simple extends Simulation {

  val r = scala.util.Random
  val httpProtocol = http
    .baseUrl("http://localhost:3000") // Here is the root for all relative URLs

  object Index {
    val index = exec(http("main page")
      .get("/"))
      .pause(1 second, 2 seconds)
  }

  object GetPosts {
    val posts = exec(http("posts")
      .get("/posts"))
      .pause(1 second, 2 seconds)
  }

  object AddComment {
    val payload =
      """
         {
          "postId": 1,
          "name": "my comment",
          "email": "test@user.habr",
          "body": "Author is cool. Some text. Hello world!"
          }
      """

    val comments = exec(http("comment")
      .post("/comments")
      .body(StringBody(payload)).asJson)
      .pause(1 second, 2 seconds)
  }

  val scn = scenario("sample 1")
    .exec(Index.index)
    .forever(randomSwitch(
      66.4 -> GetPosts.posts,
      33.3 -> AddComment.comments
    ))

  setUp(scn.inject(rampUsers(10) during (10 seconds), nothingFor(100 seconds)))
    .pauses(normalPausesWithStdDevDuration(2 seconds))
    .maxDuration(1 minute).protocols(httpProtocol)
}