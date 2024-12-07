package com.indri.api_produk.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.indri.api_produk.DetailProdukActivity
import com.indri.api_produk.ModelProduk
import com.indri.api_produk.R

class ProdukAdapter(
    private val onClick: (ModelProduk) -> Unit
) : ListAdapter<ModelProduk, ProdukAdapter.ProdukViewHolder>(ProductCallBack) {

    // ViewHolder untuk setiap item dalam RecyclerView
    class ProdukViewHolder(
        itemView: View,
        private val onClick: (ModelProduk) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val imgProduk: ImageView = itemView.findViewById(R.id.imgProduk)
        private val title: TextView = itemView.findViewById(R.id.txtTitle)
        private val brand: TextView = itemView.findViewById(R.id.txtBrand)
        private val price: TextView = itemView.findViewById(R.id.txtPrice)

        private var currentProduct: ModelProduk? = null

        init {
            itemView.setOnClickListener {
                currentProduct?.let { product ->
                    // Navigasi ke DetailProdukActivity
                    val intent = Intent(itemView.context, DetailProdukActivity::class.java).apply {
                        putExtra("title", product.title)
                        putExtra("description", product.description)
                        putExtra("thumbnail", product.thumbnail)
                        putExtra("price", product.price)
                        putExtra("stock", product.stock)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bind(produk: ModelProduk) {
            currentProduct = produk

            // Set data ke komponen UI
            title.text = produk.title
            brand.text = produk.brand
            price.text = "Rp ${produk.price}"

            // Menampilkan gambar dengan Glide
            Glide.with(itemView)
                .load(produk.thumbnail)
                .centerCrop()
                .into(imgProduk)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_product_item, parent, false)
        return ProdukViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = getItem(position)
        holder.bind(produk)
    }

    object ProductCallBack : DiffUtil.ItemCallback<ModelProduk>() {
        override fun areItemsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
            return oldItem == newItem
        }
    }
}