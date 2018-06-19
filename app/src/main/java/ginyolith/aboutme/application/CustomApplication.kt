package ginyolith.aboutme.application

import android.app.Application
import android.arch.persistence.room.Room.databaseBuilder
import ginyolith.aboutme.data.AboutMeDatabase
import ginyolith.aboutme.data.database_name

class CustomApplication : Application() {
    lateinit var database : AboutMeDatabase

    override fun onCreate() {
        super.onCreate()
        database = databaseBuilder(this, AboutMeDatabase::class.java, database_name)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

}
