package com.wiseassblog.kotlincalculator

import com.wiseassblog.kotlincalculator.data.ValidatorImpl
import com.wiseassblog.kotlincalculator.data.datamodel.Expression
import com.wiseassblog.kotlincalculator.domain.repository.ICalculator
import com.wiseassblog.kotlincalculator.domain.usecase.EvaluateExpression
import com.wiseassblog.kotlincalculator.viewmodel.ExpressionDataModel
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

/**
 * Created by R_KAY on 1/17/2018.
 */
class EvaluateExpressionTest {

    @Mock
    lateinit var calc: ICalculator

    @Mock
    lateinit var validator: ValidatorImpl

    lateinit var useCase: EvaluateExpression

    val EXPRESSION = "2+2"
    val ANSWER = "4"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = EvaluateExpression(calc, validator, TestScheduler())
    }

    @Test
    fun onUseCaseExecuted() {
        val subscriber = TestSubscriber<ExpressionDataModel>()

        Mockito.`when`(validator.validateExpression(EXPRESSION))
                .thenReturn(
                        Flowable.just(
                                Expression(EXPRESSION, true)
                        )
                )

        Mockito.`when`(calc.evaluateExpression(EXPRESSION))
                .thenReturn(
                        Flowable.just(
                                Expression(ANSWER, true)
                        )
                )

        useCase.execute(EXPRESSION).subscribeWith(subscriber)

        Mockito.verify(validator).validateExpression(EXPRESSION)
        Mockito.verify(calc).evaluateExpression(EXPRESSION)

        assertTrue(subscriber.values()[0].result == ANSWER)
        assertTrue(subscriber.values()[0].successful)
    }

}