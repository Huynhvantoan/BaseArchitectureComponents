package com.toanhamster.baseandroid.data.api

import com.google.gson.annotations.Expose

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

class JsonArray<T> {
    @Expose
    var result: List<T>? = null
}
