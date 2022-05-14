package com.decline.exerciseapitest.data

import retrofit2.http.GET

interface ExerciseAPI {

    @GET("/randomexercise")
    suspend fun getRandomExercise(): Exercise
}