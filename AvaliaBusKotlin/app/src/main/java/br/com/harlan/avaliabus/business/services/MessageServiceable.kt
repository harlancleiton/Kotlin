package br.com.harlan.avaliabus.business.services

interface MessageServiceable {
    fun newToast(mensagem: String)
    fun newProgressDialog(titulo: String, mensagem: String)
    fun updateMessageProgressDialog(mensagem: String)
    fun closeProgressDialog()
    fun newAlert(titulo: String, mensagem: String)
    fun newAlertWithAnswer(titulo: String, mensagem: String, positiveButton: String, negativeButton: String, withAnswer: WithAnswer)

    interface WithAnswer{
        fun answer(action: Boolean)
    }
}