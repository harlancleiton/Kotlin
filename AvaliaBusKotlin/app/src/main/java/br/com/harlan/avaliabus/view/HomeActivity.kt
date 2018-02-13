package br.com.harlan.avaliabus.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.prototype.FragmentPrototype
import br.com.harlan.avaliabus.singleton.NavigationServiceSingleton
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: BaseFragment
        if (item.itemId != navigation.selectedItemId)
            when (item.itemId) {
                R.id.navigation_registration -> {
                    fragment = FragmentPrototype.getFragment(FragmentPrototype.REGISTER_FRAGMENT)
                    navigationService.loadFragment(fragment)
                    title = "Registrar avaliação"
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_historic -> {
                    fragment = FragmentPrototype.getFragment(FragmentPrototype.HISTORIC_FRAGMENT)
                    navigationService.loadFragment(fragment)
                    title = "Histórico de avaliações"
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_statistics -> {
                    fragment = FragmentPrototype.getFragment(FragmentPrototype.STATISTICS_FRAGMENT)
                    navigationService.loadFragment(fragment)
                    title = "Estatísticas do dia"
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_user -> {
                    fragment = FragmentPrototype.getFragment(FragmentPrototype.USER_FRAGMENT)
                    navigationService.loadFragment(fragment)
                    title = "Meu usuário"
                    return@OnNavigationItemSelectedListener true
                }
            }
        false
    }

    @SuppressLint("ResourceType")
    override fun initializeComponents() {
        mCheckPermission()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigationService = NavigationServiceSingleton.getInstance(this, supportFragmentManager)
        navigationService.uploadFragmentHere(R.id.frame_layout)
        //navigationService.loadFragment(RegisterEvaluationFragment())
        navigationService.loadFragment(FragmentPrototype.getFragment(FragmentPrototype.REGISTER_FRAGMENT))
        title = "Registrar avaliação"
    }

    private fun mCheckPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 0)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    override fun addEvents() {

    }
}