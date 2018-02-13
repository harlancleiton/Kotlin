package br.com.harlan.avaliabus.factory

import br.com.harlan.avaliabus.model.BaseModel

abstract class BaseFactory {
    abstract fun getObject(): BaseModel
}