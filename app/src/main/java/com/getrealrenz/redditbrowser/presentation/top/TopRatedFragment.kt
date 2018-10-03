package com.getrealrenz.redditbrowser.presentation.top


import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.databinding.FragmentTopRatedBinding
import com.getrealrenz.redditbrowser.presentation.base.BaseFragment
import com.getrealrenz.redditbrowser.presentation.top.common.EndlessScrollListener
import com.getrealrenz.redditbrowser.presentation.top.common.TopRatedAdapter
import javax.inject.Inject
import io.reactivex.processors.PublishProcessor


class TopRatedFragment : BaseFragment<FragmentTopRatedBinding>(), TopRatedContract.View {


    @Inject
    lateinit var topRatedPresenter: TopRatedContract.Presenter
    private lateinit var topRatedAdapter: TopRatedAdapter
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var loadMoreListener: EndlessScrollListener


    override fun viewCreated() {
        binding.apply {
            layoutManager = LinearLayoutManager(context)
            rvPostsContainer.layoutManager = layoutManager
            topRatedAdapter = TopRatedAdapter(eventListener = topRatedPresenter)
            rvPostsContainer.adapter = topRatedAdapter
        }
        topRatedPresenter.attachView(this)
        topRatedPresenter.getTopRated()
    }

    override fun onDestroy() {
        super.onDestroy()
        topRatedPresenter.detachView()
    }

    override fun showTopRated(topRated: List<PostData?>) {
        topRatedAdapter.setData(topRated.toMutableList())
        loadMoreListener.cancelLoading()
    }

    override fun setPaginator(paginator: PublishProcessor<Int>) {
        loadMoreListener = EndlessScrollListener(layoutManager, paginator)
        binding.rvPostsContainer.addOnScrollListener(loadMoreListener)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = TopRatedFragment()

    }
}
