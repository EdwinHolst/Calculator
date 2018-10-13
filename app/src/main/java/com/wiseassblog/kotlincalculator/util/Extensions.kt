package com.wiseassblog.kotlincalculator.util

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.wiseassblog.kotlincalculator.R
import com.wiseassblog.kotlincalculator.dependencyinjection.Injector

fun Activity.attachFragment(manager: FragmentManager, view: Fragment){
    manager.beginTransaction()
            .replace(R.id.root_activity_calculator, view)
            .commit()
}

fun Fragment.newInstance(injector: Injector){

}