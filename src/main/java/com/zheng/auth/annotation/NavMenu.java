package com.zheng.auth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.zheng.auth.domain.MenuPos;

/**
 * 左边导航菜单和顶部导航菜单资源
 *
 * @author zhenglian
 * @data 2015年12月26日 下午2:56:26
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NavMenu {
	public String name();

	/**
	 * 默认为类名
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午2:57:03
	 * @return
	 */
	public String sn() default "";

	public int menuPos() default MenuPos.NAV_LEFT;

	public String icon() default "icon-more";

	public int orderNum();

	public String href() default "";

	public int display() default 1;

	public String psn();
}
