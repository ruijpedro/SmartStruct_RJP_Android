package com.rjp.smartstruct.calculation

import kotlin.math.max
import kotlin.math.pow

/** SmartStruct_RJP V4 - laje unidirecional simplesmente apoiada, pré-dimensionamento EC2. */
data class SlabInput(
    val spanM: Double,
    val permanentLoadKNm2: Double,
    val variableLoadKNm2: Double,
    val thicknessCm: Double,
    val coverCm: Double,
    val concreteFckMPa: Double,
    val steelFykMPa: Double
)

data class SlabResult(
    val designLoadKNm2: Double,
    val serviceLoadKNm2: Double,
    val momentKNmPerM: Double,
    val shearKNPerM: Double,
    val effectiveDepthCm: Double,
    val requiredSteelCm2PerM: Double,
    val minimumSteelCm2PerM: Double,
    val governingSteelCm2PerM: Double,
    val spanDepthRatio: Double,
    val warnings: List<String>
)

object SlabCalculator {
    fun calculate(input: SlabInput): SlabResult {
        val l = max(input.spanM, 0.1)
        val dCm = max(input.thicknessCm - input.coverCm - 0.6, 1.0)
        val wd = 1.35 * input.permanentLoadKNm2 + 1.50 * input.variableLoadKNm2
        val ws = input.permanentLoadKNm2 + input.variableLoadKNm2
        val med = wd * l.pow(2.0) / 8.0
        val ved = wd * l / 2.0
        val fyd = input.steelFykMPa / 1.15
        val zMm = 0.9 * dCm * 10.0
        val asReq = med * 1_000_000.0 / (fyd * zMm) / 100.0
        val asMin = 0.0013 * 100.0 * input.thicknessCm
        val asGov = max(asReq, asMin)
        val ratio = l * 100.0 / dCm
        val warnings = buildList {
            if (ratio > 30.0) add("Laje esbelta: verificar flecha e fendilhação com detalhe.")
            if (asReq > 10.0) add("Armadura elevada por metro: rever espessura ou vãos.")
            if (input.thicknessCm < 12.0) add("Espessura inferior a 12 cm: validar solução construtiva.")
            if (isEmpty()) add("Pré-dimensionamento sem alertas críticos.")
        }
        return SlabResult(wd, ws, med, ved, dCm, asReq, asMin, asGov, ratio, warnings)
    }
}
