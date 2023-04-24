package com.example.productapp.ui.helper

import android.app.AlertDialog
import androidx.fragment.app.Fragment

class PopUp(val fragment: Fragment) {

    fun showErrorPopup(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(fragment.requireContext())
        alertDialogBuilder.setTitle("Error")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}