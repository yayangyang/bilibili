package com.yayangyang.bilibili.MainUtils;

import com.yayangyang.bilibili.base.MainConstant;
import com.yayangyang.lib_common.utils.LogUtils;

public class MainStringUtil {

    public static String parseUrl(String url){
        StringBuffer sb=new StringBuffer(url);
        if(url.contains("?")){
            sb.insert(url.indexOf("?"), MainConstant.LIVE_HOME_IMG_Suffix);
        }else{
            sb.append(MainConstant.LIVE_HOME_IMG_Suffix);
        }
        return sb.toString();
    }

}
