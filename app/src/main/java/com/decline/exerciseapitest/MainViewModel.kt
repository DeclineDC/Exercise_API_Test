package com.decline.exerciseapitest

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decline.exerciseapitest.data.Exercise
import com.decline.exerciseapitest.data.ExerciseAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ExerciseAPI    
) : ViewModel() {
    
    private val _state = mutableStateOf<ExerciseState>(ExerciseState())
    val state: State<ExerciseState> = _state

    init {
        getRandomExercise()
    }

    private fun getRandomExercise() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    exercise = api.getRandomExercise(),
                    isLoading = false
                )


            } catch (e : Exception) {
                Log.e("MainViewModel", "getRandomExercise: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }
    
    data class ExerciseState(
        val exercise: Exercise? = null,
        val isLoading: Boolean = false
    )
}