package com.ac10.currencyexchange.ui.dialogs

import android.content.Context
import com.ac10.currencyexchange.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Dialogs {

    fun showErrorDialogWithRetry(
        context: Context,
        retryFunction: () -> Unit,
        closeFunction: () -> Unit,
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(context.resources.getString(R.string.error_title))
            .setMessage(context.resources.getString(R.string.error_message))

            .setPositiveButton(context.resources.getString(R.string.retry)) { _, _ ->
                retryFunction()
            }
            .setNegativeButton(context.resources.getString(R.string.close)) { _, _ ->
                closeFunction()
            }
            .show()
    }

    fun showSimpleErrorDialog(context: Context, message: String?) {
        showSimpleInfoDialog(
            context,
            context.resources.getString(R.string.error_title),
            message ?: context.resources.getString(R.string.error_title)
        )
    }

    private fun showSimpleInfoDialog(context: Context, title: String, message: String) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.resources.getString(R.string.ok)) { _, _ ->
                // Nothing to do here
            }
            .show()
    }

}
















