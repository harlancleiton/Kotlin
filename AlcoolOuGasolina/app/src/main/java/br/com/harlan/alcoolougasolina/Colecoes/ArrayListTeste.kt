package br.com.harlan.alcoolougasolina.Colecoes

/**
 List -> Imutável, tamanho fixo, apenas leitura
 ArrayList -> Mutável, tamanho dinâmico, pode adicionar e remover itens
 */
class ArrayListTeste {
    var listaItens = listOf<String>("BA","SP","RJ")
    var listaArrayItens = arrayListOf<String>("BA","SP","RJ")
    init {
        //Erro na linha abaixo, List imutável
        //listaItens[0] = "RJ"
        listaArrayItens.add("RS")
        listaArrayItens.remove("SP")
    }
}