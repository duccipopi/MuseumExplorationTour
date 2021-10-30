package br.duccipopi.met.util.adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import br.duccipopi.met.R
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageUrlToImageView(imgView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imgView.context)
            .load(imageUrl).centerInside()
            .placeholder(R.drawable.thumbnail_placeholder)
            .error(R.drawable.thumbnail_failed)
            .into(imgView)
    }
}

@BindingAdapter("noEmptyText")
fun bindEmptyTextToTextView(textView: TextView, emptyText: String?) {
    var replace = emptyText
    if (TextUtils.isEmpty(emptyText)) {
        replace = textView.context.resources.getString(R.string.unknown)
    }
    textView.text = replace
}