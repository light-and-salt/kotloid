package kr.or.lightsalt.kotloid.app

import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import kr.or.lightsalt.kotloid.instantiateFragment
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

open class SimpleFragmentPagerAdapter constructor(
    private val activity: AppCompatActivity, private vararg val pages: Page
) : FragmentPagerAdapter(activity.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = SparseArray<WeakReference<Fragment>>()

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        fragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int = pages.count { it.visible }

    @Suppress("UNCHECKED_CAST")
    fun <T> getFragment(position: Int): T? = fragments.get(position)?.get() as T?

    override fun getItem(position: Int) =
            getPage(position).run { instantiate(activity, fragmentName) }

    override fun getItemId(position: Int) = getPage(position).fragmentName.hashCode().toLong()

    override fun getItemPosition(item: Any): Int {
        var count = 0
        for (page in pages) {
            if (item.javaClass.name == page.fragmentName) {
                return if (page.visible) count else POSITION_NONE
            }
            if (page.visible) count++
        }
        return POSITION_NONE
    }

    fun getPage(position: Int): Page = pages.filter { it.visible }[position]

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        fragments.put(position, WeakReference(fragment))
        return fragment
    }

    open class Page constructor(
        fragment: KClass<out Fragment>, private val args: Bundle? = null
    ) {
        val fragmentName: String = fragment.java.name
        var visible = true

        open fun instantiate(activity: AppCompatActivity, fragment: String) =
                activity.instantiateFragment(fragment).apply { arguments = args }
    }
}