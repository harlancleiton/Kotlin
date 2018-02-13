package br.com.harlan.alcoolougasolina.Colecoes

/**
 Set -> Não permite elementos duplicados, mais rápido que ArrayList, não mantém a ordem dos elementos
 Map -> É uma implementação chave/valor
 */
class SetMapTeste {
    var itensSet = hashSetOf("BA","SP","RJ","SP")
    var itensMap = hashMapOf("BA" to "Bahia","SP" to "São Paulo","RJ" to "Rio de Janeiro"
            ,"SP" to "São Paulo")

    init {
        for (item in itensSet){
            println(item)
        }
        println(itensSet) //Não mantem a ordem dos elementos

        itensMap.put("MG", "Minas Gerais") //Add do ArrayList
        for(item in itensMap.values){//itensMap.key se quiser só a chave, itensMap.value se quiser só o valor
            println(item)
        }
        itensMap.remove("SP")
        println(itensMap)
    }
}