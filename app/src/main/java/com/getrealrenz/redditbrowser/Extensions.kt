package com.getrealrenz.redditbrowser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.*


/**
 * Created on 02.10.2018.
 */
inline fun <reified T> Observable<T>.applyIOSchedulers(): Observable<T> =
        this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

inline fun <reified T> Single<T>.applyIOSchedulers(): Single<T> =
        this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

inline fun Completable.applyIOSchedulers(): Completable =
        this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Type.simpleErasedName(): String {
    val jvmType = this
    return when (jvmType) {
        is Class<*> -> (jvmType.enclosingClass?.simpleErasedName()?.plus(".")
                ?: "") + jvmType.simpleName
        is ParameterizedType -> jvmType.rawType.simpleErasedName()
        is GenericArrayType -> jvmType.genericComponentType.simpleErasedName()
        is WildcardType -> "*"
        is TypeVariable<*> -> jvmType.name
        else -> throw IllegalArgumentException("Unknown type $javaClass $this")
    }
}

@SuppressLint("CheckResult")
fun ImageView.load(url: String?, placeholderId: Int = -1) {
    if (url == null || url.isEmpty()) {
        visibility = View.GONE
    }
    val requestBuilder = Glide.with(this).load(url)
    val options = RequestOptions()
    options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    options.skipMemoryCache(false)
    options.error(R.drawable.ic_cancel)
    if (placeholderId > 0) {
        options.placeholder(placeholderId)
    }

    requestBuilder.apply(options)
    requestBuilder.into(this)
}