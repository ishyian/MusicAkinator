package codeninjas.musicakinator.other.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import codeninjas.musicakinator.other.custom.extensions.showAlertMessage
import codeninjas.musicakinator.other.custom.extensions.showErrorMessage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {

    private val compositeDisposable = CompositeDisposable()

    private val dialogsList: MutableList<Dialog> = mutableListOf()

    private var dialog: ProgressDialog? = null

    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = null
        view = inflater.inflate(layoutRes, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView(view, savedInstanceState)
    }

    abstract fun renderView(view: View, savedInstanceState: Bundle?)


    open fun showMessage(message: String){
        showAlertMessage(message)
    }

    open fun showError(throwable: Throwable, action: (() -> Unit?)? = null){
        showErrorMessage(throwable, action)
    }


    open fun showProgress() {
        dialog?.dismiss()
        dialog = ProgressDialog.newInstance()
        dialog?.show(childFragmentManager, ProgressDialog.NAME_FRAGMENT)
    }

    open fun hideProgress() {
        dialog!!.dismiss()
    }

    private fun Dialog.registerDialog() {
        dialogsList.add(this)
    }

    private fun dismissAndClearDialogs() {
        for (dialog in dialogsList) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        dialogsList.clear()
    }

    fun Disposable.tracked() {
        compositeDisposable.add(this)
    }

    private fun unsubscribeAll() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unsubscribeAll()
    }
}