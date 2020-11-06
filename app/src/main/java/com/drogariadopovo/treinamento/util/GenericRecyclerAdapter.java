package com.drogariadopovo.treinamento.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class GenericRecyclerAdapter<L, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>  {

    public List<L> list;
    private GenericRecyclerViewInterface<L, V> genericRecyclerViewInterface;
    public Context context;

    public GenericRecyclerAdapter(Context context, List<L> list, GenericRecyclerViewInterface<L, V> genericRecyclerViewInterface){
        this.list = list;
        this.genericRecyclerViewInterface = genericRecyclerViewInterface;
        this.context = context;
    }

    public List<L> getList(){
        return list;
    }

    public Context getContext(){
        return context;
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return genericRecyclerViewInterface.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        genericRecyclerViewInterface.onBindViewHolder(holder, position, list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface GenericRecyclerViewInterface<L, V extends RecyclerView.ViewHolder>{
        V onCreateViewHolder(ViewGroup parent, int viewType);
        void onBindViewHolder(V holder, int position, List<L> list);
    }
}
