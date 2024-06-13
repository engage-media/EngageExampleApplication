package com.engage.em_ads_sdk

import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.source.ads.AdsMediaSource
import com.engage.emadsdk.data.EMVASTAd
import kotlinx.coroutines.flow.Flow

internal interface EMAdRequester {
    suspend fun requestAds()
    val completeVastUrl: String
    val receivedAds: Flow<List<EMVASTAd>>
}