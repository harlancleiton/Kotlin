package br.com.harlan.avaliabus.view

import android.view.MenuItem
import android.widget.*
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.EvaluationBusiness
import br.com.harlan.avaliabus.model.EvaluationModel
import kotlinx.android.synthetic.main.activity_edit_review.*

class EditReviewActivity : BaseActivity(R.layout.activity_edit_review) {

    //region Variables
    private lateinit var evaluationModel: EvaluationModel
    //Spinner
    private lateinit var spinnerCidades: Spinner
    //EditText
    private lateinit var edtNumeroOnibus: EditText
    private lateinit var edtNumeroLinha: EditText
    private lateinit var edtObservacoes: EditText
    //Button
    private lateinit var btnEnviar: Button
    //Ratingbar
    private lateinit var rbOnibus: RatingBar
    private lateinit var rbMotorista: RatingBar
    private lateinit var rbSeguranca: RatingBar
    private lateinit var rbLinha: RatingBar
    //Checkbox
    private lateinit var cbCrowdedBus: CheckBox
    private lateinit var cbOnibusSujo: CheckBox
    private lateinit var cbJanelaDanificada: CheckBox
    private lateinit var cbAssentoQuebrado: CheckBox
    private lateinit var cbLampadaNaoAcende: CheckBox
    private lateinit var cbMotoristaNaoParou: CheckBox
    private lateinit var cbMotoristaCelular: CheckBox
    private lateinit var cbMotoristaFreouBruscamente: CheckBox
    private lateinit var cbMotoristaVelocidade: CheckBox
    private lateinit var cbOnibusAssaltado: CheckBox
    private lateinit var cbAtosVandalismo: CheckBox
    private lateinit var cbBrigasOnibus: CheckBox
    private lateinit var cbOnibusQuebrou: CheckBox
    private lateinit var cbPercursoLongo: CheckBox
    private lateinit var cbAreasAriscadas: CheckBox
    private lateinit var cbOnibusLinhaAssaltados: CheckBox
    //endregion Variables

