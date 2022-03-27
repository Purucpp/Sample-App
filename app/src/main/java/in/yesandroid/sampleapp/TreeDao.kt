package `in`.yesandroid.sampleapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TreeDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTree(tree: Tree)


    @Query("SELECT * FROM tree_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Tree>>


    @Query("SELECT COUNT(*) FROM tree_table")
    fun getCount(): LiveData<Int?>?



    @Query("SELECT avg(age) FROM tree_table")
    fun getAge(): LiveData<Int?>?


    @Query("SELECT avg(age) From (SELECT age FROM tree_table order by age limit 2 - (select count(*) from tree_table )%2 offset (select (count(*) -1)/2  from tree_table))")
    fun getMedian(): LiveData<Float?>?





}