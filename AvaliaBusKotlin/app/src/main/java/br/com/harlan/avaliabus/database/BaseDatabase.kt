package br.com.harlan.avaliabus.database

import br.com.harlan.avaliabus.database.services.TaskServiceable

abstract class BaseDatabase(taskService: TaskServiceable) : Directories {
    val taskService: TaskServiceable = taskService
}