package com.indri.api_produk

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailProdukActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var price: TextView
    private lateinit var stok: TextView
    private lateinit var imgDetail: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_produk)

        title = findViewById(R.id.titleDetail)
        description = findViewById(R.id.descriptionDetail)
        price = findViewById(R.id.priceDetail)
        stok = findViewById(R.id.stokDetail)
        imgDetail = findViewById(R.id.imgDetail)

        val judul = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("description")
        val harga = intent.getDoubleExtra("price", 0.0)
        val stock = intent.getIntExtra("stok", 0)
        val gambar = intent.getStringExtra("thumbnail")
        val brande = intent.getStringExtra("brand")

        title.text = judul
        Glide.with(this).load(gambar).centerCrop().into(imgDetail)
        description.text = deskripsi
        price.text = harga.toString()
        stok.text = stock.toString()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}