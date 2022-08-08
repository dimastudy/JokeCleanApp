package com.justadroiddev.wordscleanarchapp.data.database

import android.content.Context
import androidx.room.*
import com.justadroiddev.wordscleanarchapp.data.models.JokeDatabaseEntity


@Dao
interface JokesDao{

    @Query("select * from favorite_jokes")
    suspend fun fetchJokes() : List<JokeDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addJoke(joke: JokeDatabaseEntity)

    @Delete
    fun removeJoke(joke: JokeDatabaseEntity)

}

@Database(entities = [JokeDatabaseEntity::class], version = 1, exportSchema = false)
abstract class JokesDatabase : RoomDatabase(){
    abstract fun dao() : JokesDao
}

private lateinit var INSTANCE: JokesDatabase

fun getDataJokesDatabase(context: Context) : JokesDatabase{

    if (!::INSTANCE.isInitialized){
        INSTANCE = Room.databaseBuilder(
            context,
            JokesDatabase::class.java,
            "jokes_database"
        ).build()
    }

    return INSTANCE

}