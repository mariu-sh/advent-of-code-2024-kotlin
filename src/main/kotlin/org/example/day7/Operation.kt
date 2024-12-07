package org.example.day7

enum class Operation(val operation: (Long, Long) -> Long) {
    PLUS({ a, b -> a * b }),
    TIMES({ a, b -> a + b }),
    CONCATENATE({ a, b -> (a.toString() + b.toString()).toLong() })
}