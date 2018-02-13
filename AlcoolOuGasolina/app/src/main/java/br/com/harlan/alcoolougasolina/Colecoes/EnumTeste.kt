package br.com.harlan.alcoolougasolina.Colecoes

/**
 * Enum(enumeração) - > É um tipo de dados que consiste em um conjuto de constantes
 */
enum class EnumTeste {
    CODIGO_CAMERA, CODIGO_GALERIA
}

class EnumTestando{
    lateinit var codigo: EnumTeste
}

class EnumPrincipal{
    var aux: EnumTestando = EnumTestando()
    init {
        aux.codigo=EnumTeste.CODIGO_GALERIA
        //aux.codigo=EnumTeste.CODIGO_CAMERA
        if(aux.codigo==EnumTeste.CODIGO_CAMERA)
            println("CODIGO_CAMERA")
        else if(aux.codigo==EnumTeste.CODIGO_GALERIA)
            println("CODIGO_GALERIA")
    }
}