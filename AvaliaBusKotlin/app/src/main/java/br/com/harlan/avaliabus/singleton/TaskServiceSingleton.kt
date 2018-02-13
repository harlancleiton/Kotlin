package br.com.harlan.avaliabus.singleton

import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.business.services.TaskService


object TaskServiceSingleton {

    private var taskService: TaskService? = null

    fun getInstance(messageService: MessageServiceable, navigationService: NavigationServiceable): TaskService {
        if (taskService == null)
            taskService = TaskService(messageService, navigationService)
        else {
            taskService!!.messageService = messageService
            taskService!!.navigationService = navigationService
        }
        return taskService as TaskService
    }
}