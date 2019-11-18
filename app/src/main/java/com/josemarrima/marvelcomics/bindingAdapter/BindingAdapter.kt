package com.josemarrima.marvelcomics.bindingAdapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josemarrima.marvelcomics.R
import com.josemarrima.marvelcomics.data.local.Comic
import com.josemarrima.marvelcomics.listOfComics.ListOfComicsAdapter
import com.josemarrima.marvelcomics.util.Resource
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri()

            Glide.with(imgView.context)
                .load(imgUri)
                .placeholder(R.drawable.orginal_image)
                .error(R.drawable.orginal_image)
                .into(imgView)
        }
    }

    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<List<Comic>>?) {
        with(recyclerView.adapter as ListOfComicsAdapter) {
            resource?.data?.let { submitList(it) }
        }
    }
}
