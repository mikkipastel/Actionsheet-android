package com.mikkipastel.actionsheet

import android.actionsheet.demo.com.khoiron.actionsheetiosforandroid.R
import com.mikkipastel.actionsheet.callback.ActionSheetCallBack
import com.mikkipastel.actionsheet.callback.OnClickListener
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*


/**
 * Created by khoiron on 01/06/18.
 * Modify by MikkiPastel on 11/04/19.
 */

class ActionSheet {

    lateinit var context :Context
    var data : MutableList<String> = ArrayList()
    val actionSheet by lazy { ActionSheet(context,data) }

    var alertDialog: AlertDialog? = null
    lateinit var title : TextView
    lateinit var linebottom: LinearLayout
    lateinit var cancel : TextView
    lateinit var myRecyclerView: RecyclerView
    val adapter by lazy { RecyclerViewAdapter(data) }

    constructor(context: Context,data :MutableList<String>)  {
        this.context = context
        this.data = data
    }

    fun setColorTitle(title: Int): ActionSheet {
        mData.colorTitle = title
        return actionSheet
    }

    fun setColorTitleCancel(title: Int): ActionSheet {
        mData.colorCancel = title
        return actionSheet
    }

    fun setColorData(title: Int): ActionSheet {
        mData.colorData = title
        return actionSheet
    }

    fun setColorSelected(title: Int): ActionSheet {
        mData.colorSelect = title
        return actionSheet
    }

    fun setTitle(title: String): ActionSheet {
        mData.title = title
        return actionSheet
    }

    fun setCancelTitle(title: String): ActionSheet {
        mData.titleCancel = title
        return actionSheet
    }

    fun create(actionSheetCallBack: ActionSheetCallBack) {
        val adb = AlertDialog.Builder(context)
        val v = LayoutInflater.from(context).inflate(R.layout.action_sheet,null)
        title = v.findViewById(R.id.tvTitle)
        linebottom = v.findViewById(R.id.linebottom)
        cancel = v.findViewById(R.id.tvCancelAction)

        setData()

        myRecyclerView = v.findViewById(R.id.myRecyclerview)
        myRecyclerView.layoutManager = LinearLayoutManager(context)
        myRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        adb.setView(v)
        alertDialog = adb.create()
        alertDialog?.window?.attributes?.windowAnimations = R.style.DialogAnimations_SmileWindow
        alertDialog?.setCancelable(false)
        alertDialog?.setCanceledOnTouchOutside(true)
        alertDialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.show()

        adapter.onclickCallback(object : OnClickListener {
            override fun onclick(string: String, position: Int) {
                alertDialog?.dismiss()
                actionSheetCallBack.data(string,position)
            }
        })

        cancel.setOnClickListener { alertDialog?.dismiss() }

    }

    private fun setData() {
        title.text = mData.title
        cancel.text = mData.titleCancel
        title.setTextColor(mData.colorTitle)
        cancel.setTextColor(mData.colorCancel)
        adapter.color = mData.colorData
        adapter.colorSelect = mData.colorSelect

        if (mData.title == "") {
            title.visibility = View.GONE
            linebottom.visibility = View.GONE
        }
    }

    object mData {
        var title =""
        var titleCancel =""
        var colorData = Color.parseColor("#5EA1D6")
        var colorCancel = Color.parseColor("#5EA1D6")
        var colorTitle = Color.parseColor("#afafaf")
        var colorSelect = Color.parseColor("#FAFF1E1E")
    }


}