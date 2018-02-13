package br.com.harlan.avaliabus.view.services

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.annotation.NonNull
import android.support.v7.app.AlertDialog
import android.widget.Toast
import br.com.harlan.avaliabus.R
import br.com.harlan.avaliabus.business.services.MessageServiceable

class MessageService : MessageServiceable {

    //region Variables and Constructor
    var activity: Activity
    private lateinit var progressDialog: ProgressDialog
    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    constructor(@NonNull activity: Activity) {
        this.activity = activity
    }
    //endregion Variables and Constructor

    override fun newToast(mensagem: String) {
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show()
    }

    override fun newProgressDialog(titulo: String, mensagem: String) {
        progressDialog = ProgressDialog.show(activity, titulo, mensagem)
        progressDialog.setIcon(R.drawable.icon_avalia_bus)
        progressDialog.setCancelable(false)
    }

    override fun updateMessageProgressDialog(mensagem: String) {
        progressDialog.setMessage(mensagem)
    }

    override fun closeProgressDialog() {
        progressDialog.dismiss()
    }

    override fun newAlert(titulo: String, mensagem: String) {
        builder = AlertDialog.Builder(activity)
        builder.setTitle(titulo)
        builder.setMessage(mensagem)
        builder.setPositiveButton("Fechar", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        alertDialog = builder.create()
        alertDialog.show()
    }

    override fun newAlertWithAnswer(titulo: String, mensagem: String, positiveButton: String, negativeButton: String, withAnswer: MessageServiceable.WithAnswer) {
        builder = AlertDialog.Builder(activity!!)
        builder.setTitle(titulo)
        builder.setMessage(mensagem)
        builder.setPositiveButton(positiveButton, DialogInterface.OnClickListener { dialogInterface, i ->
            withAnswer.answer(true)
        })
        builder.setNegativeButton(negativeButton, DialogInterface.OnClickListener { dialogInterface, i ->
            withAnswer.answer(false)
        })
        alertDialog = builder.create()
        alertDialog.show()
    }
}