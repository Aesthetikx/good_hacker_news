package com.aesthetikx.good_hacker_news.api

import java.util.concurrent.Executors

import retrofit.RestAdapter
import retrofit.client.{Client, Header, Request, Response}
import retrofit.mime.TypedByteArray

class MockRetrofitClient(statusCode: Int, responseBody: String) extends Client {

  override def execute(request: Request): Response = {
    new Response(request.getUrl(),
      statusCode,
      responseBody,
      new java.util.ArrayList[Header],
      new TypedByteArray("application/json", responseBody.getBytes()))
  }

}

object MockRetrofitClient {

  def mockApi(statusCode: Int, responseBody: String): HackerNewsApi = {
    val executor = Executors.newSingleThreadExecutor()

    val adapter = new RestAdapter.Builder()
      .setEndpoint("https://mock.com")
      .setClient(new MockRetrofitClient(statusCode, responseBody))
      .setExecutors(executor, executor)
      .build()

    adapter.create(classOf[HackerNewsApi])
  }

}
