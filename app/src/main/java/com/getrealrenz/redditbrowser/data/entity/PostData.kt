package com.getrealrenz.redditbrowser.data.entity

import com.google.gson.annotations.SerializedName

class PostData {
    var id = ""
    @SerializedName("subreddit_name_prefixed")
    var subreddit = ""
    @SerializedName("author_fullname")
    var authorName = ""
    @SerializedName("num_comments")
    var comments = 0
    var selfText = ""
    var title = ""
    var ups = 0
    var downs = 0
    var url = ""
    var thumbnail = ""
    var created: Long = 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostData

        if (id != other.id) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }

}
