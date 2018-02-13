package br.com.harlan.avaliabus.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.adapter.HistoricRecyclerAdapter
import br.com.harlan.avaliabus.business.EvaluationBusiness
import br.com.harlan.avaliabus.business.services.TaskService
import br.com.harlan.avaliabus.model.EvaluationModel

class HistoricReviewsFragment : BaseFragment(R.layout.fragment_historic_reviews), TaskService.UpgradeComponents {

    //region Variables
    private lateinit var progressBar: ProgressBar
    private lateinit var evaluationModels: ArrayList<EvaluationModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var historicRecyclerAdapter: HistoricRecyclerAdapter
    //endregion Variables

    override fun initializeComponents(viewRoot: View) {
        progressBar = viewRoot.findViewById(R.id.pgb_loading_historico)
        evaluationModels = ArrayList<EvaluationModel>()
        recyclerView = viewRoot.findViewById(R.id.rc_avaliacoes_anteriores)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        historicRecyclerAdapter = HistoricRecyclerAdapter(navigationService, messageService, evaluationModels, context)
        recyclerView.adapter = historicRecyclerAdapter
    }

    //region MenuActionBar
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_search_historic -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //endregion MenuActionBar

    override fun addEvents() {
        retrieveReviews()
    }

    private fun retrieveReviews() {
        EvaluationBusiness(messageService, navigationService).retrieveReviews(evaluationModels, historicRecyclerAdapter, this)
    }

    override fun requisitionCompleted() {
        progressBar.visibility = View.GONE
    }
}