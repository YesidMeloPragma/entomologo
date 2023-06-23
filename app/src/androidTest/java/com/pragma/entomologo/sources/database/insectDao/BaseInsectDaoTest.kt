package com.pragma.entomologo.sources.database.insectDao

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pragma.entomologo.sources.database.DatabaseApp
import com.pragma.entomologo.sources.database.dao.InsectDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseInsectDaoTest {

    private lateinit var context: Context
    private lateinit var database: DatabaseApp
    protected lateinit var insectDao: InsectDao

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context = context, DatabaseApp::class.java).build()
        insectDao = database.getInsectDao()
    }

    @After
    fun cleanUp() {
        database.close()
    }

}