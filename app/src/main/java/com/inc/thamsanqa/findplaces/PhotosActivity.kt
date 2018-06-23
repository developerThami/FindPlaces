package com.inc.thamsanqa.findplaces

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.View

import com.inc.thamsanqa.findplaces.model.Photo
import com.inc.thamsanqa.findplaces.ui.PlacesContract
import com.inc.thamsanqa.findplaces.ui.PlacesPresenter
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : AppCompatActivity(), PhotoAdapter.OnImageSelectListener, PlacesContract.PhotosView {

    lateinit var tag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        tag = if (intent.hasExtra(Intent.EXTRA_TEXT)) intent.getStringExtra(Intent.EXTRA_TEXT) else tag

        val presenter = PlacesPresenter()
        presenter.getPlacePhotos(tag, this)

    }

    override fun imageSelected(photo: Bitmap?) {
        val intent = Intent(this, FullPhotoActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, photo)
        startActivity(intent)
    }

    override fun showPhotos(photos: List<Photo>) {
        displayPhotos(photos)
    }

    private fun displayPhotos(photos: List<Photo>) {

        if (photos.isEmpty()) {
            Snackbar.make(recyclerView!!, R.string.no_photos, Snackbar.LENGTH_LONG).show()
        }

        val photoAdapter = PhotoAdapter(photos, this)

        progress!!.visibility = View.INVISIBLE
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = photoAdapter
    }
}
