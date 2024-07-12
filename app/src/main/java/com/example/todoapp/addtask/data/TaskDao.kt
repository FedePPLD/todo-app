package com.example.todoapp.addtask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * from TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(task: TaskEntity)

    @Update
    suspend fun updateTask(item: TaskEntity)

    @Delete
    suspend fun deleteTask(item: TaskEntity)
}