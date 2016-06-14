package com.ithxh.xhh.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * @category 获取spring上下文环境容器 在测试的时候等可以使用 例如：
 *  systemService = ApplicationContextUtil.getContext().getBean( SystemService.class)
 * @author 伍志平
 * 
 */

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context) throws BeansException {

		this.context = context;
	}

	public static ApplicationContext getContext() {

		return context;
	}

	/**
	 * 根据Bean名称获取实例
	 * 
	 * @param name
	 *            Bean注册名称
	 * 
	 * @return bean实例
	 * 
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {

		return context.getBean(name);
	}
}
