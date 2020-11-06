package com.drogariadopovo.treinamento.databinding

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.RecyclerView
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener
import com.drogariadopovo.treinamento.presentation.adapter.AdapterContract
import com.squareup.picasso.Picasso

class BindingAdapter {

    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, list: List<Any>) {

            recyclerView.adapter.let {
                if (it is AdapterContract) {
                    it.replaceItems(list)
                }
            }
        }

        @BindingAdapter("itemsDown")
        @JvmStatic
        fun setItemsDown(recyclerView: RecyclerView, list: List<Any>?) {
            recyclerView.adapter.let {
                if (it is AdapterContract && list != null) {
                    it.replaceItems(list)
                }
            }
            if(recyclerView.adapter != null && recyclerView.adapter!!.itemCount > 0)
                recyclerView.smoothScrollToPosition(recyclerView.adapter!!.itemCount-1)
        }

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            if(imageUrl.isNullOrBlank())
                return
            Picasso.get()
                    .load(imageUrl)
                    .resize(1000, 0)
                    .into(view)
        }

        @BindingAdapter("mask")
        @JvmStatic
        fun mask(editText: EditText, mask : String){
            editText.addTextChangedListener(MaskEditTextChangedListener(mask, editText))
        }
    }

}

