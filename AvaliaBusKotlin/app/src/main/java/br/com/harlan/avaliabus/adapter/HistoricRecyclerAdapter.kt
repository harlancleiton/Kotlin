package br.com.harlan.avaliabus.adapter

import android.content.Context
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.EvaluationBusiness
import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.model.EvaluationModel
import br.com.harlan.avaliabus.view.EditReviewActivity
import br.com.harlan.avaliabus.view.services.MessageService
import br.com.harlan.avaliabus.view.services.NavigationService

class HistoricRecyclerAdapter : RecyclerView.Adapter<HistoricRecyclerAdapter.HistoricViewHolder>, MessageServiceable.WithAnswer {

    private var context: Context
    private var navigationService: NavigationService
    private var messageService: MessageService
    private var evaluationModels: ArrayList<EvaluationModel>
    private var mPosition: Int = 0

    constructor(navigationService: NavigationService, messageService: MessageService, evaluationModels: ArrayList<EvaluationModel>, context: Context) {
        this.context = context
        this.navigationService = navigationService
        this.messageService = messageService
        this.evaluationModels = evaluationModels
    }

    override fun getItemCount(): Int {
        return evaluationModels.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HistoricViewHolder {
        val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.card_line_review, parent, false)
        return HistoricViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoricViewHolder?, position: Int) {
        if (holder != null) {
            val evaluationModel: EvaluationModel = evaluationModels.get(position)
            holder.tvNumeroLinha.text = evaluationModel.lineNumber.toString()
            holder.tvNumeroOnibus.text = evaluationModel.busNumber.toString()
            holder.tvData.text = evaluationModel.date
            holder.tvHora.text = evaluationModel.hour
            holder.rbOnibus.rating = evaluationModel.busNote
            holder.rbMotorista.rating = evaluationModel.driverNote
            holder.rbSeguranca.rating = evaluationModel.securityNote
            holder.rbLinha.rating = evaluationModel.lineNote
            holder.btnEditar.setOnClickListener {
                navigationService.navigateTo(EditReviewActivity::class.java, evaluationModel, "EvaluationModel", false)
            }
            holder.btnExcluir.setOnClickListener {
                mPosition = position
                messageService.newAlertWithAnswer("Excluir", "Tem certeza que deseja excluir essa avaliação?", "Excluir", "Cancelar", this)
            }
        }
    }

    override fun answer(action: Boolean) {
        if (action)
            EvaluationBusiness(messageService, navigationService).deleteReview(evaluationModels.get(mPosition))
    }

    fun dataChange() {
        evaluationModels.clear()
    }

    class HistoricViewHolder : RecyclerView.ViewHolder {

        var tvNumeroOnibus: TextView
        var tvNumeroLinha: TextView
        var tvHora: TextView
        var tvData: TextView
        var rbMotorista: RatingBar
        var rbOnibus: RatingBar
        var rbSeguranca: RatingBar
        var rbLinha: RatingBar
        var btnEditar: AppCompatButton
        var btnExcluir: AppCompatButton

        constructor(itemView: View) : super(itemView) {
            tvNumeroOnibus = itemView.findViewById(R.id.tv_onibus_cv)
            tvNumeroLinha = itemView.findViewById(R.id.tv_linha_cv)
            tvHora = itemView.findViewById(R.id.tv_hora_cv)
            tvData = itemView.findViewById(R.id.tv_data_cv)
            rbMotorista = itemView.findViewById(R.id.rb_morotista_cv)
            rbMotorista.setIsIndicator(true)
            rbOnibus = itemView.findViewById(R.id.rb_onibus_cv)
            rbOnibus.setIsIndicator(true)
            rbSeguranca = itemView.findViewById(R.id.rb_seguranca_cv)
            rbSeguranca.setIsIndicator(true)
            rbLinha = itemView.findViewById(R.id.rb_linha_cv)
            rbLinha.setIsIndicator(true)
            btnEditar = itemView.findViewById(R.id.btn_editar_card)
            btnExcluir = itemView.findViewById(R.id.btn_excluir_card)
        }
    }
}