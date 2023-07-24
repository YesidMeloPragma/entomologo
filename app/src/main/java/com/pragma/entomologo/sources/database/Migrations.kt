package com.pragma.entomologo.sources.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE VIEW IF NOT EXISTS `CounterRecordInsectDetailView` AS " +
                "SELECT " +
                "ri.id, ri.comment, ri.count, ri.insectId, ie.specieName, ie.urlPhoto, ri.geoLocationId, ge.lat, ge.lng " +
                "FROM CounterRecordInsectEntity ri, InsectEntity ie, geolocationentity ge " +
                "WHERE " +
                "ri.insectId = ie.id and " +
                "ri.geoLocationId = ge.id"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //region alter table GeoLocation
        val cursorGeolocation = database.query("PRAGMA table_info(GeoLocationEntity)")
        var existsCity = false
        while (cursorGeolocation.moveToNext()) {
            val columnName = cursorGeolocation.getString(cursorGeolocation.getColumnIndexOrThrow("name"))
            println("elementos: $columnName")
            if(columnName != "city") continue
            existsCity = true
            break
        }
        cursorGeolocation.close()
        if(!existsCity) {
            database.execSQL("ALTER TABLE `GeoLocationEntity` ADD COLUMN `city` TEXT NOT NULL DEFAULT `Ciudad Desconocida` ")
        }
        //endregion


        //region alter table CounterRecordInsectEntity
        val cursorCounterRecordInsectEntity = database.query("PRAGMA table_info(CounterRecordInsectEntity)")
        var existsDate = false
        while (cursorCounterRecordInsectEntity.moveToNext()) {
            val columnName = cursorCounterRecordInsectEntity.getString(cursorCounterRecordInsectEntity.getColumnIndexOrThrow("name"))
            println("elementos: $columnName")
            if(columnName != "date") continue
            existsDate = true
            break
        }
        cursorCounterRecordInsectEntity.close()
        if(!existsDate) {
            database.execSQL("ALTER TABLE `CounterRecordInsectEntity` ADD COLUMN `date` INTEGER NOT NULL DEFAULT 0 ")
        }
        //endregion

        database.execSQL("DROP VIEW IF EXISTS `CounterRecordInsectDetailView`;")
        database.execSQL(
            "CREATE VIEW IF NOT EXISTS `CounterRecordInsectDetailView` AS " +
                    "SELECT " +
                    "ri.id, ri.comment, ri.count, ri.date, ri.insectId, ie.specieName, ie.urlPhoto, ri.geoLocationId, ge.lat, ge.lng, ge.city " +
                    "FROM CounterRecordInsectEntity ri, InsectEntity ie, geolocationentity ge " +
                    "WHERE " +
                    "ri.insectId = ie.id and " +
                    "ri.geoLocationId = ge.id;"
        )
    }
}