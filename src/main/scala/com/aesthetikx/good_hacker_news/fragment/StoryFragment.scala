package com.aesthetikx.good_hacker_news.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{Menu, MenuInflater}

import com.aesthetikx.good_hacker_news.R
import com.aesthetikx.good_hacker_news.model.Story

abstract class StoryFragment extends Fragment {

  def getStory(): Story = {
    getArguments().getSerializable("story").asInstanceOf[Story]
  }

  override def onActivityCreated(savedInstanceState: Bundle) {
    setHasOptionsMenu(true) // Tell the activity that we have menu items
    super.onActivityCreated(savedInstanceState)
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.view_external, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

}
