package br.com.harlan.avaliabus.business.services

import android.support.v4.app.Fragment
import br.com.harlan.avaliabus.view.BaseFragment
import java.io.Serializable

interface NavigationServiceable {
    fun navigateTo(cls: Class<*>)
    fun navigateTo(cls: Class<*>, closeActivity: Boolean)
    fun navigateTo(cls: Class<*>, objectSerializable: Serializable, name: String, closeActivity: Boolean)
    fun finishActivity()
    fun loadFragment(fragment: BaseFragment)
}