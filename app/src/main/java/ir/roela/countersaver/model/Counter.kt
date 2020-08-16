package ir.roela.countersaver.model

import androidx.room.*

@Entity(tableName = "counter")
data class Counter(
    @ColumnInfo(name = "counter_name") val countName: String,
    @ColumnInfo(name = "counter_value") val countValue: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "counterId")
    var counterId: Int? = null
}