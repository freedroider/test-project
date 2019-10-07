package com.spacewl.test.utils

import com.spacewl.test.entity.State
import com.spacewl.test.entity.SyncStatus
import com.spacewl.test.entity.ToDo

object ToDoHelper {

    fun status(toDo: ToDo) = when {
        toDo.state == State.Done && toDo.syncStatus == SyncStatus.Synced -> "Done"
        toDo.state == State.Done && toDo.syncStatus == SyncStatus.NonSynced -> "Syncing"
        toDo.state == State.Deleted && toDo.syncStatus == SyncStatus.Synced -> "Deleted"
        toDo.state == State.Deleted && toDo.syncStatus == SyncStatus.NonSynced -> "Deleting"
        else -> "Default status"
    }
}