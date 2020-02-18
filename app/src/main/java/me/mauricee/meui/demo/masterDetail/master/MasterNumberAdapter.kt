package me.mauricee.meui.demo.masterDetail.master

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_number.view.*
import me.mauricee.meui.demo.R

class MasterNumberAdapter :
    RecyclerView.Adapter<MasterNumberAdapter.ViewHolder>() {

    private val _clicks = PublishSubject.create<Int>()
    val selectedNumber
        get() = _clicks.toFlowable(BackpressureStrategy.LATEST).map(MasterAction::SelectNumber)

    var numbers: List<Int> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
            .let(::ViewHolder)
    }

    override fun getItemCount(): Int = numbers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.masterDetail_item_number.text = "${numbers[position]}"
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.masterDetail_item_number.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            _clicks.onNext(numbers[adapterPosition])
        }
    }
}