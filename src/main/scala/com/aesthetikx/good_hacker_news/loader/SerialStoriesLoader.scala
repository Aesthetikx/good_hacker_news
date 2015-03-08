package com.aesthetikx.good_hacker_news.loader

import android.os.AsyncTask

import com.aesthetikx.good_hacker_news.api.Api
import com.aesthetikx.good_hacker_news.model.Story

import retrofit.{Callback, RetrofitError}
import retrofit.client.Response

import scala.concurrent.ExecutionContext

class SerialStoriesLoader {

  implicit val exec = ExecutionContext.fromExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

  def loadStories(ids: List[Int], onStory: (Story, Int) => Unit, onFail: (Throwable) => Unit) {

    def helper(ids: List[Int], ranks: List[Int]) {
      if (ids.size > 0) {
        Api().getStory(ids.head, new Callback[Story]() {
          override def success(story: Story, response: Response) {
            onStory(story, ranks.head)
            helper(ids.tail, ranks.tail)
          }

          override def failure(error: RetrofitError) {
            onFail(error)
            helper(ids.tail, ranks.tail)
          }
        })
      }
    }

    helper(ids, (0 until ids.size).toList)
  }
}
