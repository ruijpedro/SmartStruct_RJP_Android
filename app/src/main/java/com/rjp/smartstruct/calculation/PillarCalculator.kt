package com.rjp.smartstruct.calculation

import kotlin.math.max
import kotlin.math.pow

/** SmartStruct_RJP V4 - módulo preliminar de pilares EC2.
 * Entrada: kN, kNm, cm, MPa. Saída: cm² e verificações de pré-dimensionamento.
 */
data class PillarInput(
    val axialLoadKN: Double,
    val momentKNm: Double,
    val heightM: Double,
    val widthCm: Double,
    val depthCm: Double,
    val concreteFckMPa: Double,
    val steelFykMPa: Double,
    val coverCm: Double = 3.0
)

data class PillarResult(
    val areaConcreteCm2: Double,
    val designStressMPa: Double,
    val reducedConcreteResistanceMPa: Double,
    val eccentricityCm: Double,
    val slenderness: Double,
    val minimumSteelCm2: Double,
    val estimatedSteelCm2: Double,
    val steelRatioPercent: Double,
    val warnings: List<String>
)

object PillarCalculator {
    fun calculate(input: PillarInput): PillarResult {
        val b = max(input.widthCm, 1.0)
        val h = max(input.depthCm, 1.0)
        val areaCm2 = b * h
        val areaMm2 = areaCm2 * 100.0
        val nEdN = max(input.axialLoadKN, 0.0) * 1000.0
        val mEdNmm = max(input.momentKNm, 0.0) * 1_000_000.0
        val fcd = input.concreteFckMPa / 1.5
        val fyd = input.steelFykMPa / 1.15
        val sigma = if (areaMm2 > 0.0) nEdN / areaMm2 else 0.0
        val eMm = if (nEdN > 0.0) mEdNmm / nEdN else 0.0
        val iCm = h / kotlin.math.sqrt(12.0)
        val lambda = if (iCm > 0.0) (input.heightM * 100.0) / iCm else 0.0
        val asMin = max(0.10 * nEdN / fyd / 100.0, 0.002 * areaCm2)
        val leverArmMm = max((h - input.coverCm) * 10.0 * 0.9, 1.0)
        val asFlex = mEdNmm / (fyd * leverArmMm) / 100.0
        val asEstimate = max(asMin, asFlex)
        val rho = asEstimate / areaCm2 * 100.0
        val warnings = buildList {
            if (sigma > 0.60 * fcd) add("Compressão elevada: verificar interação N-M detalhada.")
            if (lambda > 70.0) add("Pilar esbelto: considerar efeitos de 2.ª ordem.")
            if (rho > 4.0) add("Taxa de armadura elevada: aumentar secção ou rever esforços.")
            if (input.momentKNm <= 0.0) add("Sem momento introduzido: resultado apenas para compressão/armadura mínima.")
            if (isEmpty()) add("Pré-verificação sem alertas críticos.")
        }
        return PillarResult(areaCm2, sigma, 0.60 * fcd, eMm / 10.0, lambda, asMin, asEstimate, rho, warnings)
    }
}
