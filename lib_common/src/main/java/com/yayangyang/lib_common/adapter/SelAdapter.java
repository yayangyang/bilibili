package com.yayangyang.lib_common.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.lib_common.R;
import com.yayangyang.lib_common.utils.LogUtils;

import java.util.List;

public class SelAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private int selectedPosition=0;

    public SelAdapter(int id, List<String> data) {
        super(id,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LogUtils.e("SelAdapter-convert"+item);
        helper.setText(R.id.bt_type,item);
        if(selectedPosition==helper.getLayoutPosition()){
            helper.setBackgroundColor(R.id.bt_type,mContext.getResources().getColor(R.color.colorAccent));
        }else{
            helper.setBackgroundColor(R.id.bt_type,mContext.getResources().getColor(R.color.common_bg));
        }
        helper.addOnClickListener(R.id.bt_type);
    }

    public void setSelPosition(int selectedPosition){
        this.selectedPosition=selectedPosition;
    }
}
