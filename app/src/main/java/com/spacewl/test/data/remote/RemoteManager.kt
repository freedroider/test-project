package com.spacewl.test.data.remote

import com.spacewl.test.entity.ToDo
import io.reactivex.Single

interface RemoteManager {
    fun add(item: ToDo) : Single<ToDo>

    fun delete(item: ToDo) : Single<ToDo>
}