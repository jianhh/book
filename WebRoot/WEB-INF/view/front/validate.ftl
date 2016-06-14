<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="<@front.js/>/jquery.min.js"></script>
    <script type="text/javascript" src="<@base.js/>/layer/layer.js"></script>
    <style type="text/css">
.main-content {
width: 960px;
margin: 0 auto;
}
.page-site-error img {
max-width: 100%;
margin-top: 44px;
}
.page-site-error-content {
width: 590px;
padding: 55px 0 0 0;
margin: 0 auto;
position: relative;
}
.page-site-error-content h2 {
font-size: 34px;
font-weight: bold;
color: #363636;
}
.page-site-error-content .m-btn {
display: block;
margin: 24px auto;
}
.m-btn > span {
position: absolute;
display: inline-block;
color: #fff;
vertical-align: middle;
top: 0;
left: 0;
width: 100%;
height: 100%;
}
body {
overflow-y: scroll !important;
background: #ececec;
}
.m-btn:hover, .m-btn.hover {
box-shadow: 1px 1px 7px rgba(0, 0, 0, 0.5);
}

.m-btn > span::before {
content: "";
display: inline-block;
vertical-align: middle;
width: 0;
height: 100%;
}
h2 {
display: block;
font-size: 1.5em;
-webkit-margin-before: 0.83em;
-webkit-margin-after: 0.83em;
-webkit-margin-start: 0px;
-webkit-margin-end: 0px;
font-weight: bold;
}
a, a:active, a:visited, a:hover {
text-decoration: none;
cursor: pointer;
}
.m-btn {
text-align: center;
color: #fff;
cursor: pointer;
}
.m-btn > span::before {
content: "";
display: inline-block;
vertical-align: middle;
width: 0;
height: 100%;
}

.m-btn {
  width: 96px;
  height: 32px;
  background-color: #3c80f6;
  border: none;
  border-radius: 3px;
  outline: none;
  position: relative;
  display: inline-block;
  text-align: center;
  color: #fff;
  cursor: pointer;
  vertical-align: middle;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none; }
  .m-btn::before {
    content: "";
    position: absolute;
    background: rgba(0, 0, 0, 0.15);
    width: 0;
    height: 100%;
    left: 50%;
    border-radius: 3px; }
  .m-btn:hover, .m-btn.hover {
    box-shadow: 1px 1px 7px rgba(0, 0, 0, 0.5); }
  .m-btn:hover::before {
    transition: all .2s ease-in;
    width: 100%;
    left: 0;
    top: 0; }
  .m-btn > span {
    position: absolute;
    display: inline-block;
    color: #fff;
    vertical-align: middle;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%; }
    .m-btn > span::before {
      content: "";
      display: inline-block;
      vertical-align: middle;
      width: 0;
      height: 100%; }
  .m-btn.sending > span {
    text-indent: -10000px; }
  .m-btn.loading {
    background: #ccc;
    cursor: not-allowed;
    text-align: center; }
    .m-btn.loading:before, .m-btn.loading > span, .m-btn.loading:after {
      content: '';
      width: 16px;
      height: 16px;
      background-color: rgba(255, 255, 255, 0.7);
      border-radius: 100%;
      display: inline-block;
      position: absolute;
      left: 50%;
      top: 50%;
      margin-top: -8px;
      margin-left: -8px;
      -webkit-animation: bouncedelay 1.2s infinite ease-in-out;
      animation: bouncedelay 1.2s infinite ease-in-out;
      -webkit-animation-fill-mode: both;
      animation-fill-mode: both; }
    .m-btn.loading:before {
      margin-left: -30px;
      -webkit-animation-delay: -0.32s;
      animation-delay: -0.32s; }
    .m-btn.loading > span {
      text-indent: -100000px;
      -webkit-animation-delay: -0.16s;
      animation-delay: -0.16s; }
    .m-btn.loading:after {
      margin-left: 14px; }
  .m-btn.gray, .m-btn.disabled, .m-btn:disabled {
    background-color: gray;
    margin-left: 10px; }
  .m-btn.disabled, .m-btn:disabled {
    cursor: not-allowed; }
body, button, input, select, textarea {
font: 14px/1.5 PingHei, Helvetica, Tahoma, Arial, "Microsoft YaHei", "\5fae\8f6f\96c5\9ed1", SimSun, "\5b8b\4f53", STXihei, "\534e\6587\7ec6\9ed1", Heiti, "\9ed1\4f53", sans-serif;
}

</style>
</head>
<body class="" style="height: auto;">

<div id="main-container" class="main-container main-simple" style="min-height: 90px;">
    <link href="https://res-xiaoman-cn.alikunlun.com/v4//res/web/css/views/site/error.5591a0e0fd50cf4007cfbc4dba723c6c.css" rel="stylesheet" type="text/css">
<div class="main-content page-site-error">
    <img src="https://res-xiaoman-cn.alikunlun.com/v4//res/web/images/views/site/site_error_404.561fa727e5b384993c8a7600c38660a6.jpg">
    <div class="page-site-error-content">
                    <h2 style="text-align: center;">${rm.message}</h2>
                    <input type="hidden" id="re" name="result" value="${rm.state}" />
            <a class="m-btn" href="javascript:goback();"><span style="line-height: 32px;">返回上一层</span></a>
            </div>
</div>
<script>
    function goback(){
        if(window.history.length > 1){
            window.history.go( -1 );
        }else{
            top.location.href = "/";
        }
    }
    $(function(){
    	if($("#re").val()=="true"){
        	
        	setTimeout(function () { 
    			layer.msg("请继续完成剩下的注册步骤，该页面将5秒后关闭");
	  	 	}, 1200);
    		setTimeout(function () { 
    			window.close();
	  	 	}, 6200);
    	}
    	
    });
    
</script></div>
</body></html>
