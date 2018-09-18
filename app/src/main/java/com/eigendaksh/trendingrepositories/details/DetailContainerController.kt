package com.eigendaksh.trendingrepositories.details

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.bluelinelabs.conductor.Controller
import com.eigendaksh.trendingrepositories.R
import com.eigendaksh.trendingrepositories.base.BaseController
import com.eigendaksh.trendingrepositories.utils.REPO_NAME_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_OWNER_KEY
import com.eigendaksh.trendingrepositories.utils.REPO_TYPE_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class DetailContainerController(bundle: Bundle) : BaseController(bundle) {

    @Inject lateinit var repoDetailsViewModel: RepoDetailsViewModel
    @Inject lateinit var repoDetailsPresenter: RepoDetailsPresenter

    @BindView(R.id.repoName) lateinit var repoNameText: TextView
    @BindView(R.id.repoDescription) lateinit var repoDescriptionText: TextView
    @BindView(R.id.creationDate) lateinit var createdDateText: TextView
    @BindView(R.id.updatedDate) lateinit var updatedDateText: TextView
    @BindView(R.id.contributorList) lateinit var contributorList: RecyclerView
    @BindView(R.id.loadingIndicator) lateinit var detailsLoadingView: View
    @BindView(R.id.contributorsLoadingIndicator) lateinit var contributorsLoadingView: View
    @BindView(R.id.content) lateinit var contentContainer: View
    @BindView(R.id.tvError) lateinit var errorText: TextView
    @BindView(R.id.tvcontributorsError) lateinit var contributorsErrorText: TextView

    override fun layoutRes(): Int = R.layout.screen_repo_details

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        contributorList.layoutManager = LinearLayoutManager(view.context)
        contributorList.adapter = ContributorAdapter()

        Timber.d("Repo type ${RepoType.values()[args.getInt(REPO_TYPE_KEY)].name}")
        Timber.d("Repo owner ${args.getString(REPO_OWNER_KEY)}")
        Timber.d("Repo name ${args.getString(REPO_NAME_KEY)}")
    }

    override fun subscriptions(): Array<Disposable> =
            arrayOf(
                    repoDetailsViewModel.details()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {

                                if (it.loading) {
                                    detailsLoadingView.visibility = View.VISIBLE
                                    contentContainer.visibility = View.GONE
                                    errorText.visibility = View.GONE
                                    errorText.text = null
                                } else {
                                    if (it.isSuccess()) {
                                        errorText.text = null
                                    } else {
                                        errorText.setText(it.errorRes)
                                    }
                                    detailsLoadingView.visibility = View.GONE
                                    contentContainer.visibility = if (it.isSuccess()) View.VISIBLE else View.GONE
                                    errorText.visibility = if (it.isSuccess()) View.GONE else View.VISIBLE
                                    repoNameText.setText(it.name)
                                    repoDescriptionText.setText(it.description)
                                    createdDateText.setText(it.createdDate)
                                    updatedDateText.setText(it.updatedDate)
                                }

                            },
                    repoDetailsViewModel.contributors()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {

                                if (it.loading) {
                                    contributorsLoadingView.visibility = View.VISIBLE
                                    contributorList.visibility = View.GONE
                                    contributorsErrorText.visibility = View.GONE
                                    contributorsErrorText.text = null
                                } else {
                                    contributorsLoadingView.visibility = View.GONE
                                    contributorList.visibility = if (it.isSuccess()) View.VISIBLE else View.GONE
                                    contributorsErrorText.visibility = if (it.isSuccess()) View.GONE else View.VISIBLE
                                    if (it.isSuccess()) {
                                        contributorsErrorText.text = null
                                        (contributorList.adapter as ContributorAdapter).setData(it.contributors)
                                    } else {
                                        contributorsErrorText.setText(it.errorRes)
                                    }
                                }

                            }
            )

    companion object {

        fun newInstance(repoTypeOrdinal: Int, repoOwner: String, repoName: String): Controller {
            val bundle = Bundle()
            bundle.putInt(REPO_TYPE_KEY, repoTypeOrdinal)
            bundle.putString(REPO_OWNER_KEY, repoOwner)
            bundle.putString(REPO_NAME_KEY, repoName)
            return DetailContainerController(bundle)
        }

    }
}