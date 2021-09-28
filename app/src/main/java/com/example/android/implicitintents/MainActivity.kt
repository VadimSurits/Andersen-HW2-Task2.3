package com.example.android.implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {
    private lateinit var mWebsiteEditText: EditText
    private lateinit var mLocationEditText: EditText
    private lateinit var mShareTextEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebsiteEditText = findViewById(R.id.website_edittext)
        mLocationEditText = findViewById(R.id.location_edittext)
        mShareTextEditText = findViewById(R.id.share_edittext)
    }

    fun openWebsite(view: View) {
        val url = mWebsiteEditText.text.toString()
        val webPage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("Implicit Intents", "Can't handle this intent!")
        }
    }

    fun openLocation(view: View) {
        val loc = mLocationEditText.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("Implicit Intents", "Can't handle this intent!")
        }
    }

    fun shareText(view: View) {
        val txt = mShareTextEditText.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(getString(R.string.chooser_title))
            .setText(txt)
            .startChooser()
    }

    fun launchCamera(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("Implicit Intents", "Can't handle this intent!")
        }
    }
}

//Ответы на вопросы:

//Question 1
//Which constructor method do you use to create an implicit Intent to launch a camera app?
//
//Answer: new Intent(String action)

//Question 2
//When you create an implicit Intent object, which of the following is true?
//
//Answer: All of the above:
//Don't specify the specific Activity or other component to launch.
//Add an Intent action or Intent categories (or both).
//Resolve the Intent with the system before calling startActivity() or startActivityforResult().

//Question 3
//Which Intent action do you use to take a picture with a camera app?
//
//Answer: Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
