package codeninjas.musicakinator.ui.base

import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {
    fun showProgress()
    fun hideProgress()
    fun showError(throwable: Throwable, action: (() -> Unit?)? = null)
    fun showMessage(message: String)

}