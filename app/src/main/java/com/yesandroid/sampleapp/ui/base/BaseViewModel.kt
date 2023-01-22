package com.yesandroid.sampleapp.ui.base

import androidx.lifecycle.ViewModel
import com.yesandroid.sampleapp.usecase.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by Purushottam
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
