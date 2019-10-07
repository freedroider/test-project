package com.spacewl.test.entity

sealed class SyncStatus {
    object NonSynced : SyncStatus()
    object Synced : SyncStatus()
}