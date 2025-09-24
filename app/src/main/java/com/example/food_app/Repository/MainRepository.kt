package com.example.food_app.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.food_app.Model.CategoryModel
import com.example.food_app.Model.FoodModel
import com.google.firebase.database.*

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * Load all categories from Firebase
     */
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error gracefully
                listData.postValue(mutableListOf())
            }
        })
        return listData
    }

    /**
     * Load best foods (where BestFood = true)
     */
    fun loadBestFood(): LiveData<MutableList<FoodModel>> {
        val listData = MutableLiveData<MutableList<FoodModel>>()
        val ref = firebaseDatabase.getReference("Foods")

        val query: Query = ref.orderByChild("BestFood").equalTo(true)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<FoodModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(FoodModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error gracefully
                listData.postValue(mutableListOf())
            }
        })
        return listData
    }

    /**
     * Load foods filtered by CategoryId
     */
    fun loadFiltered(id: String): LiveData<MutableList<FoodModel>> {
        val listData = MutableLiveData<MutableList<FoodModel>>()
        val ref = firebaseDatabase.getReference("Foods")
        val query: Query = ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FoodModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(FoodModel::class.java)
                    if (item != null) {
                        lists.add(item)
                    }
                }

                // 🔥 Debug
                println("Filtering by CategoryId = $id, Found = ${lists.size}")

                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                listData.value = mutableListOf()
            }
        })
        return listData
    }
}
