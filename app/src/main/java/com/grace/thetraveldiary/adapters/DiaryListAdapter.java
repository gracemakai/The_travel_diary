package com.grace.thetraveldiary.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grace.thetraveldiary.databinding.ItemDiaryEntryBinding;
import com.grace.thetraveldiary.models.DiaryEntry;

import java.util.ArrayList;
import java.util.List;


public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder> {
    private List<DiaryEntry> entries;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(DiaryEntry position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }
    public DiaryListAdapter(List<DiaryEntry> entries) {
        this.entries = entries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDiaryEntryBinding binding = ItemDiaryEntryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(entries.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(entries.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setNewDiaryEntries(ArrayList<DiaryEntry> list) {
        this.entries = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDiaryEntryBinding binding;

        public ViewHolder(ItemDiaryEntryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DiaryEntry entry) {
            binding.title.setText(entry.getTitle());
            binding.date.setText(entry.getDate());

            binding.executePendingBindings();
        }
    }
}

