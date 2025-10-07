# Repository tree (copy these files into your repo)

```
SmartStruct_RJP_Android/
├─ settings.gradle
├─ build.gradle
├─ gradle.properties
├─ app/
│  ├─ build.gradle
│  └─ src/
│     └─ main/
│        ├─ AndroidManifest.xml
│        └─ java/com/rjp/smartstruct/
│           ├─ MainActivity.kt
│           ├─ ui/theme/Color.kt
│           ├─ ui/theme/Theme.kt
│           ├─ ui/theme/Type.kt
│           ├─ ui/screens/HomeScreen.kt
│           └─ ui/screens/VigaScreen.kt
└─ README.md
```

---

// settings.gradle
```
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SmartStruct_RJP_Android"
include(":app")
```

---

// build.gradle (Project)
```
buildscript {
    ext.kotlin_version = '1.9.24'
}

plugins {
    id 'com.android.application' version '8.4.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.9.24' apply false
}
```

---

// gradle.properties
```
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
android.enableJetifier=true
kotlin.code.style=official
```

---

// app/build.gradle
```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
}

android {
    namespace 'com.rjp.smartstruct'
    compileSdk 34

    defaultConfig {
        applicationId 'com.rjp.smartstruct'
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName '1.0.0'
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion '1.5.14'
    }
}

dependencies {
    def composeBom = platform('androidx.compose:compose-bom:2024.06.00')
    implementation composeBom
    androidTestImplementation composeBom

    implementation 'androidx.core:core-ktx:1.13.1'
    implementation 'androidx.activity:activity-compose:1.9.1'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.8.3'

    implementation 'androidx.compose.ui:ui'
    implementation 'androidx.compose.material3:material3'
    implementation 'androidx.compose.ui:ui-tooling-preview'
    debugImplementation 'androidx.compose.ui:ui-tooling'
}
```

---

// app/src/main/AndroidManifest.xml
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:label="SmartStruct_RJP"
        android:icon="@mipmap/ic_launcher"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:allowBackup="true"
        android:supportsRtl="true">
        <activity android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

---

// app/src/main/java/com/rjp/smartstruct/MainActivity.kt
```
package com.rjp.smartstruct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.rjp.smartstruct.ui.theme.SmartTheme
import com.rjp.smartstruct.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HomeScreen()
                }
            }
        }
    }
}
```

---

// app/src/main/java/com/rjp/smartstruct/ui/theme/Color.kt
```
package com.rjp.smartstruct.ui.theme

import androidx.compose.ui.graphics.Color

val BlueCype = Color(0xFF1E88E5)
val GrayBG = Color(0xFFF3F5F7)
```

---

// app/src/main/java/com/rjp/smartstruct/ui/theme/Theme.kt
```
package com.rjp.smartstruct.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BlueCype,
    background = GrayBG,
    surface = Color.White,
)

@Composable
fun SmartTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}
```

---

// app/src/main/java/com/rjp/smartstruct/ui/theme/Type.kt
```
package com.rjp.smartstruct.ui.theme

import androidx.compose.material3.Typography

val Typography = Typography()
```

---

// app/src/main/java/com/rjp/smartstruct/ui/screens/HomeScreen.kt
```
package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SmartStruct_RJP", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text("RJP Engenharia — Eurocódigo 2", fontSize = 14.sp)
        DisclaimerCard()
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { VigaDialogState.show = true }) { Text("Viga") }
            OutlinedButton(onClick = { /* TODO: Pilar */ }) { Text("Pilar") }
            OutlinedButton(onClick = { /* TODO: Laje */ }) { Text("Laje") }
        }
        VigaScreen()
    }
}

@Composable
private fun DisclaimerCard() {
    Card { Column(Modifier.padding(12.dp)) {
        Text("Aviso Legal / Disclaimer", fontWeight = FontWeight.Bold)
        Text("Software académico; sem responsabilidade profissional. O utilizador deve validar os resultados. Base EC2/EC0.")
    }}
}
```

---

