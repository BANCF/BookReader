package com.example.bookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookreader.databinding.ActivityPdfListAdminBinding

class PdfListAdminActivity : AppCompatActivity() {

    //category id, title
    private var categoryId = ""
    private var category = ""
    private lateinit var binding : ActivityPdfListAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfListAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        categoryId = intent.getStringExtra("categoryId")!!
        category = intent.getStringExtra("category")!!
    }
}