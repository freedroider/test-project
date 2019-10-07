package com.spacewl.test.data.remote

import com.spacewl.test.entity.State
import com.spacewl.test.entity.SyncStatus
import com.spacewl.test.entity.ToDo
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RemoteManagerImpl : RemoteManager {
    override fun delete(item: ToDo): Single<ToDo> {
        return Single.just(item.copy(state = State.Deleted, syncStatus = SyncStatus.Synced))
            .delay(2, TimeUnit.SECONDS)
    }

    override fun add(item: ToDo): Single<ToDo> {
        return Single.just(item.copy(state = State.Done, syncStatus = SyncStatus.Synced))
            .delay(2, TimeUnit.SECONDS)
    }
}
