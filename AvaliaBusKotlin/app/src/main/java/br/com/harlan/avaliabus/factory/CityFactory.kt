package br.com.harlan.avaliabus.factory

import br.com.harlan.avaliabus.model.BaseModel
import br.com.harlan.avaliabus.model.CityModel

class CityFactory : BaseFactory {

    private var name: String
    private var state: String
    private var location: String

    constructor(name: String, state: String) : super() {
        this.name = name
        this.state = state
        location =  ""
    }

    constructor(name: String, state: String, location: String) : super() {
        this.name = name
        this.state = state
        this.location = location
    }

    override fun getObject(): BaseModel {
        val cityModel: CityModel = CityModel()
        cityModel.name = name
        cityModel.state = state
        cityModel.location = location
        return cityModel
    }
}