package com.li.baselibrary.proxy;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020-08-27 09
 * 描述：
 */
public interface IMvpProxy {
    void  bindAndCreatePresenter();
    void  unBindPresenter();
}
