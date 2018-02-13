package br.com.harlan.avaliabus.prototype

import android.support.v4.app.Fragment
import br.com.harlan.avaliabus.view.*
import java.util.*

object FragmentPrototype {

    val REGISTER_FRAGMENT: Int = 0
    val HISTORIC_FRAGMENT: Int = 1
    val STATISTICS_FRAGMENT: Int = 2
    val USER_FRAGMENT: Int = 3
    private val hashMapFragment: HashMap<Int, BaseFragment> = HashMap()

    fun loadFragments() {
        hashMapFragment.put(REGISTER_FRAGMENT, RegisterEvaluationFragment())
        hashMapFragment.put(HISTORIC_FRAGMENT, HistoricReviewsFragment())
        hashMapFragment.put(STATISTICS_FRAGMENT, StatisticsFragment())
        hashMapFragment.put(USER_FRAGMENT, UserFragment())
    }

    fun getFragment(key: Int): BaseFragment {
        var fragment: BaseFragment = hashMapFragment.get(key)!!
        return fragment.clone()
    }

    fun replace(key: Int, fragment: BaseFragment) {
        hashMapFragment.remove(key)
        hashMapFragment.put(key, fragment)
    }
}