package com.example.todoapp.addtask.data

import com.example.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getAllTasks().map { items ->
        items.map {
            TaskModel(
                id = it.id,
                task = it.task,
                isCompleted = it.isCompleted
            )
        }
    }

    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toData()
        )
    }

    fun getAllTasks() = taskDao.getAllTasks()

    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toData())
    }

    suspend fun delete(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toData())
    }
}

fun TaskModel.toData(): TaskEntity {
    return TaskEntity(
        id = this.id,
        task = this.task,
        isCompleted = this.isCompleted
    )
}