package com.edgarxie.utils.android.recyclerview;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edgarx on 16-8-22.
 */
public abstract class BaseRVAdapter<T,VH extends BaseRVAdapter.SparseArrayViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<T> mList;
    protected Map<T,Integer> mItemPosition=new HashMap<>();

    /**
     * clicked listener
     */

    protected OnItemClickedListener<T> mOnItemClickedListener;
    /**
     * long clicked listener
     */
    protected OnItemLongClickedListener<T> mOnItemLongClickedListener;

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    public List<T> getList() {
        return mList;
    }

    public boolean isListEmpty(){
        return mList == null || mList.size() == 0;
    }

    public boolean isListNull(){
        return mList==null;
    }

    public void itemChange(T data, int position){
        mList.set(position,data);
        notifyItemChanged(position);
    }

    public void itemInsert(T data,int position){
        mList.add(data);
        notifyItemInserted(position);
    }

    public void itemRemove(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    protected T getItem(int position){
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    /**
     * inflate a view by viewGroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * inflate a view by viewGroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @param attach
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext())
                .inflate(layoutId, viewGroup, attach);
    }

    /**
     * a final function to avoid you override
     * use template design pattern
     *
     * @param vh
     * @param position
     */
    @Override
    public final void onBindViewHolder(VH vh, int position) {
        final T item = getItem(position);
        mItemPosition.put(item,position);
        bindDataToItemView(vh, item);
        bindItemViewClickListener(vh, item);
    }

    /**
     * bind data to itemview
     *
     * @param vh   viewholder
     * @param item item
     */
    protected abstract void bindDataToItemView(VH vh, T item);
    /**
     * bind click listner to itemview
     *
     * @param vh   viewholder
     * @param item item
     */
    protected final void bindItemViewClickListener(VH vh, final T item) {
        if (mOnItemClickedListener != null) {
            vh.itemView.setOnClickListener(view -> mOnItemClickedListener.onClicked(view, item));
        }
        if (mOnItemLongClickedListener != null) {
            vh.itemView.setOnLongClickListener(v -> {
                mOnItemLongClickedListener.onLongClicked(v, item);
                return true;
            });
        }
    }

    public interface OnItemClickedListener<T> {
        void onClicked(View view, T item);
    }

    public interface OnItemLongClickedListener<T> {
        void onLongClicked(View view, T item);
    }

    public void setOnItemClickedListener(OnItemClickedListener mOnItemClickedListener) {
        this.mOnItemClickedListener = mOnItemClickedListener;
    }

    public void setOnItemLongClickedListener(OnItemLongClickedListener mOnItemLongClickedListener) {
        this.mOnItemLongClickedListener = mOnItemLongClickedListener;
    }

    public Map<T, Integer> getmItemPosition() {
        return mItemPosition;
    }

    public static class SparseArrayViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> views;

        public SparseArrayViewHolder(View itemView) {
            super(itemView);
            views = new SparseArray<View>();
        }

        public <T extends View> T getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return (T) view;
        }

        public SparseArrayViewHolder setText(int viewId, String value) {
            TextView view = getView(viewId);
            view.setText(value);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = getView(viewId);
            view.setImageResource(imageResId);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setImageBitmap(int viewId, Bitmap image){
            ImageView view=getView(viewId);
            view.setImageBitmap(image);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setBackgroundColor(int viewId, int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setBackgroundResource(int viewId, int backgroundRes) {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return SparseArrayViewHolder.this;
        }

        public SparseArrayViewHolder setTag(int viewId, Object tag) {
            View view = getView(viewId);
            view.setTag(tag);
            return SparseArrayViewHolder.this;
        }
    }
}
