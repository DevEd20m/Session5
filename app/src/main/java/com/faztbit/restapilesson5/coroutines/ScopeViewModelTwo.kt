package com.faztbit.restapilesson5.coroutines

import androidx.lifecycle.ViewModel

 abstract class ScopeViewModelTwo : ViewModel(),  ScopeTwo by ScopeTwo.Imp() {
    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}