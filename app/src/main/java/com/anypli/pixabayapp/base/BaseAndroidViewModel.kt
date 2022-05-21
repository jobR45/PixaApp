package com.anypli.pixabayapp.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anypli.pixabayapp.R
import com.anypli.pixabayapp.global.helper.dialog.ChooseDialogBuilder

@SuppressLint("StaticFieldLeak")
abstract class BaseAndroidViewModel
    (application: Application) : AndroidViewModel(application) {


    //application context for resource access only
    protected val applicationContext = application.applicationContext!!


    // live data for displaying choose dialog
    private val _choseDialog: MutableLiveData<ChooseDialogBuilder?> = MutableLiveData()
    val chooseDialog: LiveData<ChooseDialogBuilder?>
        get() = _choseDialog



    /**
     * show Choose dialog*/
    //using resource values
    fun showDialog(
        @StringRes titleId: Int? = null,
        @StringRes messageId: Int,
        @StringRes okId: Int,
        @StringRes cancelId: Int,
        okActionBlock: (() -> Unit)? = null,
        cancelActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)? = null
    ) {

        _choseDialog.value =
            ChooseDialogBuilder.build(
                applicationContext,
                titleId,
                messageId,
                okId,
                cancelId,
                okActionBlock,
                cancelActionBlock,
                dismissChooseBuild(dismissActionBlock)
            )
    }


    //using String values
    fun showDialog(
        title: String? = null,
        message: String,
        ok: String,
        cancel: String,
        okActionBlock: (() -> Unit)? = null,
        cancelActionBlock: (() -> Unit)? = null,
        dismissActionBlock: (() -> Unit)? = null
    ) {
        _choseDialog.value = ChooseDialogBuilder.build(
            title,
            message,
            ok,
            cancel,
            okActionBlock,
            cancelActionBlock,
            dismissChooseBuild(dismissActionBlock)
        )

    }

    /**
     * clear live data upon dismissing dialog*/
    private fun dismissChooseBuild(dismissActionBlock: (() -> Unit)? = null): () -> Unit {
        return {
            dismissActionBlock?.invoke()
            _choseDialog.value = null
        }
    }

    protected fun showDialogChoose(@StringRes messageId: Int, okActionBlock: (() -> Unit)?) {
        showDialog(
            messageId = messageId,
            okId = R.string.global_yes,
            cancelId = R.string.global_no,
            okActionBlock = okActionBlock
        )
    }
}