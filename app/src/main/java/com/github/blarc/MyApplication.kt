package com.github.blarc

import android.app.Application

internal class MyApplication : Application() {
    companion object {
        var curUserId = ""
    }
}