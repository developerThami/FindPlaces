package com.inc.thamsanqa.findplaces

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_full_photo.*

class FullPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_photo)

        fullImage.setImageBitmap(intent.getParcelableExtra(Intent.EXTRA_TEXT))
    }
}
