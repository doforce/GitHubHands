package com.edgarxie.githubhands.adapter;

import android.view.ViewGroup;

import com.edgarxie.githubhands.R;
import com.edgarxie.githubhands.model.DbLangModel;
import com.edgarxie.githubhands.model.table.TrendingLang;
import com.edgarxie.utils.android.recyclerview.BaseRVAdapter;
import com.kyleduo.switchbutton.SwitchButton;

/**
 * Created by edgar on 17-5-15.
 */

public class CustomLanguageAdapter extends BaseRVAdapter<TrendingLang,BaseRVAdapter.SparseArrayViewHolder> {

    @Override
    protected void bindDataToItemView(SparseArrayViewHolder holder, TrendingLang item) {
        holder.setText(R.id.item_lang,item.getLang());
//        Switch status=holder.getView(R.id.item_status);
        SwitchButton status=holder.getView(R.id.item_status);
        if (item.getSelected()==1){
            status.setChecked(true);
        }else {
            status.setChecked(false);
        }
        status.setOnCheckedChangeListener((buttonView, isChecked) -> {
            TrendingLang trendingLang=item;
            if (isChecked){
                trendingLang.setSelected(1);
            }else {
                trendingLang.setSelected(0);
            }
            DbLangModel.update(trendingLang);
        });
    }

    @Override
    public SparseArrayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SparseArrayViewHolder(inflateItemView(parent, R.layout.item_custom_language));
    }
}