// app/src/main/java/com/rjp/smartstruct/ui/screens/VigaScreen.kt
```
package com.rjp.smartstruct.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// Tipos de apoio suportados
enum class SupportType { BIAPOIADA, CONSOLA }

// Modelos de carga
sealed class Load {
    data class Point(val P: Double, val a: Double) : Load() // P kN (↓ +), a m
    data class UDL(val q: Double, val a: Double, val b: Double) : Load() // q kN/m (↓ +), [a,b]
    data class Moment(val M: Double, val a: Double) : Load() // M kN·m (+ anti-hor)
}

object VigaDialogState { var show by mutableStateOf(false) }

@Composable
fun VigaScreen() {
    if (VigaDialogState.show) {
        VigaDialog(onClose = { VigaDialogState.show = false })
    }
}

@Composable
private fun NumberField(label: String, value: String, onChange: (String)->Unit, modifier: Modifier = Modifier.fillMaxWidth()) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun VigaDialog(onClose: () -> Unit) {
    var L by remember { mutableStateOf("6.0") } // m
    var support by remember { mutableStateOf(SupportType.BIAPOIADA) }

    // listas de cargas
    var pointLoads by remember { mutableStateOf(listOf<Load.Point>()) }
    var udlLoads by remember { mutableStateOf(listOf<Load.UDL>()) }
    var momentLoads by remember { mutableStateOf(listOf<Load.Moment>()) }

    // resultados
    var RA by remember { mutableStateOf<Double?>(null) }
    var RB by remember { mutableStateOf<Double?>(null) }
    var Menc by remember { mutableStateOf<Double?>(null) }
    var Vmax by remember { mutableStateOf(0.0) }
    var Vmin by remember { mutableStateOf(0.0) }
    var Mmax by remember { mutableStateOf(0.0) }
    var Mmin by remember { mutableStateOf(0.0) }

    // séries para traçar
    var xs by remember { mutableStateOf(listOf<Double>()) }
    var Vs by remember { mutableStateOf(listOf<Double>()) }
    var Ms by remember { mutableStateOf(listOf<Double>()) }

    // UI: tab para V/M
    var showDiagrams by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) } // 0=V, 1=M

    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = { TextButton(onClick = onClose) { Text("Fechar") } },
        title = { Text("Vigas — apoios & múltiplas cargas") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                // Dados gerais
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    NumberField("Comprimento L (m)", L, { L = it }, modifier = Modifier.weight(1f))
                    SupportSelector(support) { support = it }
                }

                Divider()

                // Adição de cargas
                AddLoadsSection(
                    onAddPoint = { p -> pointLoads = pointLoads + p },
                    onAddUDL = { q -> udlLoads = udlLoads + q },
                    onAddMoment = { m -> momentLoads = momentLoads + m }
                )

                // Listagens compactas
                if (pointLoads.isNotEmpty() || udlLoads.isNotEmpty() || momentLoads.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.heightIn(max = 180.dp)
                    ) {
                        if (pointLoads.isNotEmpty()) {
                            item { Text("Cargas Pontuais", style = MaterialTheme.typography.titleSmall) }
                            items(pointLoads) { pl -> AssistChip(text = { Text("P=${pl.P} kN @ a=${pl.a} m") }, onClick = {}) }
                        }
                        if (udlLoads.isNotEmpty()) {
                            item { Text("UDL", style = MaterialTheme.typography.titleSmall) }
                            items(udlLoads) { ql -> AssistChip(text = { Text("q=${ql.q} kN/m de ${ql.a} a ${ql.b} m") }, onClick = {}) }
                        }
                        if (momentLoads.isNotEmpty()) {
                            item { Text("Momentos", style = MaterialTheme.typography.titleSmall) }
                            items(momentLoads) { ml -> AssistChip(text = { Text("M=${ml.M} kN·m @ a=${ml.a} m") }, onClick = {}) }
                        }
                    }
                }

                // Botões de ação
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        val Ld = L.toDoubleOrNull() ?: 0.0
                        val res = when (support) {
                            SupportType.BIAPOIADA -> computeSimplySupported(Ld, pointLoads, udlLoads, momentLoads)
                            SupportType.CONSOLA -> computeCantilever(Ld, pointLoads, udlLoads, momentLoads)
                        }
                        RA = res.RA; RB = res.RB; Menc = res.Menc

                        // Amostrar V(x), M(x)
                        val xlist = (0..200).map { it * (Ld / 200.0) }
                        var vMax = Double.NEGATIVE_INFINITY
                        var vMin = Double.POSITIVE_INFINITY
                        var mMax = Double.NEGATIVE_INFINITY
                        var mMin = Double.POSITIVE_INFINITY
                        val vvals = ArrayList<Double>(xlist.size)
                        val mvals = ArrayList<Double>(xlist.size)
                        xlist.forEach { x ->
                            val (v, m) = vmAtX(x, Ld, support, res, pointLoads, udlLoads, momentLoads)
                            vvals.add(v); mvals.add(m)
                            vMax = max(vMax, v); vMin = min(vMin, v)
                            mMax = max(mMax, m); mMin = min(mMin, m)
                        }
                        xs = xlist; Vs = vvals; Ms = mvals
                        Vmax = vMax; Vmin = vMin; Mmax = mMax; Mmin = mMin
                        showDiagrams = true
                    }) { Text("Calcular / Ver diagramas") }

                    OutlinedButton(onClick = {
                        pointLoads = emptyList(); udlLoads = emptyList(); momentLoads = emptyList()
                        RA = null; RB = null; Menc = null
                        xs = emptyList(); Vs = emptyList(); Ms = emptyList()
                        Vmax = 0.0; Vmin = 0.0; Mmax = 0.0; Mmin = 0.0
                        showDiagrams = false
                    }) { Text("Limpar") }
                }

                // Resultados numéricos
                Divider()
                when (support) {
                    SupportType.BIAPOIADA -> {
                        Text("RA = ${fmt(RA)} kN")
                        Text("RB = ${fmt(RB)} kN")
                    }
                    SupportType.CONSOLA -> {
                        Text("RA = ${fmt(RA)} kN")
                        Text("Menc = ${fmt(Menc)} kN·m")
                    }
                }
                Text("Vmax = ${"%.3f".format(Vmax)} kN   |   Vmin = ${"%.3f".format(Vmin)} kN")
                Text("Mmax = ${"%.3f".format(Mmax)} kN·m |   Mmin = ${"%.3f".format(Mmin)} kN·m")
                Text("(Superposição linear; modelos isostáticos. EC2/estática clássica.)", style = MaterialTheme.typography.bodySmall)

                // Diagramas com tabs (separados)
                if (showDiagrams && xs.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    TabRow(selectedTabIndex = selectedTab) {
                        Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("Diagrama V(x)") })
                        Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("Diagrama M(x)") })
                    }
                    when (selectedTab) {
                        0 -> DiagramCanvas(xs, Vs, title = "V(x) — kN")
                        1 -> DiagramCanvas(xs, Ms, title = "M(x) — kN·m")
                    }
                }
            }
        }
    )
}

@Composable
private fun SupportSelector(current: SupportType, onChange: (SupportType)->Unit) {
    Column {
        Text("Apoio")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = current == SupportType.BIAPOIADA, onClick = { onChange(SupportType.BIAPOIADA) }, label = { Text("Biapoiada") })
            FilterChip(selected = current == SupportType.CONSOLA, onClick = { onChange(SupportType.CONSOLA) }, label = { Text("Consola") })
        }
    }
}

@Composable
private fun AddLoadsSection(
    onAddPoint: (Load.Point)->Unit,
    onAddUDL: (Load.UDL)->Unit,
    onAddMoment: (Load.Moment)->Unit
) {
    var P by remember { mutableStateOf("10.0") }
    var aP by remember { mutableStateOf("3.0") }

    var q by remember { mutableStateOf("5.0") }
    var aQ by remember { mutableStateOf("0.0") }
    var bQ by remember { mutableStateOf("6.0") }

    var M by remember { mutableStateOf("5.0") }
    var aM by remember { mutableStateOf("2.0") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Adicionar Cargas", style = MaterialTheme.typography.titleSmall)

        // Pontual
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            NumberField("P (kN, ↓ +)", P, { P = it }, Modifier.weight(1f))
            NumberField("a (m)", aP, { aP = it }, Modifier.weight(1f))
            Button(onClick = {
                val p = P.toDoubleOrNull(); val a = aP.toDoubleOrNull()
                if (p != null && a != null) onAddPoint(Load.Point(p, a))
            }) { Text("+ Pontual") }
        }

        // UDL
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            NumberField("q (kN/m, ↓ +)", q, { q = it }, Modifier.weight(1f))
            NumberField("a (m)", aQ, { aQ = it }, Modifier.weight(1f))
            NumberField("b (m)", bQ, { bQ = it }, Modifier.weight(1f))
            Button(onClick = {
                val qq = q.toDoubleOrNull(); val aa = aQ.toDoubleOrNull(); val bb = bQ.toDoubleOrNull()
                if (qq != null && aa != null && bb != null && bb >= aa) onAddUDL(Load.UDL(qq, aa, bb))
            }) { Text("+ UDL") }
        }

        // Momento
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            NumberField("M (kN·m, + anti-hor)", M, { M = it }, Modifier.weight(1f))
            NumberField("a (m)", aM, { aM = it }, Modifier.weight(1f))
            Button(onClick = {
                val mm = M.toDoubleOrNull(); val aa = aM.toDoubleOrNull()
                if (mm != null && aa != null) onAddMoment(Load.Moment(mm, aa))
            }) { Text("+ Momento") }
        }
    }
}

/* ============================== */
/*           CÁLCULO              */
/* ============================== */

data class Reactions(val RA: Double? = null, val RB: Double? = null, val Menc: Double? = null)

private fun computeSimplySupported(L: Double, pts: List<Load.Point>, udls: List<Load.UDL>, moms: List<Load.Moment>): Reactions {
    val sumP = pts.sumOf { it.P }
    val sumQ = udls.sumOf { it.q * (it.b - it.a) }
    val total = sumP + sumQ

    val mP = pts.sumOf { it.P * it.a }
    val mQ = udls.sumOf { it.q * (it.b - it.a) * ((it.a + it.b) / 2.0) }
    val mM = moms.sumOf { it.M }
    val RB = (mP + mQ + mM) / L
    val RA = total - RB
    return Reactions(RA = RA, RB = RB)
}

private fun computeCantilever(L: Double, pts: List<Load.Point>, udls: List<Load.UDL>, moms: List<Load.Moment>): Reactions {
    val sumP = pts.sumOf { it.P }
    val sumQ = udls.sumOf { it.q * (it.b - it.a) }
    val RA = sumP + sumQ
    val mP = pts.sumOf { it.P * it.a }
    val mQ = udls.sumOf { it.q * (it.b - it.a) * ((it.a + it.b) / 2.0) }
    val mM = moms.sumOf { it.M }
    val Menc = mP + mQ + mM
    return Reactions(RA = RA, Menc = Menc)
}

// V(x) e M(x) por superposição
private fun vmAtX(
    x: Double,
    L: Double,
    support: SupportType,
    r: Reactions,
    pts: List<Load.Point>,
    udls: List<Load.UDL>,
    moms: List<Load.Moment>
): Pair<Double, Double> {
    var V = 0.0
    var M = 0.0

    when (support) {
        SupportType.BIAPOIADA -> {
            val RA = r.RA ?: 0.0
            V += RA
            M += RA * x
        }
        SupportType.CONSOLA -> {
            val RA = r.RA ?: 0.0
            val Menc = r.Menc ?: 0.0
            V += RA
            M += Menc + RA * x
        }
    }

    // UDLs parciais: só a parte à esquerda de x
    udls.forEach { ql ->
        val a = ql.a; val b = ql.b
        if (x > a) {
            val xRight = min(x, b)
            if (xRight > a) {
                val len = xRight - a
                val res = ql.q * len
                val xc = a + len / 2.0
                V -= res
                M -= res * (x - xc)
            }
        }
    }

    // Pontuais
    pts.forEach { pl ->
        if (x >= pl.a) {
            V -= pl.P
            M -= pl.P * (x - pl.a)
        }
    }

    // Momentos aplicados
    moms.forEach { ml ->
        if (x >= ml.a) M += ml.M
    }

    // Reação de B na biapoiada só atua para x ≥ L
    if (support == SupportType.BIAPOIADA) {
        val RB = r.RB ?: 0.0
        if (x >= L) {
            V -= RB
            M -= RB * (x - L)
        }
    }

    return V to M
}

private fun fmt(v: Double?): String = if (v == null) "-" else "%.3f".format(v)

/* ============================== */
/*        CANVAS DOS DIAGRAMAS    */
/* ============================== */

@Composable
private fun DiagramCanvas(x: List<Double>, y: List<Double>, title: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium)
        Surface(tonalElevation = 1.dp) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(8.dp)) {
                if (x.isEmpty() || y.isEmpty()) return@Canvas
                val w = size.width
                val h = size.height

                // Margens
                val left = 40f
                val right = w - 10f
                val top = 10f
                val bottom = h - 30f

                // Escalas
                val xmin = x.first().toFloat()
                val xmax = x.last().toFloat()
                val ymin = y.minOrNull()!!.toFloat()
                val ymax = y.maxOrNull()!!.toFloat()
                val yAbsMax = max(abs(ymin), abs(ymax)).coerceAtLeast(1e-6f)

                fun xToPx(xx: Float) = left + (xx - xmin) / (xmax - xmin).coerceAtLeast(1e-6f) * (right - left)
                fun yToPx(yy: Float) = (bottom + top)/2f - (yy / yAbsMax) * ((bottom - top)/2f)

                // Eixos
                drawLine(Color(0xFFBDBDBD), Offset(left, (bottom+top)/2f), Offset(right, (bottom+top)/2f)) // eixo horizontal (y=0)
                drawLine(Color(0xFFBDBDBD), Offset(left, top), Offset(left, bottom)) // eixo x=0

                // Path positivo (azul) e negativo (vermelho) com preenchimento
                val pathPos = Path()
                val pathNeg = Path()
                var startedPos = false
                var startedNeg = false

                for (i in x.indices) {
                    val px = xToPx(x[i].toFloat())
                    val py = yToPx(y[i].toFloat())
                    val mid = yToPx(0f)
                    if (y[i] >= 0) {
                        if (!startedPos) { pathPos.moveTo(px, mid); startedPos = true }
                        pathPos.lineTo(px, py)
                    } else {
                        if (!startedNeg) { pathNeg.moveTo(px, mid); startedNeg = true }
                        pathNeg.lineTo(px, py)
                    }
                }
                // fechar até ao eixo
                if (startedPos) {
                    pathPos.lineTo(xToPx(x.last().toFloat()), yToPx(0f))
                    drawPath(pathPos, Color(0xFF1976D2).copy(alpha = 0.25f)) // azul translúcido
                    // contorno
                    drawPath(pathPos, Color(0xFF1976D2))
                }
                if (startedNeg) {
                    pathNeg.lineTo(xToPx(x.last().toFloat()), yToPx(0f))
                    drawPath(pathNeg, Color(0xFFD32F2F).copy(alpha = 0.25f)) // vermelho translúcido
                    drawPath(pathNeg, Color(0xFFD32F2F))
                }
            }
        }
        Text("Áreas positivas (azul) e negativas (vermelho). Escala automática.", style = MaterialTheme.typography.bodySmall)
    }
}
```


