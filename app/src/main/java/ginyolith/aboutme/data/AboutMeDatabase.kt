package ginyolith.aboutme.data

import android.arch.persistence.room.*
import android.arch.persistence.room.Database
import android.content.Context
import ginyolith.aboutme.model.Category
import ginyolith.aboutme.model.Detail

@Database(entities = [Category::class, Detail::class], version = 2, exportSchema = false)
abstract class AboutMeDatabase : RoomDatabase() {
    abstract fun getCategoryDAO() : CategoryDAO
    abstract fun getDetailDAO() : DetailDAO

    companion object {

        private var INSTANCE: AboutMeDatabase? = null
        val database_name = "aboutme.db"
        private val lock = Any()

        fun getInstance(context: Context): AboutMeDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AboutMeDatabase::class.java, database_name)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}

@Dao
interface CategoryDAO {
    @Insert
    fun insertAll(vararg category : Category)
    @Query("select * from category")
    fun selectAll() : List<Category>

    @Query("select * from category order by id desc")
    fun selectAllOrderByIdDesc() : List<Category>

    @Query("delete from category")
    fun deleteAll()

    @Query("delete from category where id = :id")
    fun deleteById(id : Long)

    @Query("select * from category where id = :id")
    fun selectById(id: Long): Category

}

@Dao
interface DetailDAO {
    @Insert
    fun insertAll(vararg detail : Detail)

    @Query("select * from detail where detail.categoryId = :categoryId")
    fun selectByCategoryId(categoryId : Long) : List<Detail>

    @Query("delete from detail")
    fun deleteAll()

    @Query("delete from detail where id = :id")
    fun deleteById(id : Long)

    @Query("select * from detail where id = :id")
    fun selectById(id: Long): Category
}
