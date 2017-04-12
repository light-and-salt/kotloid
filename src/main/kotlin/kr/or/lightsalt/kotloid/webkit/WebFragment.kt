@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.webkit

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import im.delight.android.webview.AdvancedWebView
import kr.or.lightsalt.kotloid.R
import kr.or.lightsalt.kotloid.lazyViewById

open class WebFragment : Fragment(), WebClient {
	override val progressBar: ProgressBar by lazyViewById(android.R.id.progress)
	override val url: String? by lazy { arguments?.getString(KEY_URL) }
	override val webChromeClient by lazy { BaseWebChromeClient() }
	override val webView: AdvancedWebView by lazyViewById(R.id.webView)
	override val webViewClient by lazy { BaseWebViewClient() }

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		webView.onActivityResult(requestCode, resultCode, data)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?) = inflater.inflate(R.layout.web, container, false)!!

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) = init()

	companion object {
		const val KEY_URL = WebClient.KEY_URL
	}
}