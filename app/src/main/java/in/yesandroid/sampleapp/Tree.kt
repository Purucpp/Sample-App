package `in`.yesandroid.sampleapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tree_table")
 class Tree(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val age: Int,
    val timedate: String,

)