package kr.or.lightsalt.kotloid

import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings.Secure
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.reflect.KClass


val Context.androidId: String?
	@SuppressLint("HardwareIds")
	get() = Secure.getString(contentResolver, Secure.ANDROID_ID)

fun Context.dpToPixel(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
		dp, resources.displayMetrics)

fun Context.getMetaData(name: String) = packageManager.getApplicationInfo(packageName,
		PackageManager.GET_META_DATA).metaData?.getString(name)

fun Context.inchToPixel(inch: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN,
		inch, resources.displayMetrics)

fun Context.installPackage(packageName: String, referrer: String? = "") {
	startActivity(Intent.ACTION_VIEW, "market://details?id=$packageName&referrer=$referrer")
}

fun Context.isPackageInstalled(packageName: String) = try {
	packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
	true
} catch (e: PackageManager.NameNotFoundException) {
	e.printStackTrace()
	false
}

fun Context.pointsToPixel(points: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT,
		points, resources.displayMetrics)

fun Context.scaledPixel(value: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
		value, resources.displayMetrics)

fun Context.showAlertDialog(builder: AlertDialog.Builder.() -> Unit)
		= AlertDialog.Builder(this).apply(builder).show()!!

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
	Toast.makeText(this, text, duration).show()
}

fun Context.startActivity(kClass: KClass<*>) = startActivity(Intent(this, kClass.java))

fun Context.startActivity(action: String, url: String)
		= startActivity(Intent(action, Uri.parse(url)))
