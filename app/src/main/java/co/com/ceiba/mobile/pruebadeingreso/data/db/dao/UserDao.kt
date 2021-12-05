package co.com.ceiba.mobile.pruebadeingreso.data.db.dao

import androidx.room.*
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserEntity

@Dao
interface UserDao {

    @Query(value = "SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>

    @Query(value = "SELECT * FROM users WHERE id = :id")
    suspend fun getById(id:Int): UserEntity

    @Update
    suspend fun update(user:UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<UserEntity>)

    @Delete
    suspend fun delete(user: List<UserEntity>)

}