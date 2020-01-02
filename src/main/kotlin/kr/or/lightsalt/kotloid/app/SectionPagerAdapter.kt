package kr.or.lightsalt.kotloid.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import kotlin.reflect.KClass

class SectionPagerAdapter constructor(activity: AppCompatActivity, private vararg val tabs: Tab)
	: SimpleFragmentPagerAdapter(activity, *tabs) {

	fun getTab(position: Int) = tabs.filter { it.visible }[position]

	open class Tab constructor(fragment: KClass<out Fragment>, var title: String? = null,
			var icon: Int? = null, args: Bundle? = null): Page(fragment, args)
}
