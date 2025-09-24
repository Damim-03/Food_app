package com.example.food_app.ViewModel

import androidx.lifecycle.LiveData
import com.example.food_app.Model.CategoryModel
import com.example.food_app.Model.FoodModel
import com.example.food_app.Repository.MainRepository

class MainViewModel {
    private val repository = MainRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBestFood(): LiveData<MutableList<FoodModel>> {
        return repository.loadBestFood()
    }

    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        return repository.loadFiltered(id)
    }
}