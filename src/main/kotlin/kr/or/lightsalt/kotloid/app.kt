@file:Suppress("UNCHECKED_CAST")

package kr.or.lightsalt.kotloid

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*

fun Activity.showToastOnUi(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = runOnUiThread {
	Toast.makeText(this, text, duration).show()
}

fun AppCompatActivity.instantiateFragment(className: String)
	= supportFragmentManager.fragmentFactory.instantiate(classLoader, className)

fun <T : View> Fragment.lazyViewById(id: Int) = weakRef { view!!.findViewById<T>(id) }

fun Fragment.startActivity(action: String, url: String)
		= startActivity(Intent(action, Uri.parse(url)))

fun <T : Fragment> Fragment.getParent() = parentFragment as T

fun FragmentManager.commitTransaction(transaction: FragmentTransaction.() -> Unit)
		= beginTransaction().apply(transaction).commit()
