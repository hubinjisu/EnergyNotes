package com.bin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bin.data.model.EnergyNoteEntity
import com.bin.domain.model.EnergyType
import java.time.ZonedDateTime
import java.util.concurrent.Flow

@Dao
interface EnergyNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(energyNoteEntity: EnergyNoteEntity)

    @Query("select * from energy_note")
    suspend fun getNotes(): List<EnergyNoteEntity>

    @Query("select * from energy_note where type = :type order by timestamp desc")
    suspend fun getNotesByType(type: EnergyType): List<EnergyNoteEntity>

    @Query("DELETE FROM energy_note")
    suspend fun deleteAll()

    @Query("DELETE FROM energy_note where timestamp = :timestamp")
    suspend fun delete(timestamp: ZonedDateTime)
}