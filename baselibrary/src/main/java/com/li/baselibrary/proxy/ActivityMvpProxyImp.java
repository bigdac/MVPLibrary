package com.li.baselibrary.proxy;

import com.li.baselibrary.base.BasePresenter;
import com.li.baselibrary.base.BaseView;

import java.util.List;

/**
 * @author 版本：1.0
 * 创建日期：2020-08-27 09
 * 描述：
 */
public class ActivityMvpProxyImp<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy {
    public ActivityMvpProxyImp(V view) {
        super(view);
    }


}
