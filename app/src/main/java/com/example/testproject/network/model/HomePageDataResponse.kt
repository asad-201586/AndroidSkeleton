package com.example.testproject.network.model


import com.google.gson.annotations.SerializedName

data class HomePageDataResponse(
    @SerializedName("active_currencies")
    val activeCurrencies: List<ActiveCurrency>,
    @SerializedName("active_languages")
    val activeLanguages: List<ActiveLanguage>,
    @SerializedName("after")
    val after: String,
    @SerializedName("app_version")
    val appVersion: Any,
    @SerializedName("banner")
    val banner: List<Banner>,
    @SerializedName("brand")
    val brand: List<Brand>,
    @SerializedName("city_list")
    val cityList: List<City>,
    @SerializedName("company_id")
    val companyId: String,
    @SerializedName("customer_image")
    val customerImage: String,
    @SerializedName("default_currency")
    val defaultCurrency: String,
    @SerializedName("default_currency_symbol")
    val defaultCurrencySymbol: String,
    @SerializedName("default_language")
    val defaultLanguage: String,
    @SerializedName("enable_checkout_for_non_verified_user")
    val enableCheckoutForNonVerifiedUser: String,
    @SerializedName("enable_reset_password_by_otp")
    val enableResetPasswordByOtp: String,
    @SerializedName("gdpr_enable")
    val gdprEnable: Boolean,
    @SerializedName("menu")
    val menu: List<Menu>,
    @SerializedName("otp_expire_time")
    val otpExpireTime: Int,
    @SerializedName("otp_for_login")
    val otpForLogin: String,
    @SerializedName("otp_for_registartion")
    val otpForRegistartion: String,
    @SerializedName("otp_length")
    val otpLength: String,
    @SerializedName("otp_retries")
    val otpRetries: String,
    @SerializedName("outlet_status")
    val outletStatus: Boolean,
    @SerializedName("pages")
    val pages: List<Any>,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("theme_type")
    val themeType: Any,
    @SerializedName("vendor_communication")
    val vendorCommunication: Boolean
) {
    data class ActiveCurrency(
        @SerializedName("after")
        val after: String,
        @SerializedName("coefficient")
        val coefficient: String,
        @SerializedName("currency_code")
        val currencyCode: String,
        @SerializedName("currency_id")
        val currencyId: String,
        @SerializedName("decimals")
        val decimals: String,
        @SerializedName("decimals_separator")
        val decimalsSeparator: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("is_primary")
        val isPrimary: String,
        @SerializedName("position")
        val position: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("storefront_ids")
        val storefrontIds: String,
        @SerializedName("symbol")
        val symbol: String,
        @SerializedName("thousands_separator")
        val thousandsSeparator: String
    )

    data class ActiveLanguage(
        @SerializedName("country_code")
        val countryCode: String,
        @SerializedName("direction")
        val direction: String,
        @SerializedName("lang_code")
        val langCode: String,
        @SerializedName("lang_id")
        val langId: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("storefront_ids")
        val storefrontIds: String,
        @SerializedName("storefront_ids_raw")
        val storefrontIdsRaw: List<Int>
    )

    data class Banner(
        @SerializedName("banner")
        val banner: String,
        @SerializedName("data_id")
        val dataId: String,
        @SerializedName("data_type")
        val dataType: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("image_path")
        val imagePath: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String
    )

    data class Brand(
        @SerializedName("image_path")
        val imagePath: String,
        @SerializedName("variant")
        val variant: String,
        @SerializedName("variant_id")
        val variantId: String
    )

    data class City(
        @SerializedName("area")
        val area: List<Area>,
        @SerializedName("city")
        val city: String,
        @SerializedName("city_code")
        val cityCode: String,
        @SerializedName("city_id")
        val cityId: String
    ) {
        data class Area(
            @SerializedName("area")
            val area: String,
            @SerializedName("area_code")
            val areaCode: String,
            @SerializedName("area_id")
            val areaId: String
        )
    }

    data class Menu(
        @SerializedName("have_submenu")
        val haveSubmenu: Boolean,
        @SerializedName("menu_settings")
        val menuSettings: MenuSettings,
        @SerializedName("sub_menu_data")
        val subMenuData: List<SubMenuData>
    ) {
        data class MenuSettings(
            @SerializedName("activate_menu_item_for_dispatch")
            val activateMenuItemForDispatch: String,
            @SerializedName("category_id")
            val categoryId: Int,
            @SerializedName("category_name")
            val categoryName: String,
            @SerializedName("generate_submenu")
            val generateSubmenu: GenerateSubmenu,
            @SerializedName("is_leaf")
            val isLeaf: Boolean,
            @SerializedName("open_in_new_window")
            val openInNewWindow: String,
            @SerializedName("param_id")
            val paramId: String,
            @SerializedName("parent_item")
            val parentItem: Int,
            @SerializedName("position")
            val position: String,
            @SerializedName("url")
            val url: String
        ) {
            data class GenerateSubmenu(
                @SerializedName("category_name")
                val categoryName: String,
                @SerializedName("checkbox_value")
                val checkboxValue: Boolean,
                @SerializedName("id")
                val id: String,
                @SerializedName("type")
                val type: String
            )
        }

        data class SubMenuData(
            @SerializedName("menu_settings")
            val menuSettings: MenuSettings
        ) {
            data class MenuSettings(
                @SerializedName("activate_menu_item_for_dispatch")
                val activateMenuItemForDispatch: String,
                @SerializedName("category_id")
                val categoryId: Int,
                @SerializedName("category_name")
                val categoryName: String,
                @SerializedName("generate_submenu")
                val generateSubmenu: GenerateSubmenu,
                @SerializedName("is_leaf")
                val isLeaf: Boolean,
                @SerializedName("open_in_new_window")
                val openInNewWindow: String,
                @SerializedName("param_id")
                val paramId: String,
                @SerializedName("parent_item")
                val parentItem: Int,
                @SerializedName("position")
                val position: String,
                @SerializedName("url")
                val url: String
            ) {
                data class GenerateSubmenu(
                    @SerializedName("category_name")
                    val categoryName: String,
                    @SerializedName("checkbox_value")
                    val checkboxValue: Boolean,
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("type")
                    val type: String
                )
            }
        }
    }

    data class Product(
        @SerializedName("id")
        val id: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("params")
        val params: Params,
        @SerializedName("product_count")
        val productCount: String,
        @SerializedName("product_list")
        val productList: List<Product>,
        @SerializedName("title")
        val title: String
    ) {
        data class Params(
            @SerializedName("action")
            val action: String,
            @SerializedName("apply_disabled_filters")
            val applyDisabledFilters: String,
            @SerializedName("area")
            val area: String,
            @SerializedName("bid")
            val bid: Int,
            @SerializedName("cid")
            val cid: String,
            @SerializedName("company_ids")
            val companyIds: String,
            @SerializedName("company_status")
            val companyStatus: String,
            @SerializedName("custom_extend")
            val customExtend: List<Any>,
            @SerializedName("extend")
            val extend: List<String>,
            @SerializedName("feature")
            val feature: List<Any>,
            @SerializedName("features_hash")
            val featuresHash: String,
            @SerializedName("filter_variants")
            val filterVariants: List<Any>,
            @SerializedName("for_current_storefront")
            val forCurrentStorefront: Boolean,
            @SerializedName("get_frontend_urls")
            val getFrontendUrls: Boolean,
            @SerializedName("group_child_variations")
            val groupChildVariations: Boolean,
            @SerializedName("hide_out_of_stock_products")
            val hideOutOfStockProducts: Boolean,
            @SerializedName("include_child_variations")
            val includeChildVariations: Boolean,
            @SerializedName("items_per_page")
            val itemsPerPage: Int,
            @SerializedName("limit")
            val limit: Int,
            @SerializedName("load_products_extra_data")
            val loadProductsExtraData: Boolean,
            @SerializedName("match")
            val match: String,
            @SerializedName("page")
            val page: Int,
            @SerializedName("pfull")
            val pfull: String,
            @SerializedName("pid")
            val pid: String,
            @SerializedName("pkeywords")
            val pkeywords: String,
            @SerializedName("pname")
            val pname: String,
            @SerializedName("pshort")
            val pshort: String,
            @SerializedName("runtime_company_id")
            val runtimeCompanyId: Int,
            @SerializedName("show_master_products_only")
            val showMasterProductsOnly: Boolean,
            @SerializedName("sort_by")
            val sortBy: String,
            @SerializedName("sort_order")
            val sortOrder: String,
            @SerializedName("sort_order_rev")
            val sortOrderRev: String,
            @SerializedName("storefront")
            val storefront: Any,
            @SerializedName("total_items")
            val totalItems: String,
            @SerializedName("tracking")
            val tracking: List<Any>,
            @SerializedName("type")
            val type: String,
            @SerializedName("use_caching")
            val useCaching: Boolean,
            @SerializedName("usergroup_ids")
            val usergroupIds: List<Int>
        )

        data class Product(
            @SerializedName("add_to_cart")
            val addToCart: Boolean,
            @SerializedName("amount")
            val amount: String,
            @SerializedName("base_price")
            val basePrice: String,
            @SerializedName("comments")
            val comments: Comments,
            @SerializedName("currency_symbol")
            val currencySymbol: String,
            @SerializedName("discount")
            val discount: Double,
            @SerializedName("format_base_price")
            val formatBasePrice: String,
            @SerializedName("formatlist_price")
            val formatlistPrice: String,
            @SerializedName("formatprice")
            val formatprice: String,
            @SerializedName("images")
            val images: List<String>,
            @SerializedName("is_discount")
            val isDiscount: Boolean,
            @SerializedName("list_price")
            val listPrice: Int,
            @SerializedName("price")
            val price: Double,
            @SerializedName("product")
            val product: String,
            @SerializedName("product_code")
            val productCode: String,
            @SerializedName("product_id")
            val productId: String,
            @SerializedName("product_in_wishlist")
            val productInWishlist: Boolean
        ) {
            data class Comments(
                @SerializedName("avg_rating")
                val avgRating: Int
            )
        }
    }
}