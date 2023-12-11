package com.example.rightmeow;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapt extends RecyclerView.Adapter<ListAdapt.ViewHolder> {

    ToDoList list;

    public ListAdapt(ToDoList list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Task task = taskList.get(position);

        //holder.textViewDescription.setText(name);
        holder.button.setText(list.name);
        Log.d("test", "wtf: " + holder.button.getText());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(context, actualList.class);
                Log.d("test", "List is inlistadapt " + list.name);
                intent.putExtra("x", list);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonTask);
        }
    }
}
