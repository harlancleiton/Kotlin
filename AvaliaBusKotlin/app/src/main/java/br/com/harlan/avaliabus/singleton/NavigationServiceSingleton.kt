package br.com.harlan.avaliabus.singleton

import android.app.Activity
import android.support.v4.app.FragmentManager
import br.com.harlan.avaliabus.view.services.NavigationService

object NavigationServiceSingleton {

    private var navigationService: NavigationService? = null

    fun getInstance(activity: Activity): NavigationService {
        if (navigationService == null) {
            navigationService = NavigationService(activity)
        } else {
            navigationService!!.activity = activity
        }
        return navigationService as NavigationService
    }

    fun getInstance(activity: Activity, fragmentManager: FragmentManager): NavigationService {
        if (navigationService == null) {
            navigationService = NavigationService(activity, fragmentManager)
        } else {
            navigationService!!.activity = activity
            navigationService!!.fragmentManager = fragmentManager
        }
        return navigationService as NavigationService
    }
}