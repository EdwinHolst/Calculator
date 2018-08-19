package com.wiseassblog.kotlincalculator.domain.repository

import com.wiseassblog.kotlincalculator.domain.domainmodel.EvaluationResult

/**
 * Created by R_KAY on 1/20/2018.
 */
interface IValidator {

    //This part of the program can operate synchronously
    fun validateExpression(expression:String): EvaluationResult<Exception, Boolean>

}