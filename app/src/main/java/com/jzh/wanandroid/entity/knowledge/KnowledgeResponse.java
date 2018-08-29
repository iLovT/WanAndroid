package com.jzh.wanandroid.entity.knowledge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;
import com.jzh.wanandroid.entity.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/25 09:39
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeResponse extends BaseResponse implements Serializable {
    @Expose
    @SerializedName("data")
    List<KnowledgeResponseData> datas;

    public List<KnowledgeResponseData> getDatas() {
        return datas;
    }

    public void setDatas(List<KnowledgeResponseData> datas) {
        this.datas = datas;
    }


}
