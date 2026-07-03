package com.rjp.smartstruct.calculation

import kotlin.math.*

data class RetainingWallInput(
    val heightM: Double,
    val gammaSoil: Double,
    val phiDeg: Double,
    val surchargeKpa: Double,
    val baseWidthM: Double,
    val toeM: Double,
    val stemThicknessM: Double,
    val concreteGamma: Double,
    val frictionCoefficient: Double,
    val qAdmKpa: Double,
    val concreteClass: String,
    val steelFykMpa: Double
)

data class RetainingWallResult(
    val ka: Double,
    val kp: Double,
    val k0: Double,
    val paSoilKnM: Double,
    val paSurchargeKnM: Double,
    val paTotalKnM: Double,
    val overturningMomentKnM: Double,
    val resistingMomentKnM: Double,
    val verticalLoadKnM: Double,
    val eccentricityM: Double,
    val sigmaMaxKpa: Double,
    val sigmaMinKpa: Double,
    val slidingResistanceKnM: Double,
    val fsSliding: Double,
    val fsOverturning: Double,
    val stemMomentKnM: Double,
    val stemAsCm2M: Double,
    val baseAsCm2M: Double,
    val status: List<String>
)

object RetainingWallCalculator {
    private val fckMap = mapOf(
        "C20/25" to 20.0,
        "C25/30" to 25.0,
        "C30/37" to 30.0,
        "C35/45" to 35.0,
        "C40/50" to 40.0
    )

    fun calculate(input: RetainingWallInput): RetainingWallResult {
        val h = input.heightM.coerceAtLeast(0.1)
        val b = input.baseWidthM.coerceAtLeast(0.3)
        val phi = Math.toRadians(input.phiDeg.coerceIn(5.0, 45.0))
        val ka = (1.0 - sin(phi)) / (1.0 + sin(phi))
        val kp = (1.0 + sin(phi)) / (1.0 - sin(phi))
        val k0 = 1.0 - sin(phi)

        val paSoil = 0.5 * ka * input.gammaSoil * h * h
        val paSurcharge = ka * input.surchargeKpa * h
        val paTotal = paSoil + paSurcharge
        val mSoil = paSoil * h / 3.0
        val mSurcharge = paSurcharge * h / 2.0
        val mOverturn = mSoil + mSurcharge

        val heel = (b - input.toeM - input.stemThicknessM).coerceAtLeast(0.0)
        val stemWeight = input.concreteGamma * input.stemThicknessM * h
        val baseWeight = input.concreteGamma * b * 0.35
        val soilOverHeel = input.gammaSoil * heel * h
        val surchargeOverHeel = input.surchargeKpa * heel
        val vertical = stemWeight + baseWeight + soilOverHeel + surchargeOverHeel

        val xStem = input.toeM + input.stemThicknessM / 2.0
        val xBase = b / 2.0
        val xHeel = input.toeM + input.stemThicknessM + heel / 2.0
        val mResist = stemWeight * xStem + baseWeight * xBase + soilOverHeel * xHeel + surchargeOverHeel * xHeel
        val resultantX = (mResist - mOverturn) / vertical.coerceAtLeast(0.01)
        val eccentricity = b / 2.0 - resultantX
        val sigmaAvg = vertical / b
        val sigmaMax = sigmaAvg * (1.0 + 6.0 * eccentricity.absoluteValue / b)
        val sigmaMin = sigmaAvg * (1.0 - 6.0 * eccentricity.absoluteValue / b)

        val slidingResistance = vertical * input.frictionCoefficient
        val fsSliding = slidingResistance / paTotal.coerceAtLeast(0.01)
        val fsOverturning = mResist / mOverturn.coerceAtLeast(0.01)

        val fck = fckMap[input.concreteClass] ?: 25.0
        val fyd = input.steelFykMpa / 1.15
        val dStemMm = (input.stemThicknessM * 1000.0 - 50.0).coerceAtLeast(120.0)
        val zStemMm = 0.9 * dStemMm
        val stemAs = (mOverturn * 1_000_000.0 / (zStemMm * fyd) / 100.0).coerceAtLeast(2.0)
        val baseMoment = sigmaMax * b * b / 12.0
        val dBaseMm = (350.0 - 60.0).coerceAtLeast(180.0)
        val baseAs = (baseMoment * 1_000_000.0 / (0.9 * dBaseMm * fyd) / 100.0).coerceAtLeast(2.0)

        val alerts = mutableListOf<String>()
        if (fsSliding >= 1.5) alerts += "Deslizamento OK: FS ≥ 1,50" else alerts += "Atenção: FS deslizamento < 1,50"
        if (fsOverturning >= 1.5) alerts += "Derrubamento OK: FS ≥ 1,50" else alerts += "Atenção: FS derrubamento < 1,50"
        if (sigmaMin >= 0.0) alerts += "Sem tração na base" else alerts += "Atenção: há tração na base"
        if (sigmaMax <= input.qAdmKpa) alerts += "Tensão no solo OK: σmax ≤ qadm" else alerts += "Atenção: σmax > qadm"
        if (fck < 25.0) alerts += "Nota: para muros profissionais recomenda-se validar classe de betão e durabilidade"

        return RetainingWallResult(
            ka = ka,
            kp = kp,
            k0 = k0,
            paSoilKnM = paSoil,
            paSurchargeKnM = paSurcharge,
            paTotalKnM = paTotal,
            overturningMomentKnM = mOverturn,
            resistingMomentKnM = mResist,
            verticalLoadKnM = vertical,
            eccentricityM = eccentricity,
            sigmaMaxKpa = sigmaMax,
            sigmaMinKpa = sigmaMin,
            slidingResistanceKnM = slidingResistance,
            fsSliding = fsSliding,
            fsOverturning = fsOverturning,
            stemMomentKnM = mOverturn,
            stemAsCm2M = stemAs,
            baseAsCm2M = baseAs,
            status = alerts
        )
    }
}
