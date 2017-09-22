package kr.or.lightsalt.kotloid.webkit

import android.app.Activity
import android.webkit.*
import android.widget.ProgressBar
import kr.or.lightsalt.kotloid.*

open class BaseWebChromeClient : WebChromeClient() {
	var activity: Activity? = null

	var progressBar: ProgressBar? = null
		set(value) {
			field = value
			activity = value?.activity()
		}

	override fun onProgressChanged(view: WebView, progress: Int) {
		progressBar?.visibility =
				if (progress < 100) ProgressBar.VISIBLE else ProgressBar.GONE
		progressBar?.progress = progress
	}
}