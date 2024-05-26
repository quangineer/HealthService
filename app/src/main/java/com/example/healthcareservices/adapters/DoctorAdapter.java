package com.example.healthcareservices.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareservices.databinding.LayoutDoctortemBinding;
import com.example.healthcareservices.model.doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {
    List<doctor> AdapterDoctorList;

    public DoctorAdapter(List<doctor> adapterDoctorList) {
        AdapterDoctorList = adapterDoctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutDoctortemBinding binding = LayoutDoctortemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        DoctorViewHolder holder = new DoctorViewHolder(binding.getRoot(),binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        doctor target = AdapterDoctorList.get(position);

        holder.binding.txtViewDoctorId.setText(target.getDoctorId());
        holder.binding.txtViewDoctorActiveDate.setText(target.getDoctorActiveDate());
        holder.binding.txtViewDoctorName.setText(target.getDoctorName());
        holder.binding.txtViewDoctorAddress.setText(target.getDoctorAddress());
        holder.binding.txtViewDoctorPhone.setText(target.getDoctorPhone());
        holder.binding.txtViewDoctorPrice.setText(Double.toString(target.getDoctorPrice()));

    }

    @Override
    public int getItemCount() {
        return AdapterDoctorList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{
        LayoutDoctortemBinding binding;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public DoctorViewHolder(@NonNull View itemView, LayoutDoctortemBinding binding){
            super(itemView);
            this.binding = binding;

        }
    }
}
