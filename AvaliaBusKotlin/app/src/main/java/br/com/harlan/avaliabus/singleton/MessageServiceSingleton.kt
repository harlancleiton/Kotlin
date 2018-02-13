package br.com.harlan.avaliabus.singleton

import android.app.Activity
import br.com.harlan.avaliabus.view.services.MessageService

object MessageServiceSingleton {

    private var messageService: MessageService? = null

    fun getInstance(activity: Activity): MessageService {
        if (messageService == null)
            messageService = MessageService(activity)
        else{
            messageService!!.activity = activity
        }
        return messageService as MessageService
    }
}