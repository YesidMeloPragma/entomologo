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