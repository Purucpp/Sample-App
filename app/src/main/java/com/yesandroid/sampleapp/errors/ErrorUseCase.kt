package com.yesandroid.sampleapp.usecase.errors

import com.yesandroid.sampleapp.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
