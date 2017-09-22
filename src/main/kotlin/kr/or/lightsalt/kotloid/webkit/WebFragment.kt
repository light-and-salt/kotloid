@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.webkit

import android.content.*
import android.os.*
import android.support.v4.app.Fragment
import android.view.*
import android.webkit.CookieSyncManager
import android.widget.ProgressBar
import im.delight.android.webview.AdvancedWebView
import kr.or.lightsalt.kotloid.*

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

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			CookieSyncManager.createInstance(context)
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?) = inflater.inflate(R.layout.web, container, false)!!

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

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) = init()

	companion object {
		const val KEY_URL = WebClient.KEY_URL
	}
}