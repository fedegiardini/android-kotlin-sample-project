package com.kotlinsampleapp.mainfeature.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kotlinsampleapp.R
import com.kotlinsampleapp.common.show
import com.kotlinsampleapp.mainfeature.listener.ProductSelectionListener
import com.kotlinsampleapp.mainfeature.model.Product
import com.kotlinsampleapp.util.DrawableHelper
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * @author federico.giardini
 */
class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var items: MutableList<Product>? = null

    var productSelectionListener: ProductSelectionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false), productSelectionListener)

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        items?.get(position)?.let {
            holder.bind(it)
        }
    }

    fun setItemList(newItems: List<Product>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    fun updateItems(newItems: List<Product>) {
        items?.addAll(newItems)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View, private val listener: ProductSelectionListener?) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.itemTitle
        private val image = itemView.itemImage
        private val price = itemView.itemPrice
        private val freeShipping = itemView.itemFreeShipping

        fun bind(item: Product) {
            itemView.setOnClickListener {
                listener?.onProductSelected(item)
            }

            title.text = item.title
            Glide.with(itemView.itemImage.context)
                .load(item.thumbnail)
                .centerInside()
                .placeholder(DrawableHelper.getCircularDrawable(itemView.context))
                .transition(DrawableTransitionOptions.withCrossFade())
                .dontAnimate()
                .error(R.drawable.ic_baseline_error_24)
                .into(image)
            price.text = itemView.context.getString(R.string.item_price, item.price.toInt())
            if (item.shipping.free_shipping) freeShipping.show()
        }
    }
}