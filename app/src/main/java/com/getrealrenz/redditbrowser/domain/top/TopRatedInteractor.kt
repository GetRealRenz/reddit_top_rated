package com.getrealrenz.redditbrowser.domain.top

import com.getrealrenz.redditbrowser.data.repository.top.TopRatedRepository
import javax.inject.Inject

class TopRatedInteractor @Inject constructor(private var topRatedRepository: TopRatedRepository) {
    fun getTopRated(limit: Int, count: Int) = topRatedRepository.getTopRated(limit, count)
}
