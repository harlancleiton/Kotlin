package br.com.harlan.avaliabus.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.harlan.avaliabus.singleton.MessageServiceSingleton
import br.com.harlan.avaliabus.singleton.NavigationServiceSingleton
import br.com.harlan.avaliabus.view.services.MessageService
import br.com.harlan.avaliabus.view.services.NavigationService

abstract class BaseFragment : Fragment, Cloneable {

    //region Variables
    protected lateinit var viewRoot: View
    protected var layout: Int
    protected lateinit var navigationService: NavigationService
    protected lateinit var messageService: MessageService
    //endregion Variables

    //region Constructor and onCreateView
    constructor(@LayoutRes layout: Int) {
        this.layout = layout
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater!!.inflate(layout, container, false)
        navigationService = NavigationServiceSingleton.getInstance(activity, activity.supportFragmentManager)
        messageService = MessageServiceSingleton.getInstance(activity)
        initializeComponents(viewRoot)
        addEvents()
        setHasOptionsMenu(true)
        return viewRoot
    }
    //endregion Constructor and onCreateView

    //region Abstract Functions
    abstract fun initializeComponents(viewRoot: View)

    abstract fun addEvents()
    //endregion Abstract Functions

    public override fun clone(): BaseFragment {
        var fragment: Any? = null
        try {
            fragment = super.clone()
        } catch (e: CloneNotSupportedException) {
            messageService.newToast("Ocorreu um erro ao tentar mudar o contexto da aplicação.")
            e.printStackTrace()
        }
        return fragment as BaseFragment
    }
    //endregion Cloneable
}