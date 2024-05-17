package com.demo.demo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public MainAdapter(List<String> list) {
        super(R.layout.item_main, list);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder helper, @NotNull String item) {
        // helper.setText(R.id.tweetName, "This is an Item, pos: " + (helper.getAdapterPosition() - getHeaderLayoutCount()));
    }
}