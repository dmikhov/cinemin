package com.dmikhov.entities

class Result<T> (
    val payload: T? = null,
    val errorCode: Int? = null,
) {
    fun isSuccess() = payload != null && errorCode == null

    override fun toString(): String {
        return "Result(payload=$payload, errorCode=$errorCode)"
    }
}