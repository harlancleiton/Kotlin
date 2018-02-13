package br.com.harlan.alcoolougasolina.business

class AlcoolOuGasolinaBusiness{
    fun calcularRazao(precoGasolina: Double, precoAlcool: Double): String{
        if(melhorGasolina(precoGasolina, precoAlcool)==CodigoCombustivel.GASOLINA_CODE){
            return "Gasolina"
        }
        else if (melhorGasolina(precoGasolina, precoAlcool)==CodigoCombustivel.ALCOOL_CODE){
            return "Álcool"
        }
        else{
            return "Algo deu errado ao calcular"
        }
    }

    private fun melhorGasolina(precoGasolina: Double, precoAlcool: Double): Int {
        if(precoAlcool/precoGasolina>=0.7)
            return CodigoCombustivel.GASOLINA_CODE;
        else
            return CodigoCombustivel.ALCOOL_CODE
    }
}