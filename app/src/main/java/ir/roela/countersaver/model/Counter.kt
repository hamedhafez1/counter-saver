package ir.roela.countersaver.model

import androidx.room.*

@Entity(tableName = "counter")
class Counter {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "counterId")
    var counterId: Int = 0

    @ColumnInfo(name = "counter_name")
    var countName: String? = null

    @ColumnInfo(name = "counter_value")
    var countValue: String? = null

    @Dao
    interface CountDao {
        @Query("SELECT * FROM counter ORDER BY CounterId DESC")
//        fun getAll(): LiveData<List<Counter>>
        fun getAll(): List<Counter>

        @Insert
        fun insert(counter: Counter)

        @Delete
        fun delete(counter: Counter)
    }

}