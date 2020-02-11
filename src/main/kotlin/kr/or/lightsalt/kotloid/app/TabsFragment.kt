@file:Suppress("DEPRECATION")

package kr.or.lightsalt.kotloid.app

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kr.or.lightsalt.kotloid.R
import kr.or.lightsalt.kotloid.lazyViewById

open class TabsFragment(contentLayoutId: Int = R.layout.fragment_tabs) : Fragment(contentLayoutId) {
    var tabLayout by lazyViewById<TabLayout>(R.id.tabLayout)
    var viewPager by lazyViewById<ViewPager>(R.id.viewPager)

	var adapter: SectionPagerAdapter? = null
		set(value) {
			field = value
			viewPager.adapter = value
			tabLayout.setupWithViewPager(viewPager)
			for (i in 0 until tabLayout.tabCount) value!!.getTab(i).icon?.let {
				tabLayout.getTabAt(i)?.setIcon(it)
			}
		}

	override fun onDestroyView() {
		super.onDestroyView()
		adapter = null
		tabLayout = null
		viewPager = null
	}
}
