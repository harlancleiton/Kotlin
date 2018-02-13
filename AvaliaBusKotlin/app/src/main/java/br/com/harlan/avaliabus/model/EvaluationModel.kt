package br.com.harlan.avaliabus.model

import com.google.firebase.database.Exclude

class EvaluationModel : BaseModel() {

    //region Data
    var date: String = ""
    var hour: String = ""
    var city: String = ""
    var comments: String = ""
    var busNumber: Int = 0
    var lineNumber: Int = 0
    //endregion Data

    //region RatingBar
    var busNote: Float = 0.toFloat()
    var driverNote: Float = 0.toFloat()
    var securityNote: Float = 0.toFloat()
    var lineNote: Float = 0.toFloat()
    //endregion RatingBar

    //region CheckBox
    var crowdedBus: Boolean = false
    var dirtyBus: Boolean = false
    var windowDamaged: Boolean = false
    var brokenSeat: Boolean = false
    var brokenLamp: Boolean = false
    var driverDidNotStopPoint: Boolean = false
    var cellPhoneDriver: Boolean = false
    var driverStoppedAbruptly: Boolean = false
    var driverAboveSpeed: Boolean = false
    var busWasRobbed: Boolean = false
    var actsVandalismBus: Boolean = false
    var fightsBus: Boolean = false
    var busBroke: Boolean = false
    var longCourse: Boolean = false
    var ariscatedAreas: Boolean = false
    var lineFrequentlyAssaulted: Boolean = false
    //endregion CheckBox

    //region Image
    var imageModelArrayList: ArrayList<ImageModel> = ArrayList()
        @Exclude
        get() = field
    //endregion Image
}