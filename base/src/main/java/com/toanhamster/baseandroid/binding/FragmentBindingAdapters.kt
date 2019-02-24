package com.toanhamster.baseandroid.binding

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import javax.inject.Inject

/**
 * Created by Toan.IT on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */
/**
 * Binding adapters that work with a fragment instance.
 */
class FragmentBindingAdapters
@Inject constructor(val fragment: Fragment) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        GlideApp.with(fragment)
                .load(url)
                .dontAnimate()
                .into(imageView)
    }

    @BindingAdapter("imageDrawable")
    fun bindImage(imageView: ImageView, drawable: Int) {
        GlideApp.with(fragment)
                .load(drawable)
                .dontAnimate()
                .into(imageView)
    }

    @BindingAdapter("onLongClick")
    fun setOnLongClick(view: View, listener: View.OnLongClickListener) {
        view.setOnLongClickListener(listener)
    }

    @BindingAdapter("imageBitmap")
    fun setImageBitmap(imageView: ImageView, bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    @BindingAdapter("html")
    fun html(webView: WebView, html: String) {
        webView.loadDataWithBaseURL(null, html, "text/html", "utf8", null)
    }
}
