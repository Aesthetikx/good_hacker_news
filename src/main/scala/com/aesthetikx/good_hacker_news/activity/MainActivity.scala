package com.aesthetikx.good_hacker_news.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.{LinearLayoutManager, RecyclerView}

class MainActivity extends Activity {

  override def onCreate(savedInstanceState: Bundle) {
    val view: RecyclerView = new RecyclerView(this)

    view.setHasFixedSize(true)  // Size of row views won't change

    view.setLayoutManager(new LinearLayoutManager(this))

    setContentView(view)
  }

}
