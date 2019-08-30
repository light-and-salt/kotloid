@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.webkit

import android.content.Intent
import android.os.*
import android.webkit.CookieSyncManager
import android.widget.ProgressBar
import androidx.appcompat.app.*
import im.delight.android.webview.AdvancedWebView
import kr.or.lightsalt.kotloid.R

open class WebActivity : AppCompatActivity(), WebClient {
	override val progressBar by lazy { findViewById<ProgressBar?>(android.R.id.progress) }
	override val url: String? by lazy { intent.getStringExtra(EXTRA_URL) }
	override val webChromeClient by lazy { BaseWebChromeClient() }
	override val webView by lazy { findViewById<AdvancedWebView>(R.id.webView)!! }
	override val webViewClient by lazy { BaseWebViewClient() }

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		webView.onActivityResult(requestCode, resultCode, data)
	}

	override fun onBackPressed() {
		if (!goBack()) {
			super.onBackPressed()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.web)
		init(savedInstanceState)
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			CookieSyncManager.createInstance(this)
		}
	}

	override fun onPause() {
		super.onPause()
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			CookieSyncManager.getInstance().stopSync()
		}
	}

	override fun onResume() {
		super.onResume()
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			CookieSyncManager.getInstance().startSync()
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		webView.saveState(outState)
	}

	companion object {
		const val EXTRA_URL = WebClient.KEY_URL
	}
}