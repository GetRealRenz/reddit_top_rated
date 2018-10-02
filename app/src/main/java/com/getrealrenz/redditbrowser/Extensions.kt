package com.getrealrenz.redditbrowser

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
        is Class<*> -> (jvmType.enclosingClass?.simpleErasedName()?.plus(".") ?: "") + jvmType.simpleName
        is ParameterizedType -> jvmType.rawType.simpleErasedName()
        is GenericArrayType -> jvmType.genericComponentType.simpleErasedName()
        is WildcardType -> "*"
        is TypeVariable<*> -> jvmType.name
        else -> throw IllegalArgumentException("Unknown type $javaClass $this")
    }
}