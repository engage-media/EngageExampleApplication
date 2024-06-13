package com.engage.em_ads_sdk.ima

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ima.ImaAdsLoader
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.ads.AdsMediaSource
import androidx.media3.ui.PlayerView
import com.engage.emadsdk.EMAdRequester
import com.engage.emadsdk.data.EMAdMapper
import com.engage.emadsdk.data.EMVASTAd
import com.engage.emadsdk.network.AdNetworkService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.flow.map

@UnstableApi
internal class AdRequesterImpl(
    private val vastUrl: String,
    private val adNetworkService: AdNetworkService
) : EMAdRequester {

    // create a flow that will emit a list of EMAds
    private val flow: MutableStateFlow<List<EMVASTAd>?> = MutableStateFlow<List<EMVASTAd>?>(null)

    override val receivedAds: Flow<List<EMVASTAd>>
        get() {
            return flow.filter { it != null }.map { it!! }
        }

    override suspend fun requestAds() {
        val videoUrl = adNetworkService.fetchVASTResponse(vastUrl)
        val mappedResponse = EMAdMapper().mapToEMVASTAd(videoUrl)
        flow.value = mappedResponse
    }

    override val completeVastUrl: String
        get() = adNetworkService.vastUrl

}