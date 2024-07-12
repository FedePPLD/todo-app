package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.todoapp.addtask.ui.TasksScreen
import com.example.todoapp.addtask.ui.TasksViewModel
import com.example.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskViewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TasksScreen(taskViewModel)

                }
            }
        }
    }
}
