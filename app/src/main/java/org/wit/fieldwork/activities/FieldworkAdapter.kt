package org.wit.fieldwork.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_fieldwork.view.*
import org.wit.fieldwork.R
import org.wit.fieldwork.helpers.readImageFromPath
import org.wit.fieldwork.models.FieldworkModel

interface FieldworkListener {
    fun onFieldworkClick(fieldwork: FieldworkModel)
}

class FieldworkAdapter constructor(
    private var fieldworks: List<FieldworkModel>,
    private val listener: FieldworkListener
) : RecyclerView.Adapter<FieldworkAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.card_fieldwork,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val fieldwork = fieldworks[holder.adapterPosition]
        holder.bind(fieldwork, listener)
    }

    override fun getItemCount(): Int = fieldworks.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fieldwork: FieldworkModel, listener: FieldworkListener) {
            itemView.fieldworkTitle.text = fieldwork.title
            itemView.fieldworkDescription.text = fieldwork.description
            itemView.imageIcon1.setImageBitmap(readImageFromPath(itemView.context, fieldwork.image1))
            itemView.imageIcon2.setImageBitmap(readImageFromPath(itemView.context, fieldwork.image2))
            itemView.imageIcon3.setImageBitmap(readImageFromPath(itemView.context, fieldwork.image3))
            itemView.imageIcon4.setImageBitmap(readImageFromPath(itemView.context, fieldwork.image4))
            itemView.setOnClickListener { listener.onFieldworkClick(fieldwork) }
        }
    }


}

