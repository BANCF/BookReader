package com.example.bookreader

import android.content.Context
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.databinding.RowPdfAdminBinding

class AdapterPdfAdmin: RecyclerView.Adapter<AdapterPdfAdmin.HolderPdfAdmin>, Filterable {

    //context
    private var context: Context
    //arraylist to hold pdf
    public var pdfArrayList: ArrayList<ModelPdf>
    private val filterList: ArrayList<ModelPdf>
    //viewBinding
    private lateinit var binding : RowPdfAdminBinding

    //filter object
        var filter : FilerPdfAdmin? = null
    constructor(context: Context, pdfArrayList: ArrayList<ModelPdf>) : super() {
        this.context = context
        this.pdfArrayList = pdfArrayList
        this.filterList = pdfArrayList
    }
    //constructor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPdfAdmin {
        //bind/inflate layout row_pdf_admin.xml
        binding = RowPdfAdminBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderPdfAdmin(binding.root)
    }

    override fun onBindViewHolder(holder: HolderPdfAdmin, position: Int) {
        /*----Get data, set Data, Handle click etc----*/

        //get data
        val model = pdfArrayList[position]
        val pdfId = model.id
        val categoryId = model.categoryId
        val title = model.title
        val description = model.description
        val pdfUrl = model.url
        val timestamp = model.timestamp

        //convert timestamp to dd/MM/yyyy format
        val formattedDate = MyApplication.formatTimeStamp(timestamp)

        //set data
        holder.titleTv.text = title
        holder.descriptionTv.text = description
        holder.dateTv.text = formattedDate

        //load further details like category, pdf from url, pdf size

        //load category
        MyApplication.loadCategory(categoryId, holder.categoryTv)

        //i don't need page number here, pas null for page number || load page thumbnail
        MyApplication.loadPdfFromUrlSinglePage(pdfUrl, title,holder.pdfView, holder.progressBar, null)

        //load pdf size
        MyApplication.loadPdfSize(pdfUrl,title,holder.sizeTv)
    }

    override fun getItemCount(): Int {
            return pdfArrayList.size  //item count
    }


    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilerPdfAdmin(filterList, this)
        }
        return filter as FilerPdfAdmin
    }
    /*View Holder class for row_pdf_admin.xml*/
    inner class HolderPdfAdmin(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //UI Views of row_pdf_admin.xml
        val pdfView = binding.pdfView
        val progressBar = binding.progressBar
        val titleTv = binding.titleTv
        val descriptionTv = binding.descriptionTv
        val categoryTv = binding.categoryTv
        val sizeTv = binding.sizeTv
        val dateTv = binding.dateTv
        val moreBtn = binding.moreBtn
    }
}