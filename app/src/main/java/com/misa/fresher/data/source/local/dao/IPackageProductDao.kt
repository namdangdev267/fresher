package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.models.PackageProduct

interface IPackageProductDao {
    suspend fun getAllPackageProduct(): MutableList<PackageProduct>
    suspend fun addPackageProduct(packageProduct: PackageProduct): Long
    suspend fun getPackageProduct(idPackage: String): MutableList<PackageProduct>
}