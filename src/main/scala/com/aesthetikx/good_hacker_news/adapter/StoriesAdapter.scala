package com.aesthetikx.good_hacker_news.adapter

import android.support.v7.widget.RecyclerView
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.TextView

import com.aesthetikx.good_hacker_news.R
import com.aesthetikx.good_hacker_news.model.Story

sealed class StoryViewHolder(view: View) extends RecyclerView.ViewHolder(view) {

  val titleText = view.findViewById(R.id.text_view_title).asInstanceOf[TextView]

  def update(story: Story) {
    titleText.setText(story.title)
  }

}

class StoriesAdapter extends RecyclerView.Adapter[StoryViewHolder] {

  private var stories: List[Story] = Nil

  def getItemCount(): Int = stories.size

  def onBindViewHolder(holder: StoryViewHolder, position: Int): Unit = {
    holder.update(stories(position))
  }

  def onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder = {
    val rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_row, parent, false)
    return new StoryViewHolder(rowView)
  }

  def updateStories(stories: List[Story]) {
    this.stories = stories
    notifyDataSetChanged() // TODO be more specific
  }

}
