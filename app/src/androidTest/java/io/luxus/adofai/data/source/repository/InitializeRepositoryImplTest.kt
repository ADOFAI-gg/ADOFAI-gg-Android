package io.luxus.adofai.data.source.repository



import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.luxus.adofai.data.mapper.InitializeDaoMapper
import io.luxus.adofai.data.mapper.LevelDaoMapper
import io.luxus.adofai.data.repository.InitializeRepositoryImpl
import io.luxus.adofai.data.source.local.AppDatabase
import io.luxus.adofai.domain.entity.OrderOption
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class InitializeRepositoryImplTest {

    @get:Rule
    var rule = HiltAndroidRule(this)

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var initializeRepositoryImpl: InitializeRepositoryImpl

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
    fun testInit() = runBlocking {
        // given

        // when
        initializeRepositoryImpl.initialize()


        // then
        val levelDao = database.levelDao()
        val result = levelDao.getLevel()
        for (localLevel in result) {
            try {
                val level = levelDaoMapper.get(localLevel.levelId)
                Assert.assertNotNull("Failed: ${localLevel.levelId}", level)
            } catch (t: Throwable) {
                Assert.assertNotNull("Failed: ${localLevel.levelId}", null)
                throw t
            }
        }

        val levelList = levelDaoMapper.getList(OrderOption.ID, true)
        assert(levelList.isNotEmpty())

    }



}