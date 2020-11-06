package br.com.guiadeacessibilidade.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Thiago on 18/12/2017.
 */
class SearchHelper<T>(val editText: EditText, val changeCallback: ChangeCallback<T>, val list: ArrayList<T>) {
    var callback1 : Callback1<T>? = null
    var callback2 : Callback2<T>? = null
    var callback3 : Callback3<T>? = null
    var callbackList : CallbackList<T>? = null
    var callbackListWithParam : CallbackListWithParam<T>? = null

    constructor(editText: EditText,
                list: ArrayList<T>,
                callback1: Callback1<T>,
                changeCallback: ChangeCallback<T>) : this(editText, changeCallback, list){
        this.callback1 = callback1
        setEditText()
    }

    constructor(editText: EditText,
                list: ArrayList<T>,
                callback2: Callback2<T>,
                changeCallback: ChangeCallback<T>) : this(editText, changeCallback, list){
        this.callback2 = callback2
        setEditText()
    }

    constructor(editText: EditText,
                list: ArrayList<T>,
                callback3: Callback3<T>,
                changeCallback: ChangeCallback<T>) : this(editText, changeCallback, list){
        this.callback3 = callback3
        setEditText()
    }

    constructor(editText: EditText,
                list: ArrayList<T>,
                callbackList: CallbackList<T>,
                changeCallback: ChangeCallback<T>) : this(editText, changeCallback, list){
        this.callbackList = callbackList
        setEditText()
    }

    constructor(editText: EditText,
                list: ArrayList<T>,
                callbackListWithParam: CallbackListWithParam<T>,
                changeCallback: ChangeCallback<T>) : this(editText, changeCallback, list){
        this.callbackListWithParam = callbackListWithParam
        setEditText()
    }

    fun setEditText(){
        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val _list = arrayListOf<T>()
                val p = p0.toString().toLowerCase()
                for(x in list){
                    if(callback1 != null) {
                        if (callback1!!.paramOne(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        }
                    }
                    if(callback2 != null){
                        if (callback2!!.paramOne(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        }
                        else if (callback2!!.paramTwo(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        }
                    }
                    if(callback3 != null){
                        if (callback3!!.paramOne(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        }
                        else if (callback3!!.paramTwo(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        }
                        else if (callback3!!.paramThree(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        }
                    }
                    if(callbackList != null){
                        for (j in callbackList!!.params(x)){
                            if(j.toLowerCase().contains(p)){
                                _list.add(x)
                                break
                            }
                        }
                    }
                    if(callbackListWithParam != null){
                        if (callbackListWithParam!!.paramOne(x).toLowerCase().contains(p)) {
                            _list.add(x)
                            continue
                        } else{
                            for (j in callbackListWithParam!!.params(x)){
                                if(j.toLowerCase().contains(p)){
                                    _list.add(x)
                                    break
                                }
                            }
                        }
                    }
                }
                changeCallback.onChanged(_list)
            }
        })
    }

    interface Callback1<T> {
        fun paramOne(item : T) : String
    }

    interface Callback2<T> {
        fun paramOne(item : T) : String
        fun paramTwo(item : T) : String
    }

    interface Callback3<T> {
        fun paramOne(item : T) : String
        fun paramTwo(item : T) : String
        fun paramThree(item : T) : String
    }

    interface CallbackList<T> {
        fun params(item : T) : ArrayList<String>
    }

    interface CallbackListWithParam<T> {
        fun paramOne(item : T) : String
        fun params(item : T) : ArrayList<String>
    }

    interface ChangeCallback<T>{
        fun onChanged(list: ArrayList<T>)
    }
}