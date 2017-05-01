package com.edgarxie.githubhands.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 17-4-6.
 */

public abstract class BindingRecyclerAdapter<T,VB extends ViewDataBinding> extends RecyclerView.Adapter<BindingRecyclerAdapter.BindingViewHolder> {
    private final LayoutInflater mInflater;
    protected List<T> mData;
    private View.OnClickListener mClickListener;
    protected VB mBinding;
    protected Context mContext;


    public BindingRecyclerAdapter(Context context) {
        super();
        mContext=context;
        mInflater= (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData =new ArrayList<>();
    }


    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding= DataBindingUtil.inflate(mInflater, getLayoutId(),parent,false);
        return new BindingViewHolder<>(mBinding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        T data=mData.get(position);
        holder.getBinding().setVariable(getVariableId(),data);
        holder.getBinding().executePendingBindings();
        setOthers(position,data);
        holder.itemView.setOnClickListener(mClickListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<T> data){
        mData=data;
    }

    public void add(T data){
        mData.add(data);
        notifyItemInserted(mData.size()-1);
    }

    public void remove(int position){
        if (mData.size()==0){
            return;
        }
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void setClickListener(View.OnClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public void setOthers(int position,T currentData){

    }

    protected abstract int getLayoutId();
    protected abstract int getVariableId();

    public static class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private T mBinding;

        public BindingViewHolder(T binding) {
            super(binding.getRoot());
            mBinding=binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }
}
