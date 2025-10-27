import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.data.local.model.StorageLocationEntity

@Dao
interface StorageLocationDao {
    @Query("SELECT * FROM storage_locations ORDER BY name ASC")
    fun getAll(): Flow<List<StorageLocationEntity>>

    @Query("SELECT * FROM storage_locations WHERE id = :id")
    suspend fun getById(id: SafeUuid): StorageLocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: StorageLocationEntity)

    @Update
    suspend fun update(location: StorageLocationEntity)

    @Query("DELETE FROM storage_locations WHERE id = :id")
    suspend fun delete(id: SafeUuid)
}

