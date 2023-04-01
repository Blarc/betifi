package com.github.blarc

import android.app.Application
import com.github.blarc.entity.User
import java.util.Optional

internal class MyApplication : Application() {
    companion object {
        var curUserId: String = ""
    }
}