package com.rjp.smartstruct.calculation

import kotlin.math.max
import kotlin.math.sqrt

data class FoundationInput(
    val nedKN: Double,
    val medKNm: Double,
    val soilPressureKPa: Double,
    val columnBCm: Double,
    val columnHCm: Double,
    val footingBCm: Double,
    val footingLCm: Double,
    val footingHCm: Double,
    val concreteFckMPa: Double,
    val steelFykMPa: Double
)

data class FoundationResult(
    val areaM2: Double,
    val requiredAreaM2: Double,
    val eccentricityM: Double,
    val sigmaMaxKPa: Double,
    val sigmaMinKPa: Double,
    val soilOk: Boolean,
    val medXKNmPerM: Double,
    val medYKNmPerM: Double,
    val asXcm2PerM: Double,
    val asYcm2PerM: Double,
    val punchingRatio: Double,
    val punchingOk: Boolean,
    val warnings: List<String>
)

object FoundationCalculator {
    fun calculate(i: FoundationInput): FoundationResult {
        val b = max(i.footingBCm / 100.0, 0.10)
        val l = max(i.footingLCm / 100.0, 0.10)
        val h = max(i.footingHCm / 100.0, 0.10)
        val colB = max(i.columnBCm / 100.0, 0.10)
        val colH = max(i.columnHCm / 100.0, 0.10)
        val area = b * l
        val ned = max(i.nedKN, 1.0)
        val qAdm = max(i.soilPressureKPa, 1.0)
        val reqArea = ned / qAdm
        val e = i.medKNm / ned
        val sigmaAvg = ned / area
        val sigmaMax = sigmaAvg * (1.0 + 6.0 * e / l)
        val sigmaMin = sigmaAvg * (1.0 - 6.0 * e / l)
        val soilOk = sigmaMax <= qAdm && sigmaMin >= 0.0

        val cantX = max((b - colB) / 2.0, 0.0)
        val cantY = max((l - colH) / 2.0, 0.0)
        val medX = sigmaMax * cantX * cantX / 2.0
        val medY = sigmaMax * cantY * cantY / 2.0
        val d = max(h - 0.06, 0.08)
        val fyd = i.steelFykMPa / 1.15
        val z = 0.90 * d
        val asX = medX * 1000.0 / (z * fyd * 1_000_000.0) * 10_000.0
        val asY = medY * 1000.0 / (z * fyd * 1_000_000.0) * 10_000.0
        val u1 = 2.0 * ((colB + 2.0 * d) + (colH + 2.0 * d))
        val vEd = ned / max(u1 * d, 0.01)
        val vRd = 0.75 * sqrt(max(i.concreteFckMPa, 1.0)) * 1000.0
        val punchRatio = vEd / vRd
        val punchOk = punchRatio <= 1.0
        val warnings = buildList {
            if (!soilOk) add("Tensão no terreno não verifica ou existe tração na base.")
            if (!punchOk) add("Punçoamento preliminar acima do limite simplificado.")
            if (area < reqArea) add("Área da sapata inferior à área mínima NEd/qadm.")
            if (e > l / 6.0) add("Excentricidade fora do núcleo central: e > L/6.")
            if (isEmpty()) add("Verificação preliminar OK para pré-dimensionamento.")
        }
        return FoundationResult(area, reqArea, e, sigmaMax, sigmaMin, soilOk, medX, medY, asX, asY, punchRatio, punchOk, warnings)
    }
}
