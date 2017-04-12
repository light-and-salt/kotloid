package kr.or.lightsalt.kotloid

import android.widget.TextView

val TextView.trim: String
	get() = text.toString().trim()
