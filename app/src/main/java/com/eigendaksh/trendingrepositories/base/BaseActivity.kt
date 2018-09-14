package com.eigendaksh.trendingrepositories.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.di.Injector
import com.eigendaksh.trendingrepositories.di.ScreenInjector
import com.eigendaksh.trendingrepositories.ui.ScreenNavigator
import timber.log.Timber
import java.util.*
import javax.inject.Inject

private const val INSTANCE_ID_KEY = "instance_id_key"

abstract class BaseActivity: AppCompatActivity() {

    lateinit var instanceId: String
    private lateinit var router: Router
    @Inject lateinit var screenInjector: ScreenInjector
    @Inject lateinit var screenNavigator: ScreenNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        instanceId = savedInstanceState?.run {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        } ?: UUID.randomUUID().toString()

        Injector.inject(this)
        setContentView(layoutRes())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val screenContainer = findViewById<ViewGroup>(R.id.screen_container)
        screenContainer ?: throw NullPointerException("Activity must have a view with id: screen_container")
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        screenNavigator.initWithRouter(router, initialScreen())
        monitorBackStack()
        super.onCreate(savedInstanceState)
    }

    private fun monitorBackStack() {
        router.addChangeListener(object : ControllerChangeHandler.ControllerChangeListener {
            override fun onChangeStarted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler) {
            }

            override fun onChangeCompleted(
                    to: Controller?,
                    from: Controller?,
                    isPush: Boolean,
                    container: ViewGroup,
                    handler: ControllerChangeHandler) {
                if (!isPush && from != null) {
                    Injector.clearComponent(from)
                }
            }

        })
    }

    override fun onBackPressed() {
        if(!screenNavigator.pop()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    @LayoutRes protected abstract fun layoutRes(): Int

    abstract fun initialScreen() : Controller

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    override fun onDestroy() {
        super.onDestroy()
        screenNavigator.clear()
        if(isFinishing) {
            Injector.clearComponent(this)
        }
    }
}