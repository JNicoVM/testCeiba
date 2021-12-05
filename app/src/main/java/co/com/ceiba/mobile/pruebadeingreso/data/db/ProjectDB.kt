package co.com.ceiba.mobile.pruebadeingreso.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class ProjectDB : RoomDatabase() {

    abstract fun UserDao() : UserDao
}