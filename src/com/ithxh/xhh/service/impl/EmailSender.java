package com.ithxh.xhh.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.Log4jUtil;
import com.ithxh.xhh.service.BookSendEmailService;
import com.ithxh.xhh.vo.formbean.BookSendEmailVo;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("emailSender")
public class EmailSender {

	private Logger log = Logger.getLogger(EmailSender.class);

	@Autowired
	JavaMailSenderImpl mailSender;

	@Autowired
	protected FreeMarkerConfigurer freeMarkerConfigurer; // FreeMarker的技术类

	@Autowired
	private ThreadPoolTaskExecutor  taskExecutor;
	
	@Autowired
	private BookSendEmailService bookSendEmailService;

	/** 编码必须为UTF-8格式 */
	private void sendSimpleHtmlEmail(final String subject, final String[] mailTo, final String content) throws MessagingException {
		
		

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper messageHelp = new MimeMessageHelper(message, true/* , "GBK" */);
		messageHelp.setSubject(subject);// 设置邮件主题
		try {
			messageHelp.setFrom(getForm(),"何小灰二手书");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			messageHelp.setFrom(getForm());
		}// 设置发送方地址
		messageHelp.setTo(mailTo);// 设置接收方的email地址
		messageHelp.setText(content, true);// true表示内容解析成html; 如果您不想解析文本内容, 可以使用false或者不添加这项
		mailSender.send(message);
		
		List<BookSendEmailVo> list = new ArrayList<BookSendEmailVo>();
		for (int i = 0; i < mailTo.length; i++) {
			BookSendEmailVo email = new BookSendEmailVo();
			email.setSendid(IDGenerator.uuidGenerate());
			email.setSendcontent(content);
			email.setSendfrom(getForm());
			email.setSendto(mailTo[i]);
			email.setSendsubject(subject);
			email.setSendstatus("1");
			email.setSendtime(DateUtils.getNowDateTime());
			list.add(email);
		}
		
		bookSendEmailService.add(list);
	}

	/** template就是模板文件的名字, dataMap中存放的是要替换模板中字段的值 */
	private void sendTemplateEmail(final Map<String, Object> map, final String subject, final String[] mailTo, final String template) throws MessagingException {

		try {
			// 从模板中加载要发送的内容
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(template);
			// 解析模板并替换动态数据，最终content将替换模板文件中的${content}标签。
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
			log.info("content:" + content);
			sendSimpleHtmlEmail(subject, mailTo, content);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public void sendSyncSimpleEmail(final String subject, final String[] mailTo, final String content) {
		System.out.println(mailTo+"--"+content);
		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {

				try {
					sendSimpleHtmlEmail(subject, mailTo, content);
				} catch (MessagingException e) {

					Log4jUtil.error("", e);
					e.printStackTrace();
					log.error("send simple html email to [" + mailTo + "] when exception occour, exception message is " + e.toString());
					try {
						sendSimpleHtmlEmail(subject, mailTo, content);
					} catch (MessagingException e1) {
						e1.printStackTrace();
						try {
							sendSimpleHtmlEmail(subject, mailTo, content);
						} catch (MessagingException e2) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}

	/**
	 * @category 异步发送模版邮件
	 * @param dataMap
	 *            与HTML 模板对应的变量数据
	 * @param subject
	 *            邮件的标题
	 * @param mailTo
	 *            接收邮件的人
	 * @param vmfile
	 *            模板文件的路径
	 */
	public void sendSyncTemplateEmail(final Map<String, Object> dataMap, final String subject, final String[] mailTo, final String template) {

		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {

				try {
					sendTemplateEmail(dataMap, subject, mailTo, template);
					System.out.println("发送邮件完成");
				} catch (MessagingException e) {
					// e.printStackTrace();
					Log4jUtil.error("", e);
					log.error("send template email to [" + mailTo + "] when exception occour, exception message is " + e.toString());
					try {
						sendTemplateEmail(dataMap, subject, mailTo, template);
						System.out.println("发送邮件完成");
					} catch (MessagingException e1) {
						// e.printStackTrace();
						Log4jUtil.error("", e);
						log.error("send template email to [" + mailTo + "] when exception occour, exception message is " + e.toString());
						try {
							sendTemplateEmail(dataMap, subject, mailTo, template);
							System.out.println("发送邮件完成");
						} catch (MessagingException e2) {
							// e.printStackTrace();
							Log4jUtil.error("", e);
							log.error("send template email to [" + mailTo + "] when exception occour, exception message is " + e.toString());
						}
					}
				}
			}
		});
	}

	private String getForm() {

		return mailSender.getUsername();
	}
}
