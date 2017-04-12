@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.app

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.or.lightsalt.kotloid.R
import kr.or.lightsalt.kotloid.lazyViewById

open class TabsFragment : Fragment() {

	val tabLayout: TabLayout by lazyViewById(R.id.tabLayout)
	val viewPager: ViewPager by lazyViewById(R.id.viewPager)

	var adapter: SectionPagerAdapter? = null
		set(value) {
			field = value
			viewPager.adapter = value
			tabLayout.setupWithViewPager(viewPager)
			for (i in 0 until tabLayout.tabCount) value!!.getTab(i).icon?.let {
				tabLayout.getTabAt(i)?.setIcon(it)
			}
		}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
			= inflater.inflate(R.layout.fragment_tabs, container, false)!!
}
