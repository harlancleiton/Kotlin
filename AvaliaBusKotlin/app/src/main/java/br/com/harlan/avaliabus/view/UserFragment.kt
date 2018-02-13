package br.com.harlan.avaliabus.view

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.UserBusiness
import br.com.harlan.avaliabus.business.services.MessageServiceable

class UserFragment : BaseFragment(R.layout.fragment_user), MessageServiceable.WithAnswer {

    //region MenuActionBar
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_about, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_logout_user) {
            messageService.newAlertWithAnswer("Sair", "Deseja sair do aplicativo?", "Sair", "Cancelar", this)
        } else if (item!!.itemId == R.id.action_about_user)
            navigationService.navigateTo(AboutActivity::class.java, false)
        return true
    }

    override fun answer(action: Boolean) {
        if (action)
            UserBusiness(messageService, navigationService).userLogOut()
    }

    //endregion MenuActionBar

    //region Functions
    override fun initializeComponents(viewRoot: View) {

    }

    override fun addEvents() {

    }
    //endregion Functions
}