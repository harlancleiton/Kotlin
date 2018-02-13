package br.com.harlan.avaliabus.business

import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.database.CityDatabase
import br.com.harlan.avaliabus.model.CityModel

class CityBusiness : BaseBusiness {

    constructor(messageService: MessageServiceable, navigationService: NavigationServiceable)
            : super(messageService, navigationService)

    fun getCities(): ArrayList<String> {
        val cityModels: ArrayList<CityModel> = CityDatabase(taskService).getCities()
        val nameCity: ArrayList<String> = ArrayList()
        for (cityModel: CityModel in cityModels) {
            nameCity.add(cityModel.name)
        }
        return nameCity
    }

}