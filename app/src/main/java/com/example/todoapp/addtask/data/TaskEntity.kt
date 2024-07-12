package com.example.todoapp.addtask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(

    @PrimaryKey
    val id: Int = System.currentTimeMillis().hashCode(),

    val task: String,
    var isCompleted: Boolean = false
)