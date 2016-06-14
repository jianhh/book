package com.ithxh.xhh.task;

import org.apache.log4j.Logger;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.Log4jUtil;

/***
 * @category Demo Task
 * @author z
 * @date 2015-5-19
 * @time 下午5:40:09
 */
public class DemoTask {

	private Logger log = Logger.getLogger(DemoTask.class); // 日志
	private static boolean isRun = false; // 判断是否正在运行的标志。

	public synchronized void testTaskRun() {

		if (!isRun) {
			isRun = true;
			try {
				String key = DateUtils.getNowDateTime();
				log.debug("开始调用Send Sms For Group Task方法...: " + key);
				System.out.println("task start.." + key);
				// lock.lock();
				log.info("task start...: " + key);
			} catch (Exception ex) {
				ex.printStackTrace();
				Log4jUtil.error("", ex);
			} finally {
				// lock.unlock();
				isRun = false;
				System.out.println("task end.." + DateUtils.getNowDateTime());
			}
		}
	}
}
