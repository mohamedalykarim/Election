package com.mohalim.election.ui.records;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohalim.election.core.models.Elector;
import com.mohalim.election.databinding.RowRecordBinding;

import java.util.List;

public class RecordsRecyclerViewAdapter extends RecyclerView.Adapter<RecordsRecyclerViewAdapter.RecordsViewHolder> {
    List<Elector> electorList;

    @NonNull
    @Override
    public RecordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRecordBinding binding = RowRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsViewHolder holder, int position) {
        holder.binding.nameTV.setText(electorList.get(position).getName());
        holder.binding.idTV.setText(electorList.get(position).getId());
        holder.binding.familyTV.setText(electorList.get(position).getFamily());
        holder.binding.regionTV.setText( electorList.get(position).getRegion());
        holder.binding.phoneTV.setText( electorList.get(position).getPhone()+ "");
    }

    @Override
    public int getItemCount() {
        return electorList == null ? 0 : electorList.size();
    }

    class RecordsViewHolder extends RecyclerView.ViewHolder{
        RowRecordBinding binding;
        public RecordsViewHolder(RowRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setElectorList(List<Elector> electorList) {
        this.electorList = electorList;
    }
}
