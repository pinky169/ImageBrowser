package pl.patryk.imagebrowser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.patryk.imagebrowser.converters.RoomConverters
import pl.patryk.imagebrowser.dao.ItemDao
import pl.patryk.imagebrowser.model.ItemEntity
import pl.patryk.imagebrowser.model.SearchEntity

@Database(
    entities = arrayOf(SearchEntity::class, ItemEntity::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    // Singleton prevents multiple instances of database opening at the same time.
    companion object {

        // Writes to this field are immediately made visible to other threads.
        @Volatile
        var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "ImageBrowserDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}