package br.com.harlan.avaliabus.database

import br.com.harlan.avaliabus.database.services.TaskServiceable
import br.com.harlan.avaliabus.factory.CityFactory
import br.com.harlan.avaliabus.model.CityModel

class CityDatabase : BaseDatabase {

    constructor(taskService: TaskServiceable) : super(taskService)

    fun getCities(): ArrayList<CityModel> {
        val cityModels: ArrayList<CityModel> = ArrayList()
        cityModels.add(CityFactory("Salvador", "BA").getObject() as CityModel)
        cityModels.add(CityFactory("Simões Filho", "BA").getObject() as CityModel)
        cityModels.add(CityFactory("Lauro de Freitas", "BA").getObject() as CityModel)
        return cityModels
    }
}