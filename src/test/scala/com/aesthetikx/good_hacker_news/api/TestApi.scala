package com.aesthetikx.good_hacker_news.api

import com.aesthetikx.good_hacker_news.model.Story

import java.util.concurrent.CountDownLatch

import org.scalatest.FunSpec

import retrofit.{Callback, RetrofitError}
import retrofit.client.Response

class TestApi extends FunSpec {

  describe("Stories") {
    it("loads a story") {
      val api = MockRetrofitClient.mockApi(200, storyFixture)
      val latch = new CountDownLatch(1)
      var story: Story = null;

      api.getStory(8863, new Callback[Story] {
        def failure(error: RetrofitError) {
          latch.countDown()
        }

        def success(s: Story, response: Response) {
          story = s
          latch.countDown()
        }
      })

      latch.await()

      assert(story != null)
      assert(story.by == "dhouston")
      assert(story.id == 8863)
      assert(story.kids === List(8952, 9224, 8917, 8884))
      assert(story.score == 111)
      assert(story.time == 1175714200)
      assert(story.title == "My YC app: Dropbox - Throw away your USB drive")
      assert(story.url.toString == "http://www.getdropbox.com/u/2/screencast.html")
    }

    it("loads the top story list") {
      val api = MockRetrofitClient.mockApi(200, storiesListFixture)
      val latch = new CountDownLatch(1)
      var ids: java.util.List[Integer] = null;

      api.getTopStoryIds(new Callback[java.util.List[Integer]] {
        def failure(error: RetrofitError) {
          latch.countDown()
        }

        def success(list: java.util.List[Integer], response: Response) {
          ids = list
          latch.countDown()
        }
      })

      latch.await()

      assert(ids.size ==  8)
      assert(ids.get(0) == 8414149)
      assert(ids.get(1) == 8414078)
    }
  }

  val storyFixture = """{
    "by" : "dhouston",
    "id" : 8863,
    "kids" : [ 8952, 9224, 8917, 8884 ],
    "score" : 111,
    "time" : 1175714200,
    "title" : "My YC app: Dropbox - Throw away your USB drive",
    "type" : "story",
    "url" : "http://www.getdropbox.com/u/2/screencast.html"
  }"""

  var storiesListFixture = "[ 8414149, 8414078, 8413972, 8411638, 8414102, 8413204, 8413100, 8413971 ]"

}

