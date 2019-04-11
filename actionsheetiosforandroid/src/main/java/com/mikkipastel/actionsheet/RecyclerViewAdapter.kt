package com.mikkipastel.actionsheet

import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.R
import com.mikkipastel.actionsheet.callback.OnClickListener
import android.graphics.Color
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by khoiron on 01/06/18.
 * Modify by MikkiPastel on 11/04/19.
 */
class RecyclerViewAdapter(var data: MutableList<String>): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    lateinit var onClick : OnClickListener

    var color = Color.parseColor("#5EA1D6")
    var colorSelect = Color.parseColor("#FAFF1E1E")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
            MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_layout, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val string = data[position]

        if (position == data.size - 1) {
            holder.linebottom.visibility = View.GONE
        }

        holder.textView.text = string
        holder.itemView.setOnClickListener {
            holder.textView.setTextColor(colorSelect)
            Handler().postDelayed({
                onClick.onclick(string, position)
            }, 10)

        }
        holder.textView.setTextColor(color)
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView = v.findViewById(R.id.tvName) as TextView
        val linebottom = v.findViewById(R.id.linebottom) as LinearLayout
    }

    fun onclickCallback(onclickListener: OnClickListener){
        this.onClick =  onclickListener
    }

}