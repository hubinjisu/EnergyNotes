package com.bin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.bin.domain.model.EnergyType
import java.time.LocalDate

@Entity(tableName = "energy_note", primaryKeys = ["recordDate", "type"])
data class EnergyNoteEntity(
    @ColumnInfo(name = "reading")
    val reading: Long,
    @ColumnInfo(name = "recordDate")
    val recordDate: LocalDate,
    @ColumnInfo(name = "type")
    val type: EnergyType
)