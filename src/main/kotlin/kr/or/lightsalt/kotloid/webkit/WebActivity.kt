@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.webkit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import im.delight.android.webview.AdvancedWebView
import kr.or.lightsalt.kotloid.R

open class WebActivity : AppCompatActivity(), WebClient {
	override val progressBar by lazy { findViewById(android.R.id.progress) as ProgressBar }
	override val url: String? by lazy { intent.getStringExtra(EXTRA_URL) }
	override val webChromeClient by lazy { BaseWebChromeClient() }
	override val webView by lazy { findViewById(R.id.webView) as AdvancedWebView }
	override val webViewClient by lazy { BaseWebViewClient() }

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		webView.onActivityResult(requestCode, resultCode, data)
	}

	override fun onBackPressed() {
		if (webView.canGoBack()) webView.goBack()
		else super.onBackPressed()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.web)
		init()
	}

	companion object {
		const val EXTRA_URL = WebClient.KEY_URL
	}
}