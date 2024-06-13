package com.engage.em_ads_sdk

import kotlinx.coroutines.flow.Flow

interface EMVideoPlayerListener {
    val onProgressUpdateFlow: Flow<Pair<Long, Long>>
    val onContentEndedFlow: Flow<Unit>
}