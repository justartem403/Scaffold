package com.example.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiScreenApp() {
    var currentScreen by remember { mutableStateOf(0) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentScreen) {
                            0 -> "Список элементов"
                            1 -> "Детали элемента"
                            2 -> "Информационный экран"
                            else -> "Приложение"
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Список") },
                    label = { Text("Список") },
                    selected = currentScreen == 0,
                    onClick = {
                        currentScreen = 0
                        selectedItem = null
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Info, contentDescription = "Детали") },
                    label = { Text("Детали") },
                    selected = currentScreen == 1,
                    enabled = ItemRepository.items.isNotEmpty(),
                    onClick = {
                        if (ItemRepository.items.isNotEmpty()) {
                            currentScreen = 1
                            selectedItem = selectedItem ?: ItemRepository.items.first()
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Информация") },
                    label = { Text("Информация") },
                    selected = currentScreen == 2,
                    onClick = { currentScreen = 2 }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentScreen) {
                0 -> ItemListScreen(
                    items = ItemRepository.items,
                    onItemSelected = { item ->
                        selectedItem = item
                        currentScreen = 1
                    }
                )
                1 -> ItemDetailScreen(
                    item = selectedItem,
                    onBackToList = {
                        currentScreen = 0
                        selectedItem = null
                    }
                )
                2 -> InformationScreen(onBackToList = { currentScreen = 0 })
            }
        }
    }
}