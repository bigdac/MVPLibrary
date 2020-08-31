package com.li.baselibrary.base;

import android.util.Log;
import android.view.View;

import com.li.baselibrary.inject.InjectModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020-08-25 17
 * 描述：
 */
public class BasePresenter<V extends BaseView/*,M extends BaseModel*/> {
    private  V mProxyView;
    private  V mView;
//  private M  mModel;
    private List<BaseModel> mList = new ArrayList<>();
    public void  attach( V view){
        try {
            this.mView = view;
//            Log.e("TAG", "conn: --->"+view.getClass().getInterfaces().toString() );
//            Log.e("TAG", "conn: --->"+new Class[] { BaseView.class });
            mProxyView = (V) Proxy.newProxyInstance(mView.getClass().getClassLoader(), new Class[] { BaseView.class }, new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    if (mView==null){
                        return  null;
                    }
                    return method.invoke(mView,objects);
                }
            });
            //单mode下可以解析
//            Type[] params = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
//            mModel = (M) ((Class)params[1]).newInstance();
           Field[] fields =  this.getClass().getDeclaredFields();
            for (Field field : fields) {
                InjectModel injectModel = field.getAnnotation(InjectModel.class);
                if (injectModel!=null){
                    Class<? extends BaseModel> baseClazz = (Class<? extends BaseModel>) field.getType();
                    BaseModel baseModel = baseClazz.newInstance();
                    field.setAccessible(true);
                    field.set(this,baseModel);
                    mList.add(baseModel);
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }


    }

    public void detach(){
            mProxyView = null;
            mView = null;
    }


    public V getView() {
        return mProxyView;
    }
}
