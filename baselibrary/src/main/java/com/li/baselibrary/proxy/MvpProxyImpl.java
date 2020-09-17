package com.li.baselibrary.proxy;

import com.li.baselibrary.base.BasePresenter;
import com.li.baselibrary.base.BaseView;
import com.li.baselibrary.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 版本：1.0
 * 创建日期：2020-08-27 09
 * 描述：
 */
public class MvpProxyImpl<V extends BaseView> implements IMvpProxy {
    private V mView;
    private List<BasePresenter> mPresenters ;
    public MvpProxyImpl(V view) {
        this.mView = view;
        mPresenters = new ArrayList<>();
    }

    @Override
    public void bindAndCreatePresenter() {
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter!=null){
              Class<? extends BasePresenter> presenterClazz = (Class<? extends BasePresenter>) field.getType();
                try {
                    BasePresenter basePresenter = presenterClazz.newInstance();
                    basePresenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView,basePresenter);
                    mPresenters.add(basePresenter);
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unBindPresenter() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
        mView = null;
    }


}
