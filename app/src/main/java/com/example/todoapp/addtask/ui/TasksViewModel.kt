package com.example.todoapp.addtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.addtask.domain.AddTaskUseCase
import com.example.todoapp.addtask.domain.DeleteTaskUseCase
import com.example.todoapp.addtask.domain.GetTasksUseCase
import com.example.todoapp.addtask.domain.UpdateTaskUseCase
import com.example.todoapp.addtask.ui.TaskUiState.Success
import com.example.todoapp.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map(::Success)
        .catch { TaskUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(isCompleted = !taskModel.isCompleted))

        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        viewModelScope.launch { deleteTaskUseCase(taskModel) }
    }
}
