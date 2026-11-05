package com.example.fmradyo

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val fmPaketleri = listOf(
        "com.miui.fm",
        "com.android.fmradio",
        "com.mediatek.fmradio",
        "com.sec.android.app.fm",
        "com.caf.fmradio",
        "com.motorola.fmplayer",
        "com.huawei.android.FMRadio"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bilgi = TextView(this).apply {
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(48, 48, 48, 48)
        }
        val buton = Button(this).apply { text = "Telefonun FM radyosunu aç" }

        val bulunan = fmPaketleri.firstOrNull { packageManager.getLaunchIntentForPackage(it) != null }

        bilgi.text = if (bulunan != null) {
            "Telefonda sistem FM uygulaması bulundu:\n$bulunan\n\nNot: FM sinyali için kablolu kulaklık (anten) gerekir."
        } else {
            "Bu telefonda sistem FM uygulaması bulunamadı.\nFM çipi sadece üreticinin uygulamasına açıktır; bu telefonda FM yoksa veya kapatılmışsa uygulama bir şey yapamaz."
        }

        buton.setOnClickListener {
            val paket = fmPaketleri.firstOrNull { packageManager.getLaunchIntentForPackage(it) != null }
            if (paket != null) {
                startActivity(packageManager.getLaunchIntentForPackage(paket))
            } else {
                bilgi.text = "FM uygulaması bulunamadı."
            }
        }

        setContentView(LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(bilgi)
            addView(buton)
        })
    }
}
