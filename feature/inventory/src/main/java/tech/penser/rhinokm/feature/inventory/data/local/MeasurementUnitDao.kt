package tech.penser.rhinokm.feature.inventory.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tech.penser.rhinokm.core.domain.model.SafeUuid
import tech.penser.rhinokm.feature.inventory.data.local.model.MeasurementUnitEntity

@Dao
interface MeasurementUnitDao {

    @Query("SELECT * FROM measurement_units ORDER BY name ASC")
    fun getAll(): Flow<List<MeasurementUnitEntity>>

    @Query("SELECT * FROM measurement_units WHERE id = :id")
    suspend fun getById(id: SafeUuid): MeasurementUnitEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(unit: MeasurementUnitEntity)

    @Update
    suspend fun update(unit: MeasurementUnitEntity): Int

    @Query("DELETE FROM measurement_units WHERE id = :id")
    suspend fun delete(id: SafeUuid)
}