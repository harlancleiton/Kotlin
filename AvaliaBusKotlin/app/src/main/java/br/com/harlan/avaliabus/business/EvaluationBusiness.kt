package br.com.harlan.avaliabus.business

import br.com.harlan.avaliabus.adapter.HistoricRecyclerAdapter
import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.business.services.TaskService
import br.com.harlan.avaliabus.database.EvaluationDatabase
import br.com.harlan.avaliabus.model.EvaluationModel
import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import br.com.harlan.avaliabus.util.CurrentDateTime

class EvaluationBusiness : BaseBusiness {

    constructor(messageService: MessageServiceable, navigationService: NavigationServiceable)
            : super(messageService, navigationService)

    fun registerEvaluation(evaluationModel: EvaluationModel) {
        if (validateEvaluation(evaluationModel)) {
            evaluationModel.date = CurrentDateTime.currentDate
            evaluationModel.hour = CurrentDateTime.currentTime
            evaluationModel.classChild = EVALUATION_CHILD
            evaluationModel.userObjectId = CURRENT_USER_ID
            if (containsImages(evaluationModel)) {
                evaluationModel.objectId = FirebaseSingleton.getDatabaseReference()!!.push().key
                ImageBusiness(taskService).sendImage(evaluationModel.objectId, evaluationModel.imageModelArrayList, IMAGE_EVALUATION_UPLOAD_TYPE)
            }
            EvaluationDatabase(taskService).registerEvaluation(evaluationModel)
        }
    }

    private fun containsImages(evaluationModel: EvaluationModel): Boolean {
        return (evaluationModel.imageModelArrayList.size > 0)
    }

    fun editEvaluation(evaluationModel: EvaluationModel) {
        if (validateEvaluation(evaluationModel)) {
            evaluationModel.classChild = EVALUATION_CHILD
            evaluationModel.userObjectId = CURRENT_USER_ID
            EvaluationDatabase(taskService).editEvaluation(evaluationModel)
        } else
            taskService.newError("Avaliação inválida")
    }

    fun deleteReview(evaluationModel: EvaluationModel) {
        if (validateEvaluation(evaluationModel))
            EvaluationDatabase(taskService).deleteReview(evaluationModel)
        else
            taskService.newError("Avaliação inválida")
    }

    private fun validateEvaluation(evaluationModel: EvaluationModel): Boolean {
        return evaluationModel != null
    }

    fun retrieveReviews(evaluationModels: ArrayList<EvaluationModel>, historicAdapter: HistoricRecyclerAdapter, upgradeComponents: TaskService.UpgradeComponents) {
        taskService.waitForResult(historicAdapter, evaluationModels, upgradeComponents)
        EvaluationDatabase(taskService).retrieveReviews()
    }
}