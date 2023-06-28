package org.devundef1ned

data class Log<T>(
    val value: T,
    val logs: List<String>
)

fun <T, R> toLogger(
    value: T,
    function: (T) -> R,
    log: String
): Log<R> {
    return Log(logs = listOf(log), value = function(value))
}

fun addOne(value: Int): Log<Int> {
    return toLogger(
        value = value,
        function = { integer: Int -> integer + 1 },
        log = "add 1"
    )
}

fun multiplyThree(value: Int): Log<Int> {
    return toLogger(
        value = value,
        function = { integer: Int -> integer * 3 },
        log = "multiply by 3"
    )
}

fun <T, Q, R> executeLoggers(
    initialValue: T,
    firstAction: (T) -> Log<Q>,
    secondAction: (Q) -> Log<R>
): Log<R> {
    val firstLog = firstAction(initialValue)
    val secondLog = secondAction(firstLog.value)
    return Log(value = secondLog.value, logs = firstLog.logs + secondLog.logs)
}

fun runLogger(initialValue: Int) {
    val result = executeLoggers(
        initialValue = initialValue,
        firstAction =  ::addOne,
        secondAction = ::multiplyThree)

    println("Result is ${result.value}")
    println("Result is obtained via \n  $initialValue ${result.logs.joinToString(", ")}")
}