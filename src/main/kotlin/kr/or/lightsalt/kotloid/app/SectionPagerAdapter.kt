package kr.or.lightsalt.kotloid.app

import android.content.Context
import android.util.SparseArray
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class SectionPagerAdapter constructor(val context: Context, fragmentManager: FragmentManager,
		private vararg val tabs: Tab) : FragmentPagerAdapter(fragmentManager) {
	private val fragments = SparseArray<WeakReference<Fragment>>()

	constructor(activity: AppCompatActivity, vararg tabs: Tab) :
			this(activity, activity.supportFragmentManager, *tabs)

	override fun getItem(position: Int) = getTab(position).run { instantiate(context, fragment) }

	override fun getItemId(position: Int) = getTab(position).fragment.hashCode().toLong()

	override fun getItemPosition(item: Any): Int {
		var count = 0
		for (tab in tabs) {
			if (item.javaClass.name == tab.fragment) {
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

	override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
		fragments.remove(position)
		super.destroyItem(container, position, `object`)
	}

	@Suppress("UNCHECKED_CAST")
	fun <T> getFragment(position: Int): T? = fragments.get(position)?.get() as T?

	open class Tab constructor(fragment: KClass<out Fragment>, var title: String? = null,
			var icon: Int? = null) {
		val fragment: String = fragment.java.name
		var visible = true

		open fun instantiate(context: Context, fname: String) =
				Fragment.instantiate(context, fname)!!
	}
}