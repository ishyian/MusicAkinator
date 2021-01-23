package codeninjas.musicakinator.other.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    fun Disposable.tracked() {
        compositeDisposable.add(this)
    }

    private fun unsubscribeAll() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.isDisposed
            compositeDisposable.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribeAll()
    }



}