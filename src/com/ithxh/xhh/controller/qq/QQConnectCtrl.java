package com.ithxh.xhh.controller.qq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.exception.SystemBusyException;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
@RequestMapping(value="qq")
public class QQConnectCtrl extends BaseController{
	
	@RequestMapping(value="/toLogin",method=RequestMethod.GET)
	public void sendRedirectUrl(HttpServletResponse response,HttpServletRequest request){
		
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unused")
	@RequestMapping(value="/connect",method=RequestMethod.GET)
	public void connect(HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();

        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken   = null,
                   openID        = null;
            long tokenExpireIn = 0L;


            if (accessTokenObj.getAccessToken().equals("")) {
                //我们的网站被CSRF攻击了或者用户取消了授权
                //做一些数据统计工作
                System.out.print("没有获取到响应参数");
                throw new SystemBusyException();
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                // 利用获取到的accessToken 去获取当前用的openid
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                
                HttpSession session = request.getSession();

                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                	session.setAttribute("img", new UserInfo(accessToken, openID).getUserInfo().getAvatar().getAvatarURL30());
                    session.setAttribute("nickName", new UserInfo(accessToken, openID).getUserInfo().getNickname());
                    out.write("<script   language=javascript>window.opener.location.href=window.opener.location.href;window.close();</script>");
                } else {
                	out.write("对不起，登录失败！原因："+userInfoBean.getMsg());
                }
            }
        } catch (QQConnectException e) {
        	throw new SystemBusyException();
        }
		
	}

}
