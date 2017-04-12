@file:Suppress("UNCHECKED_CAST")

package kr.or.lightsalt.kotloid

import android.app.Activity
import android.view.View

fun <T : Activity> View.activity() = context as T

fun <T : View> View.find(id: Int): T? = findViewById(id) as T?

var View.isGone: Boolean
	get() = visibility == View.GONE
	set(value) {
		visibility = if (value) View.GONE else View.VISIBLE
	}

var View.isInVisible: Boolean
	get() = visibility == View.INVISIBLE
	set(value) {
		visibility = if (value) View.INVISIBLE else View.VISIBLE
	}
