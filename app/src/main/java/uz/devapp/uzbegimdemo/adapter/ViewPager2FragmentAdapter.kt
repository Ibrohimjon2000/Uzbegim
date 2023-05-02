package uz.devapp.uzbegimdemo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.devapp.uzbegimdemo.screen.main.home.category.CategoryFragment

class ViewPager2FragmentAdapter(
    fragment: Fragment,
    private val list: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryFragment.newInstance(list[position])
    }
}