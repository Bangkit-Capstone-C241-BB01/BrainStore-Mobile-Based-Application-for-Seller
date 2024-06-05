package com.dicoding.braincoresellerapp.data.response.product

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("store_id")
	val storeId: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("store_name")
	val storeName: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
)

data class ProductsItem(

	@field:SerializedName("store_id")
	val storeId: Int? = null,

	@field:SerializedName("storeStoreId")
	val storeStoreId: Any? = null,

	@field:SerializedName("product_rate")
	val productRate: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("product_price")
	val productPrice: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("product_img")
	val productImg: String? = null,

	@field:SerializedName("product_desc")
	val productDesc: String? = null,

	@field:SerializedName("product_acceptance")
	val productAcceptance: Boolean? = null,

	@field:SerializedName("product_spec")
	val productSpec: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("img_quality")
	val imgQuality: String? = null,

	@field:SerializedName("product_stock")
	val productStock: Int? = null,

	@field:SerializedName("product_category")
	val productCategory: String? = null
)
