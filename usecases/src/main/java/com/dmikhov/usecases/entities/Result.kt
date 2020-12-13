package com.dmikhov.usecases.entities

class Result<T> (
    val payload: T? = null,
    val errorCode: Int? = null,
) {
    fun isSuccess() = payload != null && errorCode == null

    fun requirePayload() = payload!!

    override fun toString(): String {
        return "Result(payload=$payload, errorCode=$errorCode)"
    }
}