package com.stock.market.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stock.market.BaseFragment
import com.stock.market.R
import com.stock.market.domain.model.News
import com.stock.market.ui.home.IHomeActivity

class NewsFragment: BaseFragment(), OnNewsClickListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: com.stock.market.databinding.FragmentNewsBinding
    private var adapterNews: NewsAdapter = NewsAdapter(this)
    lateinit var callbackHomeActivity: IHomeActivity

    override fun getLayout(): Int {
        return R.layout.fragment_news
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.indicatorMarketFilter.setListener(this)

        binding.newsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_list)!!)
        binding.newsList.addItemDecoration(dividerItemDecoration)
        binding.newsSwipeRefresh.setOnRefreshListener(this)
        callbackHomeActivity.getNewsViewModel().resultNews.observe(viewLifecycleOwner, {
            binding.newsSwipeRefresh.isRefreshing = false
            binding.newsProgressBar.visibility = View.GONE
            setNewsList(it.news!!.asList())
        })

    }

    private fun setNewsList(news: List<News>) {
        binding.newsList.adapter = adapterNews
        adapterNews.updateNewsList(news)

    }

    override fun defineAdditionalCallbacks(context: Context) {
        callbackHomeActivity = context as IHomeActivity
    }

    override fun onRefresh() {
        callbackHomeActivity.getNewsViewModel().getNews("en")
    }

    override fun openNews(news: News) {

    }

}