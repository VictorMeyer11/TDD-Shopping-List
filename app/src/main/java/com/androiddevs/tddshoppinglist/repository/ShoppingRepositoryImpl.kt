package com.androiddevs.tddshoppinglist.repository

import androidx.lifecycle.LiveData
import com.androiddevs.tddshoppinglist.data.local.ShoppingDao
import com.androiddevs.tddshoppinglist.data.local.ShoppingItem
import com.androiddevs.tddshoppinglist.data.remote.PixaBayApi
import com.androiddevs.tddshoppinglist.data.remote.responses.ImageResponse
import com.androiddevs.tddshoppinglist.other.Resource
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixaBayApi: PixaBayApi
): ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixaBayApi.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknwown error occurred")
            } else {
                Resource.Error("An unknown error occurred")
            }
        } catch(e: Exception) {
            Resource.Error("Couldn't reach server")
        }
    }
}