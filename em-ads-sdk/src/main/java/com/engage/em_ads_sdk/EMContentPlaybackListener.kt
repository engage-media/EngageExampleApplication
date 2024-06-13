package com.engage.em_ads_sdk

interface EMContentPlaybackListener {
    fun onProgressUpdate(currentTime: Long, totalTime: Long)
    fun onContentEnded()
}