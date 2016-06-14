	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<link rel="stylesheet" href="<@base.js />/webuploader/webuploader.css" />
	<script src="<@base.js />/webuploader/webuploader.js"></script>
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
    
	<form id="saveForm" class="editor-form">
		<input type="hidden" name="userid" value="${(obj.userid)?if_exists}"/>
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					用户管理<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table  border= "1">
				<tr class="info">
					<td>
						<span class="span-title">账号：</span>
						<input type="text" name="username" class="{required: true, maxlength:24, messageDirection:'bottom', messageBgColor:'#AE81FF'}" value="${(obj.username)?if_exists}" placeholder="输入账号"  />
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">密码：</span>
						<input type="password" name="password" value="${(obj.password)?if_exists}" placeholder="输入密码"  />
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">性别：</span>
						<input type="radio" name="sex" value="0" <#if !(obj.sex)?exists || obj.sex == '女'>checked</#if>>
						<span class="lbl">&nbsp;女&nbsp;</span>
						<input type="radio" name="sex" value="1" <#if (obj.sex)?exists && obj.sex == '男'>checked</#if>>
						<span class="lbl">&nbsp;男&nbsp;</span>
						<span  style="color:red;">*</span>
					</td>
	 				<td style="padding:5px 0px;">
						<span class="span-title">状态：</span>
						<input type="radio" name="isenable" value="yes" <#if !(obj.isenable)?exists || obj.isenable == 'yes'>checked</#if>>
						<span class="lbl">&nbsp;启用&nbsp;</span>
						<input type="radio" name="isenable" value="no" <#if (obj.isenable)?exists && obj.isenable == 'no'>checked</#if>>
						<span class="lbl">&nbsp;不启用&nbsp;</span>
					</td>
				</tr>
				<tr class="info">
					<td  >
						<span class="span-title">出生日期：</span>
						<input type="text" name="birthday" value="${(obj.birthday)?if_exists}" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})" readonly="readonly"  class="{required: false, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入生日日期"  />
					</td>
					<td   style="padding:5px 0px;">
						<span class="span-title">邮箱：</span>
						<input type="text" name="email" value="${(obj.email)?if_exists}" class="{required: false, email:true,maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入邮箱"  />
					</td>
				</tr>
			
				<tr class="info">
					<td >
						<span class="span-title">手机号：</span>
						<input type="text" name="phonenumber" value="${(obj.phonenumber)?if_exists}" class="{required: false, mobile:true,maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入手机号"  />
					</td>
	 
					<td   style="padding:5px 0px;">
						<span class="span-title">短号：</span>
						<input type="text" name="shortphonenumber" value="${(obj.shortphonenumber)?if_exists}" class="{required: false, digits:true,maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入短号"  />
					</td>
				</tr>				
				<tr class="info">
					<td>
						<span class="span-title">学校：</span>
						<select name="schoolid" onchange="selectCollege(this)"  data-placeholder="请选择学校"  class="chzn-select {required: true}" style="vertical-align:top; margin-bottom:0 !important;">
							<option value="">请选择学校</option>
							<#list sList as s>
								<option value="${s.dicid}" <#if obj?if_exists && s.dicid==obj.schoolid>selected</#if>>${s.dicname}</option>
							</#list>
						</select>						
						<span style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">学院：</span>
						<select name="collegeid" id="college"  data-placeholder="请选择学院"  class="chzn-select {required: true}" style="vertical-align:top; margin-bottom:0 !important;">
							<option value="">请选择学院</option>
							<#list cList as c>
								<option value="${c.dicid}" <#if obj?if_exists && c.dicid==obj.collegeid>selected</#if>>${c.dicname}</option>
							</#list>
						</select>						
						<span style="color:red;">*</span>
					</td>
	 			</tr>
	 			<tr class="info">
					<td >
						<span class="span-title">角色：</span>
						<select name="roleVo.roleid"  data-placeholder="请选择角色"  class="chzn-select {required: true}" style="vertical-align:top; margin-bottom:0 !important;">
							<option value="">请选择角色</option>
							<#list allList as role>
								<option value="${role.roleid}" <#if obj.roleVo?if_exists && role.roleid==obj.roleVo.roleid>selected</#if> >${role.rolename}</option>
							</#list>
						</select>						
						<span style="color:red;">*</span>
					</td>
				</tr>
	 			<tr class="info" style="height:auto;">
					<td style="vertical-align: middle;" colspan="2">
						<span class="span-title">用户头像：</span>
						<span style="width:129px;">
						<a title="重复上传将覆盖旧文件" id="userpic" >上传头像</a> 
						</span>
						<span class="upload-tip">（分辨率:100px*80px &nbsp;&nbsp;图片类型:jpg、png&nbsp;&nbsp;大小:5M以内）</span>
					</td>
				</tr>
				<tr class="info" style="height:auto;">
					<td style="padding-left:110px;padding-top:10px;" id="js-face-reault" rowspan="3" colspan="1">
						<#if pic?if_exists>
							<div class="face-result-inner"><img src="${(pic.uploadfilepath)?if_exists}" width="120"><span class="rm-face-reault">删除</span></div>
						</#if>
					</td>
					<input type="hidden" name="userpic" value="${(obj.userpic)?if_exists}" id="art-face" />
				</tr>
			</table>
			<table style="margin:10px auto;">	
				<tr>
					<td style="text-align: center;">
						<a class="btn btn-mini btn-primary" onclick="save()">保存</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="btn btn-mini btn-danger" onclick="cancel()">取消</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
		
	<!-- 引入 -->
	<script type="text/javascript" src="<@base.js/>/laydate/laydate.js" charset="utf-8"></script>
	<script src="<@operating.js />/business/user.js"></script>
	<script>
		initEditor();
	</script>
		
<@operating.bottom />
		