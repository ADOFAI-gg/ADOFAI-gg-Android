package io.luxus.adofai.data.source.mapper

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.luxus.adofai.data.mapper.InitializeDaoMapper
import io.luxus.adofai.data.mapper.LevelDaoMapper
import io.luxus.adofai.data.source.local.AppDatabase
import io.luxus.adofai.data.source.remote.entity.ForumLevel
import io.luxus.adofai.domain.entity.OrderOption
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class LevelDaoMapperTest {

    @get:Rule
    public var rule = HiltAndroidRule(this)

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var initializeDaoMapper: InitializeDaoMapper

    @Inject
    lateinit var levelDaoMapper: LevelDaoMapper

    @Before
    fun inject() {
        rule.inject()
    }

    @After
    fun clearDB() {
        database.clearAllTables()
    }

    @Test
    fun testGetLevel() {
        // given
        initializeDaoMapper.initializeData(
            listOf(ForumLevel(
                1, "[ns]", listOf("かめりあ(Camellia)"), 20.0, listOf("Ruren"),
                "", "", "", false,
                222.5, 266.0, 2542,
                listOf("동시치기", "셋잇단", "개박", "4분이상"))),
            listOf(),
            listOf("동시치기", "셋잇단", "개박", "4분이상")
        )

        // when
        val level = levelDaoMapper.get(1)

        // then
        assertNotNull(level)
    }

    @Test
    fun testGetLevelList() {
        // given
        initializeDaoMapper.initializeData(
            listOf(ForumLevel(
                1, "[ns]", listOf("かめりあ(Camellia)"), 20.0, listOf("Ruren"),
                "", "", "", false,
                222.5, 266.0, 2542,
                listOf("동시치기", "셋잇단", "개박", "4분이상"))),
            listOf(),
            listOf("동시치기", "셋잇단", "개박", "4분이상")
        )

        // when
        val levelList = levelDaoMapper.getList(OrderOption.LEVEL, true)

        // then
        assertNotNull(levelList)
        assertEquals(1, levelList.size)
    }

}