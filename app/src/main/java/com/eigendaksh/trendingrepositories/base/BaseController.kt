package com.eigendaksh.trendingrepositories.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.di.Injector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseController(bundle: Bundle? = null) : Controller(bundle) {

    private var injected = false

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var unbinder: Unbinder? = null

    override fun onContextAvailable(context: Context) {
        if (!injected) {
            Injector.inject(this)
            injected = true
        }
        super.onContextAvailable(context)
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(layoutRes(), container, false)
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        disposables.addAll(*subscriptions())
        return view
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        disposables.clear()
        unbinder?.run {
            unbind()
            unbinder = null
        }
    }

    protected open fun  onViewBound(view: View) {

    }

    protected open fun subscriptions() : Array<Disposable> = arrayOf()

    @LayoutRes protected abstract fun layoutRes(): Int

}