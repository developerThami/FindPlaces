package com.inc.thamsanqa.findplaces

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_full_photo.*

import android.graphics.Bitmap
import android.graphics.Matrix
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.photo_item.*


class FullPhotoActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_photo)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

         bitmap = intent.getParcelableExtra(Intent.EXTRA_TEXT) as Bitmap
        adjustForOrientation()
        fullImage.setImageBitmap(bitmap)
    }

    private fun rotateBitmap(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId

        if(id == android.R.id.home){
            super.onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    private fun adjustForOrientation() {
        val currentOrientation = resources.configuration.orientation
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE ) {
            //bitmap = rotateBitmap( bitmap, 90.0f)
            hideSystemUi()
            supportActionBar!!.hide()
        } else {
            supportActionBar!!.show()
        }
    }

    private fun hideSystemUi() {
        fullImage.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

}
