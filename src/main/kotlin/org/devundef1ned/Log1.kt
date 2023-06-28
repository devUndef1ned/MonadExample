package org.devundef1ned

data class Log1<T>(val value: T, val logs: List<String>)

fun <T, R> toLogger1(
    value: T,
    function: (T) -> R,
    log: String
): Log1<R> {
    return Log1(
        value = function(value),
        logs = listOf(log),
    )
}

fun _addOne(value: Int): Log1<Int> {
    return toLogger1(
        value = value,
        function = { integer: Int -> integer + 1 },
        log = "add 1"
    )
}

fun _multiplyThree(value: Int): Log1<Int> {
    return toLogger1(
        value = value,
        function = { integer: Int -> integer * 3 },
        log = "multiply by 3"
    )
}

fun _minusTwo(value: Int): Log1<Int> {
    return toLogger1(
        value = value,
        function = { integer: Int -> integer -2 },
        log = "subtract 2"
    )
}

fun <T, R> Log1<T>.flatMap(function: (T) -> Log1<R>): Log1<R> {
    val newLog = function(this.value)
    return Log1(
        value = newLog.value,
        logs = this.logs + newLog.logs,
    )
}

fun <T> T.just(): Log1<T> = Log1(value = this, logs = emptyList())

fun runLogger1(initialValue: Int) {
    val result = initialValue.just()
        .flatMap(::_addOne)
        .flatMap(::_multiplyThree)
        .flatMap(::_minusTwo)

    println("Result is ${result.value}")
    println("Result is obtained via \n  $initialValue ${result.logs.joinToString(", ")}")
}
