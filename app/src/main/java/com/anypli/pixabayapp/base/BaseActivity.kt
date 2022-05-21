package com.anypli.pixabayapp.base

import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.anypli.pixabayapp.global.helper.DetachableClickHelper

abstract class BaseActivity : AppCompatActivity() {


    private var alertDialog: AlertDialog? = null

    protected fun registerBaseObservers(viewModel: ViewModel) {
        if (viewModel is BaseAndroidViewModel) {
            registerChoseDialog(viewModel)

            //register other BaseObservers (navigation, Toast, ProgressBar, snackBar...)

        }
    }


    private fun registerChoseDialog(viewModel: BaseAndroidViewModel) {
        viewModel.chooseDialog.observe(
            this
        ) { it ->
            it?.let {
                showChooseDialog(
                    it.title,
                    it.message,
                    it.ok,
                    it.cancel,
                    it.okActionBlock,
                    it.cancelActionBlock,
                    it.dismissActionBlock
                )
            }
        }
    }



    /**
     * show choose dialog */
    fun showChooseDialog(
        @StringRes titleId: Int? = null, @StringRes messageId: Int, @StringRes okId: Int,
        @StringRes cancelId: Int, okActionBlock: (() -> Unit)? = null,
        cancelActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)? = null
    ) {
        if (!isFinishing) {
            val title = titleId?.let { getString(it) }
            showChooseDialog(
                title,
                getString(messageId),
                getString(okId),
                getString(cancelId),
                okActionBlock,
                cancelActionBlock,
                dismissActionBlock
            )
        }
    }
    fun showChooseDialog(
        title: String? = null,
        message: String,
        ok: String,
        cancel: String,
        okActionBlock: (() -> Unit)? = null,
        cancelActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)? = null
    ) {
        if (!isFinishing) {
            val clickListener =
                getWrapperClick(okActionBlock, cancelActionBlock, dismissActionBlock)
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(okActionBlock == null)
                .setMessage(message)
                .setPositiveButton(ok, clickListener)
                .setNegativeButton(cancel, clickListener)
                .setOnDismissListener(clickListener)
            title?.let { builder.setTitle(it) }
            alertDialog = builder.create().apply {
                show()
            }
        }
    }

    /**
     * get dialog click listener*/
    @NonNull
    private fun getWrapperClick(
        okActionBlock: (() -> Unit)?,
        cancelActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)?

    ): DetachableClickHelper {
        return DetachableClickHelper.wrapClick(okActionBlock, cancelActionBlock, dismissActionBlock)
    }

    /**
     * hide dialog if its showing*/
    private fun hideSimperDialog() {
        if (alertDialog?.isShowing == true) {
            alertDialog?.dismiss()
            alertDialog = null
        }
    }

    override fun onDestroy() {
        hideSimperDialog()
        super.onDestroy()
    }



}