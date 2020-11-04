package com.yan.ahtloginscreen000.database

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.User

@Database(
    entities = arrayOf(Info::class),
    version = 2
)

@TypeConverters(MyTypeConverters::class)
abstract class InfoDatabase : RoomDatabase() {
    // 2
    abstract fun infoDao(): InfoDAO

    // 3
    companion object {
        private var instance: InfoDatabase? = null

        fun getInstance(context: Context): InfoDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        InfoDatabase::class.java, "InfoDatabase"
                    ).build()
            }
            return instance as InfoDatabase
        }
    }
}

class MyTypeConverters {

    @TypeConverter
    fun objectToString(user: User): String = Gson().toJson(user)

    @TypeConverter
    fun stringToObject(string: String): User = Gson().fromJson(string, User::class.java)

}
