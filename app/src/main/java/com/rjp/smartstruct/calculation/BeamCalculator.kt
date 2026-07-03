package com.rjp.smartstruct.calculation

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow

/** SmartStruct_RJP V3
 * Unidades de entrada: m, kN, kN/m, MPa, cm.
 * Resultados: kN, kNm, cm², mm.
 */
enum class BeamType(val label: String) {
    SIMPLY_SUPPORTED("Biapoiada"),
    CANTILEVER("Consola")
}

data class MaterialPreset(
    val name: String,
    val fckMPa: Double,
    val fykMPa: Double,
    val gammaC: Double = 1.50,
    val gammaS: Double = 1.15
)

object MaterialLibrary {
    val presets = listOf(
        MaterialPreset("C20/25 + A500", 20.0, 500.0),
        MaterialPreset("C25/30 + A500", 25.0, 500.0),
        MaterialPreset("C30/37 + A500", 30.0, 500.0),
        MaterialPreset("C35/45 + A500", 35.0, 500.0),
        MaterialPreset("C25/30 + A400", 25.0, 400.0)
    )
}

data class BeamInput(
    val beamType: BeamType,
    val spanM: Double,
    val permanentLoadKNm: Double,
    val variableLoadKNm: Double,
    val pointLoadKN: Double,
    val pointLoadPositionM: Double,
    val widthCm: Double,
    val heightCm: Double,
    val coverCm: Double,
    val concreteFckMPa: Double,
    val steelFykMPa: Double
)

data class DiagramPoint(val x: Double, val shear: Double, val moment: Double)

data class BeamResult(
    val beamType: BeamType,
    val designLoadKNm: Double,
    val serviceLoadKNm: Double,
    val designPointLoadKN: Double,
    val servicePointLoadKN: Double,
    val reactionA: Double,
    val reactionB: Double,
    val maxMoment: Double,
    val maxShearAbs: Double,
    val effectiveDepthM: Double,
    val requiredSteelCm2: Double,
    val minSteelCm2: Double,
    val adoptedSteelCm2: Double,
    val deflectionEstimateMm: Double,
    val deflectionLimitMm: Double,
    val deflectionRatio: Double,
    val diagram: List<DiagramPoint>,
    val warnings: List<String>,
    val notes: List<String>
)

object BeamCalculator {
    fun calculate(input: BeamInput): BeamResult {
        val l = input.spanM.coerceAtLeast(0.10)
        val a = input.pointLoadPositionM.coerceIn(0.0, l)
        val g = input.permanentLoadKNm.coerceAtLeast(0.0)
        val q = input.variableLoadKNm.coerceAtLeast(0.0)
        val p = input.pointLoadKN.coerceAtLeast(0.0)
        val wd = 1.35 * g + 1.50 * q
        val ws = g + q
        val pd = 1.50 * p

        val (ra, rb) = when (input.beamType) {
            BeamType.SIMPLY_SUPPORTED -> {
                val rb0 = wd * l / 2.0 + pd * a / l
                val ra0 = wd * l + pd - rb0
                ra0 to rb0
            }
            BeamType.CANTILEVER -> (wd * l + pd) to 0.0
        }

        val points = (0..100).map { i ->
            val x = l * i / 100.0
            when (input.beamType) {
                BeamType.SIMPLY_SUPPORTED -> {
                    val shear = ra - wd * x - if (x >= a) pd else 0.0
                    val moment = ra * x - wd * x * x / 2.0 - if (x >= a) pd * (x - a) else 0.0
                    DiagramPoint(x, shear, moment)
                }
                BeamType.CANTILEVER -> {
                    val shear = wd * (l - x) + if (x <= a) pd else 0.0
                    val moment = -(wd * (l - x).pow(2.0) / 2.0 + if (x <= a) pd * (a - x) else 0.0)
                    DiagramPoint(x, shear, moment)
                }
            }
        }

        val maxM = points.maxOf { abs(it.moment) }.coerceAtLeast(0.0)
        val maxV = points.maxOf { abs(it.shear) }
        val bM = input.widthCm.coerceAtLeast(5.0) / 100.0
        val hM = input.heightCm.coerceAtLeast(5.0) / 100.0
        val dM = ((input.heightCm - input.coverCm - 1.0) / 100.0).coerceAtLeast(0.05)
        val zM = 0.9 * dM
        val fyk = input.steelFykMPa.coerceAtLeast(100.0)
        val fyd = fyk / 1.15
        val asMm2 = if (zM > 0.0) maxM * 1_000_000.0 / (fyd * zM * 1000.0) else 0.0
        val asCm2 = asMm2 / 100.0
        val fctm = 0.30 * input.concreteFckMPa.coerceAtLeast(12.0).pow(2.0 / 3.0)
        val asMinMm2 = max(0.26 * fctm / fyk * bM * 1000.0 * dM * 1000.0, 0.0013 * bM * 1000.0 * dM * 1000.0)
        val asMinCm2 = asMinMm2 / 100.0
        val adopted = max(asCm2, asMinCm2)

        val ecm = 30_000_000.0 // kN/m², aproximação académica
        val inertia = bM * hM.pow(3.0) / 12.0
        val serviceP = p
        val deflection = when (input.beamType) {
            BeamType.SIMPLY_SUPPORTED -> 5.0 * ws * l.pow(4.0) / (384.0 * ecm * inertia) * 1000.0 + serviceP * l.pow(3.0) / (48.0 * ecm * inertia) * 1000.0
            BeamType.CANTILEVER -> ws * l.pow(4.0) / (8.0 * ecm * inertia) * 1000.0 + serviceP * l.pow(3.0) / (3.0 * ecm * inertia) * 1000.0
        }
        val limit = l * 1000.0 / 250.0
        val ratio = if (limit > 0.0) deflection / limit else 0.0

        val warnings = buildList {
            if (input.heightCm / 100.0 < l / 20.0 && input.beamType == BeamType.SIMPLY_SUPPORTED) add("Altura da viga pode ser reduzida para controlo de flecha: verificar L/h.")
            if (ratio > 1.0) add("Flecha simplificada superior a L/250. Aumentar inércia ou rever solução.")
            if (asCm2 > 0.04 * bM * 100.0 * hM * 100.0) add("Armadura elevada: rever secção ou ações.")
        }
        val notes = buildList {
            add("ELU: 1,35G + 1,50Q e 1,50P.")
            add("As = MEd/(z·fyd), com z≈0,9d.")
            add("Flecha imediata simplificada, sem fluência nem fendilhação.")
            add("V3 adiciona biapoiada/consola, biblioteca de materiais e verificação L/250.")
            add("Uso académico/de apoio: validar sempre por técnico habilitado.")
        }

        return BeamResult(input.beamType, wd, ws, pd, p, ra, rb, maxM, maxV, dM, asCm2, asMinCm2, adopted, deflection, limit, ratio, points, warnings, notes)
    }
}
