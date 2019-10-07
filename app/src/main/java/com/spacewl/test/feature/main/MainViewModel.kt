package com.spacewl.test.feature.main

import android.util.ArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacewl.test.data.local.LocalManager
import com.spacewl.test.data.remote.RemoteManager
import com.spacewl.test.entity.ToDo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val localManager: LocalManager,
    private val remoteManager: RemoteManager
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val itemsLD: MutableLiveData<MutableMap<String, ToDo>> = MutableLiveData()

    init {
        itemsLD.value = ArrayMap()
    }

    fun insert() {
        val toDo = ToDo()
        val disposable = localManager.insert(toDo)
            .doOnSuccess { item ->
                itemsLD.value?.put(item.id, item)
                itemsLD.postValue(itemsLD.value)
            }
            .flatMap(remoteManager::add)
            .doOnSuccess { item ->
                itemsLD.value?.put(item.id, item)
                itemsLD.postValue(itemsLD.value)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun delete(toDo: ToDo) {
        val disposable = localManager.delete(toDo)
            .doOnSuccess { item ->
                itemsLD.value?.put(item.id, item)
                itemsLD.postValue(itemsLD.value)
            }
            .flatMap(remoteManager::delete)
            .doOnSuccess { item ->
                itemsLD.value?.remove(item.id)
                itemsLD.postValue(itemsLD.value)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}