package br.com.harlan.avaliabus.database

import br.com.harlan.avaliabus.database.services.TaskServiceable
import br.com.harlan.avaliabus.model.EvaluationModel
import br.com.harlan.avaliabus.singleton.FirebaseSingleton
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class EvaluationDatabase : BaseDatabase, Directories {

    val EDIT_EVALUATION_ACTION: Int = 0
    val REGISTER_EVALUATION_ACTION: Int = 1

    constructor(taskService: TaskServiceable) : super(taskService)

    fun registerEvaluation(evaluationModel: EvaluationModel) {
        saveToDatabase(evaluationModel, REGISTER_EVALUATION_ACTION)
    }

    fun editEvaluation(evaluationModel: EvaluationModel) {
        saveToDatabase(evaluationModel, EDIT_EVALUATION_ACTION)
    }

    private fun saveToDatabase(evaluationModel: EvaluationModel, action: Int) {
        var databaseReference: DatabaseReference = FirebaseSingleton.getDatabaseReference()!!
        if (action == REGISTER_EVALUATION_ACTION && evaluationModel.objectId.equals(""))
            evaluationModel.objectId = databaseReference.push().key
        databaseReference.child(evaluationModel.classChild).child(evaluationModel.userObjectId)
                .child(evaluationModel.objectId).setValue(evaluationModel)
                .addOnCompleteListener { task: Task<Void> ->
                    if (action == REGISTER_EVALUATION_ACTION)
                        taskService.evaluationSent(task)
                    else if (action == EDIT_EVALUATION_ACTION)
                        taskService.editedEvaluation(task)
                }
    }

    fun retrieveReviews() {
        val evaluationModels: ArrayList<EvaluationModel> = ArrayList<EvaluationModel>()
        val databaseReference: DatabaseReference = EVALUATION_CHILD_COMPLETE
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                taskService.dataChange()
                var evaluationModel: EvaluationModel
                for (result in dataSnapshot.getChildren()) {
                    evaluationModel = result.getValue(EvaluationModel::class.java)!!
                    evaluationModels.add(evaluationModel)
                }
                taskService.recoveredRatings(evaluationModels)
            }

            override fun onCancelled(p0: DatabaseError?) {
                if (p0 != null)
                    taskService.errorRetrieveReviews(p0.toException())
            }
        })
    }

    fun deleteReview(evaluationModel: EvaluationModel) {
        val databaseReference: DatabaseReference = EVALUATION_CHILD_COMPLETE
        databaseReference.child(evaluationModel.objectId).setValue(null)
    }
}