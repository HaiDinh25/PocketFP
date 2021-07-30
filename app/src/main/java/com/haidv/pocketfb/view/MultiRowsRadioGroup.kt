package com.haidv.pocketfb.view

import android.content.Context
import android.os.Build.VERSION
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup

class MultiRowsRadioGroup : RadioGroup {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    private fun init() {
        setOnHierarchyChangeListener(object : OnHierarchyChangeListener {
            override fun onChildViewRemoved(parent: View, child: View?) {
                if (parent === this@MultiRowsRadioGroup && child is ViewGroup) {
                    for (radioButton in getRadioButtonFromGroup(child as ViewGroup?)) {
                        radioButton.setOnCheckedChangeListener(null)
                    }
                }
            }

            override fun onChildViewAdded(parent: View, child: View?) {
                if (parent === this@MultiRowsRadioGroup && child is ViewGroup) {
                    for (radioButton in getRadioButtonFromGroup(child as ViewGroup?)) {
                        var id = radioButton.id
                        // generates an id if it's missing
                        if (id == View.NO_ID) {
                            id = if (VERSION.SDK_INT >= 17) View.generateViewId() else radioButton.hashCode()
                            radioButton.id = id
                        }
                        if (radioButton.isChecked) {
                            check(id)
                        }
                        radioButton.setOnCheckedChangeListener(object :
                            CompoundButton.OnCheckedChangeListener {
                            override fun onCheckedChanged(
                                buttonView: CompoundButton,
                                isChecked: Boolean
                            ) {
                                if (isChecked) {
                                    radioButton.setOnCheckedChangeListener(null)
                                    check(buttonView.id)
                                    radioButton.setOnCheckedChangeListener(this)
                                }
                            }
                        })
                    }
                }
            }
        })
    }

    private var checking = false
    override fun check(id: Int) {
        if (checking) return
        checking = true
        super.check(id)
        checking = false
    }

    private fun getRadioButtonFromGroup(group: ViewGroup?): ArrayList<RadioButton> {
        if (group == null) return ArrayList()
        val list: ArrayList<RadioButton> = ArrayList()
        getRadioButtonFromGroup(group, list)
        return list
    }

    private fun getRadioButtonFromGroup(group: ViewGroup, list: ArrayList<RadioButton>) {
        var i = 0
        val count = group.childCount
        while (i < count) {
            val child: View = group.getChildAt(i)
            if (child is RadioButton) {
                list.add(child)
            } else if (child is ViewGroup) {
                getRadioButtonFromGroup(child, list)
            }
            i++
        }
    }
}