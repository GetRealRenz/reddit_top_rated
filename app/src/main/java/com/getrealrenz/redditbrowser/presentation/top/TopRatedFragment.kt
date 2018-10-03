package com.getrealrenz.redditbrowser.presentation.top


import android.support.v7.widget.LinearLayoutManager
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
    lateinit var topRatedAdapter: TopRatedAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var loadMoreListener:EndlessScrollListener
    private val paginator = PublishProcessor.create<Int>()

    override fun viewCreated() {
        topRatedPresenter.attachView(this)
        binding.apply {
            layoutManager = LinearLayoutManager(context)
            rvPostsContainer.layoutManager = layoutManager
            topRatedAdapter = TopRatedAdapter()
            rvPostsContainer.adapter = topRatedAdapter
            loadMoreListener = EndlessScrollListener(layoutManager)
            rvPostsContainer.addOnScrollListener(loadMoreListener)

        }
        topRatedPresenter.getTopRated()
    }

    override fun onDestroy() {
        super.onDestroy()
        topRatedPresenter.detachView()
    }

    override fun showTopRated(topRated: List<PostData?>) {
        topRatedAdapter.setData(topRated.toMutableList())
    }

    companion object {
        fun newInstance() = TopRatedFragment()

    }
}
