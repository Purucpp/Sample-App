package com.yesandroid.sampleapp.usecase.errors

import com.yesandroid.sampleapp.data.error.Error
import com.yesandroid.sampleapp.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by Purushottam
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
