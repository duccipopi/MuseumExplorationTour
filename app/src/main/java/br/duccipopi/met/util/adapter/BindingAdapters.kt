package br.duccipopi.met.util.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.duccipopi.met.R
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageViewToArtwork(imgView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imgView.context)
            .load(imageUrl).centerInside()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imgView)
    }
}