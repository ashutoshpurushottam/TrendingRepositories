package com.eigendaksh.trendingrepositories.home

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

import java.util.ArrayList

abstract class RouterPagerAdapter
/**
 * Creates a new RouterPagerAdapter using the passed host.
 */
(private val host: Controller) : PagerAdapter() {
    private var maxPagesToStateSave = Integer.MAX_VALUE
    internal var savedPages: SparseArray<Bundle>? = SparseArray()
        private set
    private val visibleRouters = SparseArray<Router>()
    private var savedPageHistory: ArrayList<Int>? = ArrayList()
    private var currentPrimaryRouter: Router? = null

    /**
     * Called when a router is instantiated. Here the router's root should be set if needed.
     *
     * @param router   The router used for the page
     * @param position The page position to be instantiated.
     */
    abstract fun configureRouter(router: Router, position: Int)

    /**
     * Sets the maximum number of pages that will have their states saved. When this number is exceeded,
     * the page that was state saved least recently will have its state removed from the save data.
     */
    fun setMaxPagesToStateSave(maxPagesToStateSave: Int) {
        if (maxPagesToStateSave < 0) {
            throw IllegalArgumentException("Only positive integers may be passed for maxPagesToStateSave.")
        }

        this.maxPagesToStateSave = maxPagesToStateSave

        ensurePagesSaved()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val name = makeRouterName(container.id, getItemId(position))

        val router = host.getChildRouter(container, name)
        if (!router.hasRootController()) {
            val routerSavedState = savedPages!!.get(position)

            if (routerSavedState != null) {
                router.restoreInstanceState(routerSavedState)
                savedPages!!.remove(position)
                savedPageHistory!!.remove(position)
            }
        }

        router.rebindIfNeeded()
        configureRouter(router, position)

        if (router !== currentPrimaryRouter) {
            for (transaction in router.backstack) {
                transaction.controller().setOptionsMenuHidden(true)
            }
        }

        visibleRouters.put(position, router)
        return router
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val router = `object` as Router

        val savedState = Bundle()
        router.saveInstanceState(savedState)
        savedPages!!.put(position, savedState)

        savedPageHistory!!.remove(position)
        savedPageHistory!!.add(position)

        ensurePagesSaved()

        host.removeChildRouter(router)

        visibleRouters.remove(position)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        val router = `object` as Router
        if (router !== currentPrimaryRouter) {
            if (currentPrimaryRouter != null) {
                for (transaction in currentPrimaryRouter!!.backstack) {
                    transaction.controller().setOptionsMenuHidden(true)
                }
            }
            if (router != null) {
                for (transaction in router.backstack) {
                    transaction.controller().setOptionsMenuHidden(false)
                }
            }
            currentPrimaryRouter = router
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        val router = `object` as Router
        val backstack = router.backstack
        for (transaction in backstack) {
            if (transaction.controller().view === view) {
                return true
            }
        }
        return false
    }

    override fun saveState(): Parcelable? {
        val bundle = Bundle()
        bundle.putSparseParcelableArray(KEY_SAVED_PAGES, savedPages)
        bundle.putInt(KEY_MAX_PAGES_TO_STATE_SAVE, maxPagesToStateSave)
        bundle.putIntegerArrayList(KEY_SAVE_PAGE_HISTORY, savedPageHistory)
        return bundle
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        val bundle = state as Bundle?
        if (state != null) {
            savedPages = bundle!!.getSparseParcelableArray(KEY_SAVED_PAGES)
            maxPagesToStateSave = bundle.getInt(KEY_MAX_PAGES_TO_STATE_SAVE)
            savedPageHistory = bundle.getIntegerArrayList(KEY_SAVE_PAGE_HISTORY)
        }
    }

    /**
     * Returns the already instantiated Router in the specified position or `null` if there
     * is no router associated with this position.
     */
    fun getRouter(position: Int): Router? {
        return visibleRouters.get(position)
    }

    fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private fun ensurePagesSaved() {
        while (savedPages!!.size() > maxPagesToStateSave) {
            val positionToRemove = savedPageHistory!!.removeAt(0)
            savedPages!!.remove(positionToRemove)
        }
    }

    companion object {

        private val KEY_SAVED_PAGES = "RouterPagerAdapter.savedStates"
        private val KEY_MAX_PAGES_TO_STATE_SAVE = "RouterPagerAdapter.maxPagesToStateSave"
        private val KEY_SAVE_PAGE_HISTORY = "RouterPagerAdapter.savedPageHistory"

        private fun makeRouterName(viewId: Int, id: Long): String {
            return viewId.toString() + ":" + id
        }
    }
}
