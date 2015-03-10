package com.aesthetikx.good_hacker_news.activity

import android.os.Bundle
import android.support.v4.app.{Fragment, FragmentActivity}

import com.aesthetikx.good_hacker_news.fragment.{CommentsFragment, ArticleFragment}

class StoryActivity extends FragmentActivity {

  override def onCreate(savedInstanceState: Bundle) {

    // Decide to show the comments or article fragment
    val fragment: Fragment = getIntent().getExtras().getBoolean("comments", false) match {
      case true  => new CommentsFragment()
      case false => new ArticleFragment()
    }

    // Set the story id argument
    val args = new Bundle()
    args.putInt("story_id", getIntent().getExtras().getInt("story_id"))
    fragment.setArguments(args)

    // Replace the root view of this activity with the fragment
    getSupportFragmentManager()
      .beginTransaction()
      .replace(android.R.id.content, fragment)
      .commit()
  }

}
