package com.josemarrima.marvelcomics.bindingAdapter

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.josemarrima.marvelcomics.R
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri()

            Glide.with(imgView.context)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.orginal_image)
                .error(R.drawable.orginal_image)
                .into(imgView)
        }
    }

}
