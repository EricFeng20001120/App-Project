package com.example.myapplication.Survey.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.R
import com.example.myapplication.Survey.Model.Question


private const val ratingtrue = 1
private const val ratingfalse = 0

class OptionAdapter(val context: Context,val question: Question) :
        RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options: ArrayList<String> = arrayListOf(question.option1,question.option2,question.option3,question.option4,question.option5)
    private var singleoption:ArrayList<String> = arrayListOf(question.option1)
    private var rating:String = question.rating



    inner class OptionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
        var ratingView = itemView.findViewById<RatingBar>(R.id.ratingBar123)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        if(viewType == ratingfalse) {
            val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
            return OptionViewHolder(view)
        }else{
            val view = LayoutInflater.from(context).inflate(R.layout.rating,parent,false)
            return OptionViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {

        if(getItemViewType(position)== ratingfalse) {
            holder.optionView.text = options[position]
            holder.itemView.setOnClickListener {
                ischoosen.intchoosenposition = true
                question.userAnswer = options[position]
                notifyDataSetChanged()
                recordposition.resultposition = position
            }
            if (question.userAnswer == options[position] && options[position] != " ") {
                holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
            } else if (options[position] == " ") {
                holder.itemView.setBackgroundResource(R.drawable.emptyviewfornotselected)
            } else {
                holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
            }
        }else{

            holder.ratingView.setOnRatingBarChangeListener{ ratingBar, rating, fromUser ->
                ratingvalue.ratingvalue = rating.toInt()
                haschoosenratingornot.yesno = ratingvalue.ratingvalue != 0
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (rating == "false"){
            ratingfalse
        }else{
            ratingtrue
        }
    }


    override fun getItemCount(): Int {
        if(rating =="false") {
            Ratingornot.rating = false
            return options.size
        } else{
            Ratingornot.rating = true
            return singleoption.size
        }
    }


    //check if an option is choosen
    class ischoosen{
        companion object{
            var intchoosenposition : Boolean = false
        }
    }
    //record the number of questions that has been answered
    class recordintofposition{
        companion object{
            var intposition = 0
        }
    }
    //record the name of the answer used
    class recordposition{
        companion object{
            var resultposition = 0
        }
    }
    //record all answer in arraylist
    class recordresult{
        companion object{
            var resultarray = arrayListOf<String>()
        }
    }
    class ratingvalue{
        companion object{
            var ratingvalue = 0
        }
    }
    class Ratingornot{
        companion object{
            var rating = false
        }
    }
    class haschoosenratingornot{
        companion object{
            var yesno = false
        }
    }
}