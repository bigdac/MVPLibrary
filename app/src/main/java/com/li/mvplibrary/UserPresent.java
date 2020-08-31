package com.li.mvplibrary;

import com.li.baselibrary.base.BasePresenter;
import com.li.baselibrary.inject.InjectModel;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020-08-27 18
 * 描述：
 */
public class UserPresent extends BasePresenter <MainActivity> {
    @InjectModel
    private userModel userModel;
    @InjectModel
    private userModel1 userModel1;

    public String  getMessage(){

        return userModel.getName();
    }
    public String  getMessage2(){
        return userModel1.getName();
    }

}
