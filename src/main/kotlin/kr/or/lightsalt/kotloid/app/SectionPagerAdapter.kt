package kr.or.lightsalt.kotloid.app

import android.support.v4.app.Fragment
import android.support.v4.app.Fragment.instantiate
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.ViewGroup
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class SectionPagerAdapter constructor(val activity: AppCompatActivity,
			vararg private val tabs: Tab)
		: FragmentPagerAdapter(activity.supportFragmentManager) {
		private val fragments = SparseArray<WeakReference<Fragment>>()

		override fun getItem(position: Int) = instantiate(activity, getTab(position).fragment)!!

		override fun getItemId(position: Int) = getTab(position).fragment.hashCode().toLong()

		override fun getItemPosition(item: Any?): Int {
			var count = 0
			for (tab in tabs) {
				if (item?.javaClass?.name == tab.fragment) {
					return if (tab.visible) count else POSITION_NONE
				}
				if (tab.visible) count++
			}
			return POSITION_NONE
		}

		override fun getCount(): Int = tabs.count { it.visible }

		override fun getPageTitle(position: Int) = getTab(position).title

		fun getTab(position: Int) = tabs.filter { it.visible }[position]

		override fun instantiateItem(container: ViewGroup, position: Int): Any {
			val fragment = super.instantiateItem(container, position) as Fragment
			fragments.put(position, WeakReference(fragment))
			return fragment
		}

		override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {
			fragments.remove(position)
			super.destroyItem(container, position, `object`)
		}

		@Suppress("UNCHECKED_CAST")
		fun <T : Fragment> getFragment(position: Int): T? = fragments.get(position)?.get() as T?

		class Tab constructor(fragment: KClass<out Fragment>, var title: String,
				var icon: Int? = null) {
			val fragment = fragment.java.name!!
			var visible = true
		}
	}