@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.webkit

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import android.widget.ProgressBar
import im.delight.android.webview.AdvancedWebView

interface WebClient {
	val progressBar: ProgressBar?
	val url: String?
	val webChromeClient: BaseWebChromeClient
	val webView: AdvancedWebView
	val webViewClient: BaseWebViewClient

	@SuppressLint("SetJavaScriptEnabled")
	fun init() = webView.run {
		webChromeClient.progressBar = progressBar
		setWebChromeClient(webChromeClient)
		setWebViewClient(webViewClient)
		settings.run {
			databaseEnabled = true
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
				databasePath = context.getDir("database", Context.MODE_PRIVATE).path
			}
			domStorageEnabled = true
			javaScriptEnabled = true
			pluginState = WebSettings.PluginState.ON
		}
		loadUrl(this@WebClient.url)
	}

	companion object {
		const val KEY_URL = "url"
	}
}