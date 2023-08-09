package com.satyam.notesapp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class NotesRvAdapter(val context: Context, val listener: INotesRvAdapter) :
    RecyclerView.Adapter<NotesRvAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imgEdit: ImageView
        lateinit var imgDelete: ImageView
        lateinit var txtNote: TextView

        init {
            txtNote = itemView.findViewById(R.id.txtNote)
            imgDelete = itemView.findViewById(R.id.imgDelete)
            imgEdit = itemView.findViewById(R.id.imgEdit)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.imgDelete.setOnClickListener {
            listener.onDeleteClicked(allNotes[position])
            delete(allNotes)
        }
        holder.imgEdit.setOnClickListener {

            openDialog(position);

        }
        val currentNote = allNotes[position]
        holder.txtNote.text = currentNote.text
    }

    private fun openDialog(pos: Int) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.edit_view);
        dialog.setCancelable(true)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val etEditNote: EditText = dialog.findViewById(R.id.etEditNote)
        val btnUpdate: AppCompatButton = dialog.findViewById(R.id.btnUpdate)
        etEditNote.setText(allNotes[pos].text)
        btnUpdate.setOnClickListener {
            if (etEditNote.text.toString().isNotEmpty()) {
                // Update the corresponding note in the allNotes list
                allNotes[pos].text = etEditNote.text.toString()
                listener.onEditClicked(allNotes[pos])
                dialog.dismiss()
                // Notify the adapter of the change
                notifyDataSetChanged()

                // Call the listener method to notify the ViewModel or other component
                listener.onEditClicked(allNotes[pos])

                // Close the dialog
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Please Enter Text To Update", Toast.LENGTH_SHORT).show()
            }
        }


        dialog.show()
    }

    fun delete(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    fun update(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRvAdapter {
    fun onDeleteClicked(note: Note)
    fun onEditClicked(note: Note)
}