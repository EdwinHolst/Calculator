package com.wiseassblog.kotlincalculator.domain.usecase

import com.wiseassblog.kotlincalculator.domain.BaseUseCase
import com.wiseassblog.kotlincalculator.domain.domainmodel.EvaluationResult
import com.wiseassblog.kotlincalculator.domain.repository.ICalculator
import com.wiseassblog.kotlincalculator.domain.repository.IValidator
import com.wiseassblog.kotlincalculator.util.EvaluationError


/**
 * Created by R_KAY on 12/20/2017.
 */
class EvaluateExpression(private val calculator: ICalculator,
                         private val validator: IValidator) : BaseUseCase {

    override suspend fun execute(expression: String): EvaluationResult<Exception, String> {


        val validationResult = validator.validateExpression(expression)

        when (validationResult) {
            is EvaluationResult.Value -> {
                val evaluationResult = calculator.evaluateExpression(expression)
                if (evaluationResult is EvaluationResult.Value) {
                    return EvaluationResult.buildValue { evaluationResult.value }
                } else {
                    return EvaluationResult.buildError(EvaluationError.CalculationError())
                }
            }

            is EvaluationResult.Error -> return EvaluationResult.buildError(validationResult.error)
        }

    }
}
