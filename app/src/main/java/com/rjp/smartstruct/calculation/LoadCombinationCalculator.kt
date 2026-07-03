package com.rjp.smartstruct.calculation

import kotlin.math.max

data class LoadInput(
    val gk: Double,
    val qk: Double,
    val psi0: Double = 0.7,
    val psi1: Double = 0.5,
    val psi2: Double = 0.3
)

data class LoadCombinationResult(
    val elu: Double,
    val elsRare: Double,
    val elsFrequent: Double,
    val elsQuasiPermanent: Double,
    val governing: String,
    val governingValue: Double
)

object LoadCombinationCalculator {
    fun calculate(input: LoadInput): LoadCombinationResult {
        val elu = 1.35 * input.gk + 1.50 * input.qk
        val elsRare = input.gk + input.qk
        val elsFrequent = input.gk + input.psi1 * input.qk
        val elsQuasi = input.gk + input.psi2 * input.qk
        val maxValue = max(elu, max(elsRare, max(elsFrequent, elsQuasi)))
        val governing = when (maxValue) {
            elu -> "ELU fundamental"
            elsRare -> "ELS rara"
            elsFrequent -> "ELS frequente"
            else -> "ELS quase permanente"
        }
        return LoadCombinationResult(elu, elsRare, elsFrequent, elsQuasi, governing, maxValue)
    }
}
