package br.duccipopi.met.model.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.duccipopi.met.model.Artwork
import br.duccipopi.met.model.Department

@Dao
interface MetMuseumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(department: Department)

    @Query("SELECT * from DEPARTMENT_TABLE WHERE id = :id")
    fun getDepartment(id: Int): LiveData<Department>

    @Query("SELECT * from DEPARTMENT_TABLE")
    fun getDepartments(): LiveData<List<Department>>

    @Query("DELETE from DEPARTMENT_TABLE")
    fun clearDepartments()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artwork: Artwork)

    @Query("SELECT * from ARTWORK_TABLE WHERE id = :id")
    fun getArtwork(id: Int): LiveData<Artwork>

    @Query("SELECT * from ARTWORK_TABLE")
    fun getArtworks(): LiveData<List<Artwork>>

    @Query("SELECT * from ARTWORK_TABLE WHERE departmentId = :id")
    fun getArtworksFromDepartment(id: Int): LiveData<List<Artwork>>

    @Query("DELETE from ARTWORK_TABLE")
    fun clearArtworks()

}