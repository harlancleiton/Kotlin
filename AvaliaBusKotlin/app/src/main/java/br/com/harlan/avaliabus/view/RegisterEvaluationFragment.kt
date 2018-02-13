package br.com.harlan.avaliabus.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.adapter.ImageRecyclerAdapter
import br.com.harlan.avaliabus.business.CityBusiness
import br.com.harlan.avaliabus.business.EvaluationBusiness
import br.com.harlan.avaliabus.business.ImageBusiness
import br.com.harlan.avaliabus.model.BaseModel
import br.com.harlan.avaliabus.model.EvaluationModel
import br.com.harlan.avaliabus.model.ImageModel
import br.com.harlan.avaliabus.prototype.FragmentPrototype


class RegisterEvaluationFragment : BaseFragment(R.layout.fragment_register_evaluation), ImageRecyclerAdapter.ItemRemoved {

    //region Variables
    private lateinit var evaluationModel: EvaluationModel
    //Spinner
    private lateinit var spinnerCidades: AppCompatSpinner
    //EditText
    private lateinit var edtNumeroOnibus: AppCompatEditText
    private lateinit var edtNumeroLinha: AppCompatEditText
    private lateinit var edtObservacoes: AppCompatEditText
    //Button
    private lateinit var btnEnviar: AppCompatButton
    //Ratingbar
    private lateinit var rbOnibus: AppCompatRatingBar
    private lateinit var rbMotorista: AppCompatRatingBar
    private lateinit var rbSeguranca: AppCompatRatingBar
    private lateinit var rbLinha: AppCompatRatingBar
    //Checkbox
    private lateinit var cbOnibusSujo: AppCompatCheckBox
    private lateinit var cbJanelaDanificada: AppCompatCheckBox
    private lateinit var cbAssentoQuebrado: AppCompatCheckBox
    private lateinit var cbLampadaNaoAcende: AppCompatCheckBox
    private lateinit var cbMotoristaNaoParou: AppCompatCheckBox
    private lateinit var cbMotoristaCelular: AppCompatCheckBox
    private lateinit var cbMotoristaFreouBruscamente: AppCompatCheckBox
    private lateinit var cbMotoristaVelocidade: AppCompatCheckBox
    private lateinit var cbOnibusAssaltado: AppCompatCheckBox
    private lateinit var cbAtosVandalismo: AppCompatCheckBox
    private lateinit var cbBrigasOnibus: AppCompatCheckBox
    private lateinit var cbOnibusQuebrou: AppCompatCheckBox
    private lateinit var cbPercursoLongo: AppCompatCheckBox
    private lateinit var cbAreasAriscadas: AppCompatCheckBox
    private lateinit var cbOnibusLinhaAssaltados: AppCompatCheckBox
    //Image
    private lateinit var fabCaptureImage: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private var fileableArrayList: ArrayList<BaseModel.Fileable> = ArrayList()
    private lateinit var imageRecyclerAdapter: ImageRecyclerAdapter
    //endregion Variables

