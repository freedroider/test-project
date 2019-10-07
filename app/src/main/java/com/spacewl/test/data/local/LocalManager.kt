package com.spacewl.test.data.local

import com.spacewl.test.entity.ToDo
import io.reactivex.Single

interface LocalManager {
    fun insert(item: ToDo) : Single<ToDo>

    fun delete(item: ToDo) : Single<ToDo>
}