package com.aesthetikx.good_hacker_news.model

import java.net.URL

case class Story(id: Int, title: String, url: URL, time: Long, score: Int, by: String, kids: Array[Int])
