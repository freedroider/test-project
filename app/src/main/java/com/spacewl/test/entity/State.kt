package com.spacewl.test.entity

sealed class State {
    object Default : State()
    object Done : State()
    object Deleted : State()
}