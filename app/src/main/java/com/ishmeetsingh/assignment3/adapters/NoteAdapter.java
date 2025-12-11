// Added ViewHolder explanation to clarify binding flow for RecyclerView notes.
// PR 2 Note: RecyclerView adapter optimized for note binding and view recycling.
package com.ishmeetsingh.assignment3.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishmeetsingh.assignment3.EditNoteActivity;
import com.ishmeetsingh.assignment3.R;
import com.ishmeetsingh.assignment3.models.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public interface OnNoteActionListener {
        void onToggleFavorite(Note note);
        void onDelete(Note note);
    }

    private Context context;
    private List<Note> notes;
    private OnNoteActionListener listener;

    public NoteAdapter(Context context, List<Note> notes, OnNoteActionListener listener) {
        this.context = context;
        this.notes = notes;
        this.listener = listener;
    }

    public void setNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        final Note n = notes.get(position);
        holder.title.setText(n.title);
        holder.content.setText(n.content);
        holder.favoriteIcon.setImageResource(n.isFavorite ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);

        if (n.mediaUri != null) {
            holder.mediaPreview.setVisibility(View.VISIBLE);
            try {
                holder.mediaPreview.setImageURI(Uri.parse(n.mediaUri));
            } catch (Exception e) { holder.mediaPreview.setVisibility(View.GONE); }
        } else {
            holder.mediaPreview.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, EditNoteActivity.class);
            i.putExtra("noteId", n.id);
            context.startActivity(i);
        });

        holder.favoriteIcon.setOnClickListener(v -> {
            if (listener != null) listener.onToggleFavorite(n);
        });

        holder.deleteIcon.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(n);
        });
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView mediaPreview, favoriteIcon, deleteIcon;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            content = itemView.findViewById(R.id.noteContent);
            mediaPreview = itemView.findViewById(R.id.noteMediaPreview);
            favoriteIcon = itemView.findViewById(R.id.noteFavoriteIcon);
            deleteIcon = itemView.findViewById(R.id.noteDeleteIcon);
        }
    }
}
