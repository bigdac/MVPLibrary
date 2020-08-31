package com.li.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.li.baselibrary.R;
import com.li.baselibrary.proxy.ActivityMvpProxy;
import com.li.baselibrary.proxy.ActivityMvpProxyImp;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020-08-25 16
 * 描述：
 */
public abstract class MvpBaseActivity extends AppCompatActivity implements BaseView {
    ActivityMvpProxyImp activityMvpProxy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        activityMvpProxy = createMvpProxy();
        initAllConfig();
    }

    protected abstract void initAllConfig();

    private   ActivityMvpProxyImp createMvpProxy(){
        if (activityMvpProxy==null){
            activityMvpProxy = new ActivityMvpProxyImp<>(this);
            activityMvpProxy.bindAndCreatePresenter();
        }
        return activityMvpProxy;
    }

    protected abstract void setContentView()  ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityMvpProxy.unBindPresenter();
    }

    public <V extends  BaseView,M extends BaseModel> BasePresenter<V>  getPresenter(){
        if (activityMvpProxy.getPresenters().size()>0){
            return (BasePresenter<V>) activityMvpProxy.getPresenters().get(0);
        }
        return null;

    }
    public <V extends  BaseView,M extends BaseModel> BasePresenter<V>   getPresenter(int pos){
        if (activityMvpProxy.getPresenters().size()>0&&pos<activityMvpProxy.getPresenters().size()){
            return (BasePresenter<V>) activityMvpProxy.getPresenters().get(pos);
        }
        return null;
    }
}
