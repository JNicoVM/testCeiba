package co.com.ceiba.mobile.pruebadeingreso.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
)