    override fun initializeComponents() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        evaluationModel = intent.getSerializableExtra("EvaluationModel") as EvaluationModel
        supportActionBar!!.title = "Editar avaliação"
        edtNumeroOnibus = edt_numero_onibus_editar
        edtNumeroOnibus.setText(evaluationModel.busNumber.toString())
        edtNumeroLinha = edt_numero_linha_editar
        edtNumeroLinha.setText(evaluationModel.lineNumber.toString())
        edtObservacoes = edt_observacoes_editar
        edtObservacoes.setText(evaluationModel.comments)
        btnEnviar = btn_enviar_avaliacao
        rbOnibus = rb_onibus_editar
        rbOnibus.rating = evaluationModel.busNote
        rbMotorista = rb_motorista_editar
        rbMotorista.rating = evaluationModel.driverNote
        rbSeguranca = rb_seguranca_editar
        rbSeguranca.rating = evaluationModel.securityNote
        rbLinha = rb_linha_editar
        rbLinha.rating = evaluationModel.lineNote
        cbCrowdedBus = cb_onibus_lotado_editar
        cbCrowdedBus.isChecked = evaluationModel.crowdedBus
        cbOnibusSujo = cb_onibus_sujo_editar
        cbOnibusSujo.isChecked = evaluationModel.dirtyBus
        cbJanelaDanificada = cb_janela_quebrada_editar
        cbJanelaDanificada.isChecked = evaluationModel.windowDamaged
        cbAssentoQuebrado = cb_assentos_quebrados_editar
        cbAssentoQuebrado.isChecked = evaluationModel.brokenSeat
        cbLampadaNaoAcende = cb_lampadas_queimadas_editar
        cbLampadaNaoAcende.isChecked = evaluationModel.brokenLamp
        cbMotoristaNaoParou = cb_motorista_nao_parou_editar
        cbMotoristaNaoParou.isChecked = evaluationModel.driverDidNotStopPoint
        cbMotoristaCelular = cb_motorista_celular_editar
        cbMotoristaCelular.isChecked = evaluationModel.cellPhoneDriver
        cbMotoristaFreouBruscamente = cb_motorista_freou_bruscamente_editar
        cbMotoristaFreouBruscamente.isChecked = evaluationModel.driverStoppedAbruptly
        cbMotoristaVelocidade = cb_motorista_velocidade_editar
        cbMotoristaVelocidade.isChecked = evaluationModel.driverAboveSpeed
        cbOnibusAssaltado = cb_onibus_assaltado_editar
        cbOnibusAssaltado.isChecked = evaluationModel.dirtyBus
        cbAtosVandalismo = cb_atos_vandalismo_editar
        cbAtosVandalismo.isChecked = evaluationModel.actsVandalismBus
        cbBrigasOnibus = cb_brigas_onibus_editar
        cbBrigasOnibus.isChecked = evaluationModel.fightsBus
        cbOnibusQuebrou = cb_onibus_quebrou_editar
        cbOnibusQuebrou.isChecked = evaluationModel.busBroke
        cbPercursoLongo = cb_percurso_longo_editar
        cbPercursoLongo.isChecked = evaluationModel.longCourse
        cbAreasAriscadas = cb_linha_areas_arriscadas_editar
        cbAreasAriscadas.isChecked = evaluationModel.ariscatedAreas
        cbOnibusLinhaAssaltados = cb_linha_assaltada_constantemente_editar
        cbOnibusLinhaAssaltados.isChecked = evaluationModel.busWasRobbed
    }

    override fun addEvents() {
        btnEnviar.setOnClickListener {
            registerEvaluation()
        }
    }

    private fun registerEvaluation() {
        try {
            recuperarDadosFormulario()
            EvaluationBusiness(messageService, navigationService).editEvaluation(evaluationModel)
        } catch (e: Exception) {
            messageService.newToast("Ocorreu um erro ao tentar recuperar os dados preenchidos." + e.message)
        }
    }

    private fun recuperarDadosFormulario() {
        evaluationModel.comments = edtObservacoes.text.toString()
        //evaluationModel.cidade = spinnerCidades.selectedItem.toString()
        evaluationModel.busNumber = Integer.parseInt(edtNumeroOnibus.text.toString())
        evaluationModel.lineNumber = Integer.parseInt(edtNumeroLinha.text.toString())
        evaluationModel.busNote = rbOnibus.rating
        evaluationModel.driverNote = rbMotorista.rating
        evaluationModel.securityNote = rbSeguranca.rating
        evaluationModel.lineNote = rbLinha.rating
        evaluationModel.crowdedBus = cbCrowdedBus.isChecked
        evaluationModel.dirtyBus = cbOnibusSujo.isChecked
        evaluationModel.windowDamaged = cbJanelaDanificada.isChecked
        evaluationModel.brokenSeat = cbAssentoQuebrado.isChecked
        evaluationModel.brokenLamp = cbLampadaNaoAcende.isChecked
        evaluationModel.driverDidNotStopPoint = cbMotoristaNaoParou.isChecked
        evaluationModel.cellPhoneDriver = cbMotoristaCelular.isChecked
        evaluationModel.driverStoppedAbruptly = cbMotoristaFreouBruscamente.isChecked
        evaluationModel.driverAboveSpeed = cbMotoristaVelocidade.isChecked
        evaluationModel.busWasRobbed = cbOnibusAssaltado.isChecked
        evaluationModel.actsVandalismBus = cbAtosVandalismo.isChecked
        evaluationModel.fightsBus = cbBrigasOnibus.isChecked
        evaluationModel.busBroke = cbOnibusQuebrou.isChecked
        evaluationModel.longCourse = cbPercursoLongo.isChecked
        evaluationModel.ariscatedAreas = cbAreasAriscadas.isChecked
        evaluationModel.lineFrequentlyAssaulted = cbOnibusLinhaAssaltados.isChecked
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