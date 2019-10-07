package com.spacewl.test.entity

import java.util.*

data class ToDo(
    val id: String = UUID.randomUUID().toString(),
    val title: String = UUID.randomUUID().toString(),
    val state: State = State.Default,
    val syncStatus: SyncStatus = SyncStatus.NonSynced
)