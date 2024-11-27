package com.example.scaffold

data class Item(
    val id: Int,
    val title: String,
    val description: String
)

object ItemRepository {
    val items = listOf(
        Item(1, "Алиев Артём", "студент группы 403"),
        Item(2, "Банан", "Banana"),
        Item(3, "Апельсин", "Круглый цитрусовый фрукт"),
        Item(4, "Киви", "Зеленый волосатый фрукт"),
        Item(5, "Персик", "Мягкий сладкий персик")
    )

    fun getItemById(id: Int): Item? {
        return items.find { it.id == id }
    }

    fun getAllItems(): List<Item> = items
}