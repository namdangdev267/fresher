package com.misa.fresher.di

import android.content.Context
import com.misa.fresher.data.repository.product.*
import com.misa.fresher.data.repository.user.UserRepository
import com.misa.fresher.data.source.local.*
import com.misa.fresher.data.source.local.dao.*
import com.misa.fresher.data.source.local.database.AppDbHelper
import com.misa.fresher.data.source.remote.UserRemoteDataSource

/**
 * - object's purpose: dependency injection method to get repository
 *
 * @author HTLong
 * @edit_at 24/03/2022
 */

object Injector {
    fun getProductModelRepository(context: Context): ProductModelRepository {
        val dbHelper = AppDbHelper.getInstance(context)
        val productModelDao = ProductModelDao.getInstance(dbHelper)
        val productModelLocalDataSource = ProductModelLocalDataSource.getInstance(productModelDao)
        return ProductModelRepository.getInstance(productModelLocalDataSource)
    }

    fun getProductItemRepository(context: Context): ProductItemRepository {
        val dbHelper = AppDbHelper.getInstance(context)
        val productItemDao = ProductItemDao.getInstance(dbHelper)
        val productItemLocalDataSource = ProductItemLocalDataSource.getInstance(productItemDao)
        return ProductItemRepository.getInstance(productItemLocalDataSource)
    }

    fun getProductUnitRepository(context: Context): ProductUnitRepository {
        val dbHelper = AppDbHelper.getInstance(context)
        val productUnitDao = ProductUnitDao.getInstance(dbHelper)
        val productUnitLocalDataSource = ProductUnitLocalDataSource.getInstance(productUnitDao)
        return ProductUnitRepository.getInstance(productUnitLocalDataSource)
    }

    fun getProductBillRepository(context: Context): ProductBillRepository {
        val dbHelper = AppDbHelper.getInstance(context)
        val productBillDao = ProductBillDao.getInstance(dbHelper)
        val productBillLocalDataSource = ProductBillLocalDataSource.getInstance(productBillDao)
        return ProductBillRepository.getInstance(productBillLocalDataSource)
    }

    fun getModelUnitRepository(context: Context): ModelUnitRepository {
        val dbHelper = AppDbHelper.getInstance(context)
        val modelUnitDao = ModelUnitDao.getInstance(dbHelper)
        val modelUnitLocalDataSource = ModelUnitLocalDataSource.getInstance(modelUnitDao)
        return ModelUnitRepository.getInstance(modelUnitLocalDataSource)
    }

    fun getItemBillRepository(context: Context): ItemBillRepository {
        val dbHelper = AppDbHelper.getInstance(context)
        val itemBillDao = ItemBillDao.getInstance(dbHelper)
        val itemBillLocalDataSource = ItemBillLocalDataSource.getInstance(itemBillDao)
        return ItemBillRepository.getInstance(itemBillLocalDataSource)
    }

    fun getUserRepository(): UserRepository {
        val userRemoteDataSource = UserRemoteDataSource.getInstance()
        return UserRepository.getInstance(userRemoteDataSource)
    }

}