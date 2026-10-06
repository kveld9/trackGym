package com.kveld9.trackgym.domain.model

enum class SetType(val shortLabel: String, val displayName: String) {
    NORMAL("N", "Serie Normal"),
    WARMUP("W", "Calentamiento"),
    DROP("D", "Drop Set"),
    FAILURE("F", "Al Fallo")
}
