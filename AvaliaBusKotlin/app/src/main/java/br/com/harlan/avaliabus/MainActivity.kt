package br.com.harlan.avaliabus

import br.com.harlan.avaliabus.prototype.FragmentPrototype
import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import br.com.harlan.avaliabus.view.BaseActivity
import br.com.harlan.avaliabus.view.HomeActivity
import br.com.harlan.avaliabus.view.LoginActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initializeComponents() {
        checkLogin()
    }

    private fun checkLogin() {
        if (FirebaseSingleton.getFirebaseAuth().currentUser == null) {
            navigationService.navigateTo(LoginActivity::class.java)
        } else {
            FragmentPrototype.loadFragments()
            navigationService.navigateTo(HomeActivity::class.java)
        }
    }

    override fun addEvents() {
    }
}