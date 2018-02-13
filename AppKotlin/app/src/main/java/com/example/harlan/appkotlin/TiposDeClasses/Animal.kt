package com.example.harlan.appkotlin.TiposDeClasses

/**
 * Created by Harlan on 18/09/2017.
 */
abstract class Animal {
    abstract var cor: String
    abstract var tamanho: Double
    abstract var peso: Double
    abstract protected fun comer()
    abstract protected fun dormir(horasSono: Double)
}