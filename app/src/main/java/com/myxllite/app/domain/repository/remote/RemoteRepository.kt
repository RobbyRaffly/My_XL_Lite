package com.myxllite.app.domain.repository.remote

import com.google.gson.Gson
import com.myxllite.app.data.ApiService
import javax.inject.Inject

/**
 * Created by Bacharudin Farisi on 29/03/22
 */
class RemoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson
) : RemoteDataSource {

}