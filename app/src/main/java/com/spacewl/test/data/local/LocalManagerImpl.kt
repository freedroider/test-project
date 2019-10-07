package com.spacewl.test.data.local

import com.spacewl.test.entity.State
import com.spacewl.test.entity.SyncStatus
import com.spacewl.test.entity.ToDo
import io.reactivex.Single

class LocalManagerImpl : LocalManager {
    override fun delete(item: ToDo) =
        Single.just(item.copy(state = State.Deleted, syncStatus = SyncStatus.NonSynced))

    override fun insert(item: ToDo) =
        Single.just(item.copy(state = State.Done, syncStatus = SyncStatus.NonSynced))
}