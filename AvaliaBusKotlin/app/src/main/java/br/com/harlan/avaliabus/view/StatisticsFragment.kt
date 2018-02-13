package br.com.harlan.avaliabus.view

import android.view.Menu
import android.view.MenuInflater
import android.view.View
import br.com.harlan.avaliabus.R

class StatisticsFragment : BaseFragment(R.layout.fragment_statistics) {

    //region MenuActionBar
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    //region MenuActionBar

    //region Functions
    override fun initializeComponents(viewRoot: View) {
        setHasOptionsMenu(true)
    }

    override fun addEvents() {

    }
    //region Functions
}