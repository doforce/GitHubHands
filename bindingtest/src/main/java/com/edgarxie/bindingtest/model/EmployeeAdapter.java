package com.edgarxie.bindingtest.model;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgarxie.bindingtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by edgar on 17-4-6.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private static final int TYPE_ON=1;
    private static final int TYPE_OFF=2;
    private onItemClickListener mListener;
    private final LayoutInflater mInflater;
    private List<Employee> mEmployeeList;
    private Random mRandom=new Random();

    public interface onItemClickListener{
        void onClick(Employee employee);
    }

    public EmployeeAdapter(Context context) {
        super();
        mInflater= (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mEmployeeList=new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        final Employee employee=mEmployeeList.get(position);
        if (employee.isFired()){
            return TYPE_OFF;
        }
        return TYPE_ON;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType==TYPE_ON){
            binding= DataBindingUtil.inflate(mInflater, R.layout.item_employee_on,parent,false);
        }else {
            binding=DataBindingUtil.inflate(mInflater,R.layout.item_employee_off,parent,false);
        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Employee employee=mEmployeeList.get(position);
        holder.getBinding().setVariable(com.edgarxie.bindingtest.BR.employee,employee);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onClick(employee);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    public void addAll(List<Employee> employees){
        mEmployeeList.addAll(employees);
    }

    public void add(Employee employee){
        mEmployeeList.add(employee);
        notifyItemInserted(mEmployeeList.size()-1);
    }

    public void remove(){
        if (mEmployeeList.size()==0){
            return;
        }
        mEmployeeList.remove(0);
        notifyItemRemoved(0);
    }

    public void remove(int position){
        if (mEmployeeList.size()==0){
            return;
        }
        mEmployeeList.remove(position);
        notifyItemRemoved(position);
    }

    public void setListener(onItemClickListener mListener) {
        this.mListener = mListener;
    }
}
