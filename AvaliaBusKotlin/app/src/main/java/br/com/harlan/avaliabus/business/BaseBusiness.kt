package br.com.harlan.avaliabus.business

import br.com.harlan.avaliabus.business.services.MessageServiceable
import br.com.harlan.avaliabus.business.services.NavigationServiceable
import br.com.harlan.avaliabus.business.services.TaskService
import br.com.harlan.avaliabus.database.Directories
import br.com.harlan.avaliabus.singleton.TaskServiceSingleton

abstract class BaseBusiness : Directories {

    val taskService: TaskService
    val messageService: MessageServiceable
    val navigationService: NavigationServiceable

    constructor(messageService: MessageServiceable, navigationService: NavigationServiceable) {
        taskService = TaskServiceSingleton.getInstance(messageService, navigationService)
        this.navigationService = navigationService
        this.messageService = messageService
    }

    constructor(taskService: TaskService) {
        this.taskService = taskService
        this.navigationService = taskService.navigationService
        this.messageService = taskService.messageService
    }
}