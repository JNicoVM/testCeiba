package co.com.ceiba.mobile.pruebadeingreso.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    private lateinit var database: ProjectDB
    private lateinit var dao: UserDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ProjectDB::class.java)
            .allowMainThreadQueries().build()
        dao = database.UserDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    /**
     * The following Unit test check if the object added in the DB
     * is indeed in there
     */
    @Test
    fun insertUserItem() = runBlocking {
        val userEntity = UserEntity(email = "example@example.com", id = 0, name = "Pedro", phone = "1234567", username = "Pedrito")
        dao.insert(listOf(userEntity))
        val allUsers = dao.getAll()
        allUsers.contains(userEntity)
        assertThat(allUsers).contains(userEntity)
    }

    /**
     * The following Unit test check if the repeated object (by id)
     * is added/replace and check if the function is able to control
     * this use case
     */
    @Test
    fun insertRepeatedUserItem() = runBlocking {
        val userEntity = UserEntity(email = "example@example.com", id = 0, name = "Pedro", phone = "1234567", username = "Pedrito")
        dao.insert(listOf(userEntity))
        dao.insert(listOf(userEntity)) // Adding the same object (by id)
        val allUsers = dao.getAll()
        allUsers.contains(userEntity)
        assertThat(allUsers).contains(userEntity)
    }

    /**
     * The following Unit test check if the object removed in the DB
     * isn't indeed in there
     */
    @Test
    fun removeUserItem() = runBlocking {
        val userEntity = UserEntity(email = "example@example.com", id = 0, name = "Pedro", phone = "1234567", username = "Pedrito")
        dao.insert(listOf(userEntity))
        dao.delete(listOf(userEntity)) // Deleting the object
        val allUsers = dao.getAll()
        allUsers.contains(userEntity)
        assertThat(allUsers).doesNotContain(userEntity)
    }

    /**
     * The following Unit test check if the object searched by id
     * was found
     */
    @Test
    fun getUserByIdItem() = runBlocking {
        val userEntity = UserEntity(email = "example@example.com", id = 0, name = "Pedro", phone = "1234567", username = "Pedrito")
        dao.insert(listOf(userEntity))
        val specificUser = dao.getById(0)
        assertThat(userEntity).isEqualTo(specificUser)
    }
}