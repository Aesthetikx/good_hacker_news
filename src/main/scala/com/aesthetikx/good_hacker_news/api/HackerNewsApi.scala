package com.aesthetikx.good_hacker_news.api

import com.aesthetikx.good_hacker_news.model.Story

import retrofit.{Callback, RestAdapter}
import retrofit.http.{GET, Path}

trait HackerNewsApi {

  @GET("/item/{id}.json")
  def getStory(@Path("id") id: Int, callback: Callback[Story])

  @GET("/topstories.json")
  def getTopStoryIds(callback: Callback[java.util.List[Integer]])

}

object Api {

  lazy val adapter = new RestAdapter.Builder()
    .setEndpoint("https://hacker-news.firebaseio.com/v0/")
    .build()

  lazy val api: HackerNewsApi = adapter.create(classOf[HackerNewsApi])

  def apply(): HackerNewsApi = api

}
