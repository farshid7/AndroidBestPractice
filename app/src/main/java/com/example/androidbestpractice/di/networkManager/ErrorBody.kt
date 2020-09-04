package com.example.androidbestpractice.di.networkManager

import com.google.gson.annotations.SerializedName

data class ErrorBody(@SerializedName("errors")val errors:List<String>)