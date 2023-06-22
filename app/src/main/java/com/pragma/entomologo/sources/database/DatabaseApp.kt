package com.pragma.entomologo.sources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pragma.entomologo.sources.database.dao.CounterRecordInsectDao
import com.pragma.entomologo.sources.database.dao.EntomologistDao
import com.pragma.entomologo.sources.database.dao.GeoLocationDao
import com.pragma.entomologo.sources.database.dao.InsectDao
import com.pragma.entomologo.sources.database.entities.CounterRecordInsectEntity
import com.pragma.entomologo.sources.database.entities.EntomologistEntity
import com.pragma.entomologo.sources.database.entities.GeoLocationEntity
import com.pragma.entomologo.sources.database.entities.InsectEntity

/**
 * This class is in charge of managing the database management.
 * Documentation in
 * https://developer.android.com/training/data-storage/room?hl=es-419
 *
 * Best practices:
 * https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
 *
 * Testing:
 * https://developer.android.com/training/data-storage/room/testing-db?hl=es-419
 * https://stackoverflow.com/questions/48049131/cannot-resolve-symbol-instanttaskexecutorrule
 *
 * add to gradle module:
 * id 'kotlin-kapt'
 *
 * //region add in dependencies
 * constraints {
 *  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
 *      because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
 *  }
 *  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
 *      because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
 *  }
 * }
 *
 * androidTestImplementation "android.arch.core:core-testing:1.0.0"
 * testImplementation "android.arch.core:core-testing:1.0.0"
 * //endregion
 *
 * //region room
 * def room_version = "2.5.1"
 * implementation "androidx.room:room-runtime:$room_version"
 * implementation "androidx.room:room-ktx:$room_version"
 * kapt "androidx.room:room-compiler:$room_version"
 * testImplementation "androidx.room:room-testing:$room_version"
 * //endregion
 *
 * @author Elver Yesid Melo Monroy
 */
@Database(
    entities = [
        CounterRecordInsectEntity::class,
        EntomologistEntity::class,
        GeoLocationEntity::class,
        InsectEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseApp : RoomDatabase() {

    companion object {
        private val NameDB = "EntomologistApp"
        private lateinit var db: DatabaseApp

        fun startDatabase(aplicationContext: Context) {
            db = Room.databaseBuilder(
                aplicationContext,
                DatabaseApp::class.java, NameDB
            ).build()
        }

        fun getDB() = db
    }

    abstract  fun getCounterRecordInsectDao(): CounterRecordInsectDao

    abstract  fun getEntomologistDao(): EntomologistDao

    abstract fun getGeoLocationDao() : GeoLocationDao
    abstract fun getInsectDao() : InsectDao
}