package aab.lib.commons.data.room

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

abstract class BaseDao <T: BaseEntity>(private val tableName: String) {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertList(entities: List<T>)

    @Upsert
    abstract suspend fun upsert(entity: T)

    @Upsert
    abstract suspend fun upsertAll(entities: List<T>)

    @Update
    abstract suspend fun update(entity: T)

    @Update
    abstract suspend fun updateList(entity: List<T>)

    @Delete
    abstract suspend fun delete(entity: T)

    @Delete
    abstract suspend fun deleteList(entity: List<T>)

    @RawQuery
    protected abstract fun deleteAll(query: SupportSQLiteQuery): Int

    fun deleteAll() {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName")
        deleteAll(query)
    }

    @RawQuery
    protected abstract suspend fun getAll(query: SupportSQLiteQuery): List<T>

    suspend fun getAll(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
        return getAll(query)
    }

//    @RawQuery
//    protected abstract suspend fun emitAll(query: SupportSQLiteQuery) : Flow<List<T>>
//
//    suspend fun emitAll(): Flow<List<T>> {
//        val query = SimpleSQLiteQuery("SELECT * FROM $tableName")
//        return emitAll(query)
//    }

    @RawQuery
    protected abstract suspend fun getItemById(query: SupportSQLiteQuery): T?

    suspend fun getItemById(itemId: Int): T? {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE id = :$itemId")
        return getItemById(query)
    }

    @RawQuery
    protected abstract suspend fun getItemByName(query: SupportSQLiteQuery): List<T>

    suspend fun getItemsByName(searchText: String): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE name LIKE '%$searchText%'")
        return getItemByName(query)
    }
}