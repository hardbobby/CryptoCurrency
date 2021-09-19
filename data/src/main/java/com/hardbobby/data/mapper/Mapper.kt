package com.hardbobby.data.mapper


interface Mapper<Response, Model> {

    fun mapFromResponse(response: Response?, pageNum: Int): Model

    fun mapFromDao(model: Model?) : Response
}