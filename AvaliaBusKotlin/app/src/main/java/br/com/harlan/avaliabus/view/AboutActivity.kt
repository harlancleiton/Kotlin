package br.com.harlan.avaliabus.view

import android.view.MenuItem
import br.com.harlan.avaliabus.R

class AboutActivity : BaseActivity(R.layout.activity_about) {

    override fun initializeComponents() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Sobre o AvaliaBus"
        checkVersion()
    }

    private fun checkVersion() {

    }

    override fun addEvents() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