---

// README.md
```
# SmartStruct_RJP_Android (Base v1.0)

App Android (Kotlin + Jetpack Compose) para cálculo estrutural académico segundo o Eurocódigo 2 (RJP Engenharia).

## Funcionalidades desta base
- Ecrã inicial com menu (Viga/Pilar/Laje)
- **Viga biapoiada**: UDL + uma carga pontual → RA, RB, Vmax, Mmax
- Disclaimer legal
- Estilo visual tipo CYPE (Material3 + Compose)

## Como abrir e compilar
1. Instala **Android Studio Iguana+**.
2. Faz *Download ZIP* deste repositório ou *Clone*.
3. Abre a pasta no Android Studio.
4. Aguarda o sync do Gradle e clica ▶️ **Run** (emulador ou dispositivo físico).

## Estrutura
- `MainActivity.kt`: arranque e tema
- `HomeScreen.kt`: menu e disclaimer
- `VigaScreen.kt`: cálculo rápido de viga biapoiada

## Notas técnicas
- Package ID: `com.rjp.smartstruct`
- compileSdk 34, minSdk 24
- Compose BOM 2024.06

## Aviso Legal / Disclaimer
Software académico; sem responsabilidade profissional. O utilizador é responsável por validar os resultados. Base EC0/EC2.
