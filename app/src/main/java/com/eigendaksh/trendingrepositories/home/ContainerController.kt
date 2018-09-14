package com.eigendaksh.trendingrepositories.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.base.BaseController
import com.eigendaksh.trendingrepositories.screens.RepoAdapter
import com.eigendaksh.trendingrepositories.screens.TrendingReposViewModel
import com.eigendaksh.trendingrepositories.screens.javarepos.TrendingJavaReposPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ContainerController : BaseController() {

    @Inject lateinit var presenter: TrendingJavaReposPresenter
    @Inject lateinit var viewModel: TrendingReposViewModel

    @BindView(R.id.repoList)
    lateinit var repoList: RecyclerView
    @BindView(R.id.loadingIndicator)
    lateinit var loadingView: View
    @BindView(R.id.errorTV)
    lateinit var errorText: TextView

    override fun layoutRes(): Int  = R.layout.screen_trending_repos

    override fun onViewBound(view: View) {
        repoList.layoutManager = LinearLayoutManager(view.context)
        repoList.adapter = RepoAdapter(presenter)
    }

    override fun subscriptions(): Array<Disposable> {
        return arrayOf(
                viewModel.getLoading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            loadingView.visibility = if (it) View.VISIBLE else View.GONE
                            repoList.visibility = if (it) View.GONE else View.VISIBLE
                            errorText.visibility = if (it) View.GONE else errorText.visibility
                        },
                viewModel.getRepos()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((repoList.adapter as RepoAdapter)::setData),
                viewModel.getError()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            if(it == -1) {
                                errorText.text = null
                                errorText.visibility = View.GONE
                            } else {
                                errorText.setText(it)
                                errorText.visibility = View.VISIBLE
                                repoList.visibility = View.GONE
                            }
                        }
        )
    }
}