package com.nimeji.gw2tp.common.exceptions

class TaskRunningException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}