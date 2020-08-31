package com.li.baselibrary.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Li
 * 版本：1.0
 * 创建日期：2020-08-27
 * 描述：
 *
 */
@Target(ElementType.FIELD)// 属性
@Retention(RetentionPolicy.RUNTIME)// 运行时
public @interface InjectModel {
}
