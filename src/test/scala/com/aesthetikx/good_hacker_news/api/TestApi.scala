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

      assert(story.id == 8863)
      assert(story.title == "My YC app: Dropbox - Throw away your USB drive")
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

}
