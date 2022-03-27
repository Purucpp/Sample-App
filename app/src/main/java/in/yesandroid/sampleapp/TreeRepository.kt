package `in`.yesandroid.sampleapp

import androidx.lifecycle.LiveData

class TreeRepository(private val treeDao: TreeDao)
{

    val readAllData: LiveData<List<Tree>> = treeDao.readAllData()

    suspend fun addTree(tree: Tree){
        treeDao.addTree(tree)
    }




    fun getCount(): LiveData<Int?>? {
        return treeDao.getCount()
    }


    fun getAge(): LiveData<Int?>? {
        return treeDao.getAge()
    }


    fun getMedian(): LiveData<Float?>? {
        return treeDao.getMedian()
    }

}