    //region MenuActionBar

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_refresh, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_refresh_registration -> {
                FragmentPrototype.replace(FragmentPrototype.REGISTER_FRAGMENT, RegisterEvaluationFragment())
                navigationService.loadFragment(FragmentPrototype.getFragment(FragmentPrototype.REGISTER_FRAGMENT))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //endregion MenuActionBar

    //region Functions
    override fun initializeComponents(viewRoot: View) {
        evaluationModel = EvaluationModel()
        spinnerCidades = viewRoot.findViewById(R.id.spinner_cidades_avaliar)
        val cityName: ArrayList<String> = CityBusiness(messageService, navigationService).getCities()
        val cityAdapter: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, cityName)
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCidades.adapter = cityAdapter
        edtNumeroOnibus = viewRoot.findViewById(R.id.edt_numero_onibus_avaliar)
        edtNumeroLinha = viewRoot.findViewById(R.id.edt_numero_linha_avaliar)
        edtObservacoes = viewRoot.findViewById(R.id.edt_observacoes_avaliar)
        btnEnviar = viewRoot.findViewById(R.id.btn_enviar_avaliacao)
        rbOnibus = viewRoot.findViewById(R.id.rb_onibus_avaliar)
        rbMotorista = viewRoot.findViewById(R.id.rb_motorista_avaliar)
        rbSeguranca = viewRoot.findViewById(R.id.rb_seguranca_avaliar)
        rbLinha = viewRoot.findViewById(R.id.rb_linha_avaliar)
        cbOnibusSujo = viewRoot.findViewById(R.id.cb_onibus_sujo_avaliar)
        cbJanelaDanificada = viewRoot.findViewById(R.id.cb_janela_quebrada_avaliar)
        cbAssentoQuebrado = viewRoot.findViewById(R.id.cb_assentos_quebrados_avaliar)
        cbLampadaNaoAcende = viewRoot.findViewById(R.id.cb_lampadas_queimadas_avaliar)
        cbMotoristaNaoParou = viewRoot.findViewById(R.id.cb_motorista_nao_parou_avaliar)
        cbMotoristaCelular = viewRoot.findViewById(R.id.cb_motorista_celular_avaliar)
        cbMotoristaFreouBruscamente = viewRoot.findViewById(R.id.cb_motorista_freou_bruscamente_avaliar)
        cbMotoristaVelocidade = viewRoot.findViewById(R.id.cb_motorista_velocidade_avaliar)
        cbOnibusAssaltado = viewRoot.findViewById(R.id.cb_onibus_assaltado_avaliar)
        cbAtosVandalismo = viewRoot.findViewById(R.id.cb_atos_vandalismo_avaliar)
        cbBrigasOnibus = viewRoot.findViewById(R.id.cb_brigas_onibus_avaliar)
        cbOnibusQuebrou = viewRoot.findViewById(R.id.cb_onibus_quebrou_avaliar)
        cbPercursoLongo = viewRoot.findViewById(R.id.cb_percurso_longo_avaliar)
        cbAreasAriscadas = viewRoot.findViewById(R.id.cb_linha_areas_arriscadas_avaliar)
        cbOnibusLinhaAssaltados = viewRoot.findViewById(R.id.cb_linha_assaltada_constantemente_avaliar)
        fabCaptureImage = viewRoot.findViewById(R.id.fab_capture_image_registrar)
        imageRecyclerAdapter = ImageRecyclerAdapter(context, fileableArrayList, this)
        recyclerView = viewRoot.findViewById(R.id.rc_fotos_avaliar)
        recyclerView.adapter = imageRecyclerAdapter
    }

    override fun addEvents() {
        btnEnviar.setOnClickListener {
            registerEvaluation()
        }
        fabCaptureImage.setOnClickListener {
            takePicture()
        }
    }

    //region CaptureImage
    private lateinit var imageModel: ImageModel

    private fun takePicture() {
        imageModel = ImageBusiness(messageService, navigationService).newImage()
        navigationService.dispatchTakePictureIntent(this, activity.packageManager, imageModel)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == navigationService.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            navigationService.saveGallery(this, imageModel)
            evaluationModel.imageModelArrayList.add(imageModel!!)
            fileableArrayList.add(imageModel)
            imageRecyclerAdapter.notifyDataSetChanged()
        }
    }
    //endregion CaptureImage

    private fun registerEvaluation() {
        try {
            recuperarDadosFormulario()
            EvaluationBusiness(messageService, navigationService).registerEvaluation(evaluationModel)
        } catch (e: Exception) {
            messageService.newToast("Ocorreu um erro ao tentar recuperar os dados preenchidos." + e.message)
        }
    }

    private fun recuperarDadosFormulario() {
        evaluationModel.comments = edtObservacoes.text.toString()
        evaluationModel.city = spinnerCidades.selectedItem.toString()
        evaluationModel.busNumber = Integer.parseInt(edtNumeroOnibus.text.toString())
        evaluationModel.lineNumber = Integer.parseInt(edtNumeroLinha.text.toString())
        evaluationModel.busNote = rbOnibus.rating
        evaluationModel.driverNote = rbMotorista.rating
        evaluationModel.securityNote = rbSeguranca.rating
        evaluationModel.lineNote = rbLinha.rating
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
    //endregion Functions

    //region ItemRemoved
    override fun removed(fileable: BaseModel.Fileable) {
        /*for (imageModel in evaluationModel.imageModelArrayList) {
            if (imageModel.pathFile!!.absolutePath == item) {
                val index = evaluationModel.imageModelArrayList.indexOf(imageModel)
                evaluationModel.imageModelArrayList.removeAt(index)
            }
        }*/
    }
    //endregion ItemRemoved
}