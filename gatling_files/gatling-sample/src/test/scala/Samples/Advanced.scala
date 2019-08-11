package Sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class TransactionTest extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:3000")

  object NewPost {
    val payload =
      """
         {
          "userId": 1,
          "title": "my shiny new post",
          "body": "hello everybody"
          }
      """

    val post = exec(http("new post")
      .post("/posts")
      .check(status is 201) // checking response status code
      // saving post ID to session. Can be used in strings as ${postId}
      // can be called within session as session("postId").as[String]
      // value can be set type by jsonPath("$.id").ofType[Int]
      .check(jsonPath("$.id").saveAs("postId"))
      .body(StringBody(payload)).asJson)
      .pause(1 second, 2 seconds)
  }

  object NewComment {
    val payload =
      """
         {
          "postId": ${postId},
          "name": "my comment",
          "email": "test@user.habr",
          "body": "Author is cool. Some text. Hello world!"
          }
      """

    val comment = exec(http("new comment")
      .post("/comments")
      .check(status is 201) // checking response status code
      .check(jsonPath("$.id").saveAs("commentId")) // saving
      .body(StringBody(payload)).asJson)
      .pause(1 second, 2 seconds)
  }

  object ReadComment {
    val readCom = exec(http("read comment")
      .get("/comments/${commentId}")
      .check(status is 200))
      .pause(1 second, 2 seconds)
  }

  val scn = scenario(" transaction sample").forever(exec(NewPost.post, NewComment.comment, ReadComment.readCom))

  setUp(scn.inject(rampUsers(3) during (10 seconds), nothingFor(60 seconds)))
    .pauses(normalPausesWithStdDevDuration(2 seconds))
    .maxDuration(1 minute).protocols(httpProtocol)
}