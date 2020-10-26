package com.osama.chatbotbasicapp.ui

import android.annotation.SuppressLint
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.widget.AppCompatButton
import com.osama.chatbotbasicapp.R
import com.osama.chatbotbasicapp.RootApp
import com.osama.mobioptionsads.MobiInitializationListener
import com.osama.mobioptionsads.MobiOptionsAdsInit
import com.osama.mobioptionsads.banner.MobiBannerListener
import com.osama.mobioptionsads.banner.MobiOptionBannerError
import com.osama.mobioptionsads.banner.MobiOptionsBanner
import com.osama.mobioptionsads.banner.MobiOptionsBannerSize
import com.osama.mobioptionsads.banner.size.AdmobBannerSize
import com.osama.mobioptionsads.banner.size.FacebookBannerSize
import com.osama.mobioptionsads.banner.size.UnityBannerSize
import com.osama.mobioptionsads.interstitial.MobiInterstitialError
import com.osama.mobioptionsads.interstitial.MobiInterstitialListener
import com.osama.mobioptionsads.interstitial.MobiOptionsInterstitial

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private lateinit var mobiOptionsAdsInit: MobiOptionsAdsInit


    private lateinit var mobiOptionsBanner: MobiOptionsBanner
    private lateinit var mobiOptionsInterstitial: MobiOptionsInterstitial

    private lateinit var adsContainer: LinearLayout
    private lateinit var buttonShowInterstitial: AppCompatButton

    private val listener: MobiInitializationListener = object : MobiInitializationListener {
        override fun onInitializationSuccess() {
            Log.d(tag, "onInitializationSuccess: initialization completed successfully")
            setUpAds()
        }

        override fun onInitializationFailed(error: String?) {
            Log.d(tag, "onInitializationFailed: Errors => $error")
        }
    }


    private val bannerListener: MobiBannerListener = object : MobiBannerListener {
        override fun onLoaded(adsProvider: String?) {
            Log.d(tag, "onLoaded: Banner loaded")
        }

        override fun onClicked(adsProvider: String?) {
            TODO("Not yet implemented")
        }

        override fun onFailedToLoad(adsProvider: String?, error: MobiOptionBannerError?) {
            Log.d(tag, "onLoaded: Banner Errors => ${error?.errorMessage}")
        }

        override fun onLeftApplication(adsProvider: String?) {
            TODO("Not yet implemented")
        }
    }


    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adsContainer = findViewById(R.id.adsContainer)
        buttonShowInterstitial = findViewById(R.id.show_interstitial)
        RootApp.setupMobiOptionsAds(listener)
    }


    private fun setUpAds() {
        handler.postDelayed({
            mobiOptionsBanner = MobiOptionsBanner(
                adsContainer, MobiOptionsBannerSize(
                    AdmobBannerSize(AdmobBannerSize.ADMOB_SIZE_BANNER),
                    UnityBannerSize(320, 50),
                    FacebookBannerSize(FacebookBannerSize.FACEBOOK_BANNER_HEIGHT_50)
                ),
                "Banner_0"
            )

            mobiOptionsBanner.load()
            mobiOptionsBanner.setMobiBannerListener(bannerListener)
        }, 500)

        mobiOptionsInterstitial = MobiOptionsInterstitial(this, "Interstitial_2")
        mobiOptionsInterstitial.loadAd()

        mobiOptionsInterstitial.setMobiInterstitialListener(object : MobiInterstitialListener {
            override fun onDisplayed(adsProvider: String?) {
                Log.d(
                    tag,
                    "onDisplayed: interstitial ad displayed, the provider => $adsProvider"
                )
            }

            override fun onClosed(adsProvider: String?) {
                Log.d(
                    tag,
                    "onClosed: interstitial ad closed, the provider => $adsProvider"
                )
            }

            override fun onError(adsProvider: String?, error: MobiInterstitialError?) {
                Log.d(
                    tag,
                    "onError: interstitial errors => ${error?.message}, the provider => $adsProvider"
                )
            }

            override fun onLoaded(adsProvider: String?) {
                Log.d(
                    tag,
                    "onLoaded: interstitial ad loaded, the provider => $adsProvider"
                )
            }

            override fun onClicked(adsProvider: String?) {
                Log.d(
                    tag,
                    "onClicked: interstitial ad clicked, the provider => $adsProvider"
                )
            }
        })

        buttonShowInterstitial.setOnClickListener {
            if (mobiOptionsInterstitial.isLoaded) {
                mobiOptionsInterstitial.show()
            } else {
                makeText(this, "Not loaded yet", Toast.LENGTH_SHORT).show()
            }
        }
    }
}