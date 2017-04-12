@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.webkit

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView

@SuppressLint("SetJavaScriptEnabled")
fun WebView.init(webClient: WebClient) = webClient.run {
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
}
