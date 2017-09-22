@file:Suppress("UNCHECKED_CAST")

package kr.or.lightsalt.kotloid

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.app.*
import android.view.View
import android.widget.Toast

fun Activity.showToastOnUi(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = runOnUiThread {
	Toast.makeText(this, text, duration).show()
}

fun <T : View> Fragment.lazyViewById(id: Int) = lazy { view!!.findViewById<T>(id) }

fun Fragment.startActivity(action: String, url: String)
		= startActivity(Intent(action, Uri.parse(url)))

fun FragmentManager.commitTransaction(transaction: FragmentTransaction.() -> Unit)
		= beginTransaction().apply(transaction).commit()
