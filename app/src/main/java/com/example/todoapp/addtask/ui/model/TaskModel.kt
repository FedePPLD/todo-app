package com.example.todoapp.addtask.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var isCompleted: Boolean = false
)