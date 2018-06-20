package ginyolith.aboutme.application

import android.app.Application
import ginyolith.aboutme.data.AboutMeDatabase

class CustomApplication : Application() {
    lateinit var database : AboutMeDatabase

    override fun onCreate() {
        super.onCreate()
        database = AboutMeDatabase.getInstance(this)
    }

}
