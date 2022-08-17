package com.androiddevs.tddshoppinglist.repository

import androidx.lifecycle.LiveData
import com.androiddevs.tddshoppinglist.data.local.ShoppingItem
import com.androiddevs.tddshoppinglist.data.remote.responses.ImageResponse
import com.androiddevs.tddshoppinglist.other.Resource

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}