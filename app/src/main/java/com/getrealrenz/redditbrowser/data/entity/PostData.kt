package com.getrealrenz.redditbrowser.data.entity

import com.google.gson.annotations.SerializedName
import java.util.concurrent.TimeUnit

class PostData {
    var name = ""
    @SerializedName("subreddit_name_prefixed")
    var subreddit = ""
    @SerializedName("author")
    var authorName = ""
    @SerializedName("num_comments")
    var comments = 0
    var title = ""
    @SerializedName("score")
    var ups = 0
    var url = ""
    var thumbnail = ""
    var created: Long = 0
        get() = TimeUnit.SECONDS.toMillis(field)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostData

        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }
}
