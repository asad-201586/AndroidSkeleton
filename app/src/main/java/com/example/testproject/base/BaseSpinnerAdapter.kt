package com.prangroup.thirdEyePlus.base

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

abstract class BaseSpinnerAdapter<T>: BaseAdapter() {

    protected val list: ArrayList<T> = arrayListOf()
    var spinnerItemClickLister: ItemClickListener<T>? = null

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): T {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun addNewList(list: List<T>?) {
        if (list != null) {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun addNewList(list: ArrayList<T>?) {
        if (list != null) {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun removeAll(){
        list.clear()
        notifyDataSetChanged()
    }

    interface ItemClickListener<T> {
        fun onItemClickListener(item: T)
    }

    abstract override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    open fun onAttach(activity: Activity)
    {}
}

