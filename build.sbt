import android.Keys._

android.Plugin.androidBuild

platformTarget in Android := "android-21"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

libraryDependencies += "com.squareup.retrofit" % "retrofit" % "1.9.0"

name := "good-hacker-news"
