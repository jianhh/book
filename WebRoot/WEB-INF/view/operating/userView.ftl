	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<link rel="stylesheet" href="<@base.js />/webuploader/webuploader.css" />
	<script src="<@base.js />/webuploader/webuploader.js"></script>
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
    
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
						<input type="text" readonly="readonly" value="${(obj.username)?if_exists}"/>
					</td>
					<td>
						<span class="span-title">密码：</span>
						<input type="text" readonly="readonly" value="${(obj.password)?if_exists}"/>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">性别：</span>
						<input type="text" readonly="readonly" value="<#if !(obj.sex)?exists || obj.sex == 0>女<#else>男</#if>" readonly="readonly" />
					</td>
	 				<td style="padding:5px 0px;">
						<span class="span-title">状态：</span>
						<input type="text" readonly="readonly" value="<#if !(obj.isenable)?exists || obj.isenable == 1>启用<#else>不启用</#if>" readonly="readonly" />
					</td>
				</tr>
				<tr class="info">
					<td  >
						<span class="span-title">出生日期：</span>
						<input type="text" value="${(obj.birthday)?if_exists}" readonly="readonly" />
					</td>
					<td   style="padding:5px 0px;">
						<span class="span-title">邮箱：</span>
						<input type="text" readonly="readonly" value="${(obj.email)?if_exists}" />
					</td>
				</tr>
			
				<tr class="info">
					<td >
						<span class="span-title">手机号：</span>
						<input type="text" readonly="readonly" value="${(obj.phonenumber)?if_exists}"/>
					</td>
	 
					<td   style="padding:5px 0px;">
						<span class="span-title">短号：</span>
						<input type="text"readonly="readonly" value="${(obj.shortphonenumber)?if_exists}" />
					</td>
				</tr>				
				<tr class="info">
					<td>
						<span class="span-title">学校：</span>
						<input type="text" readonly="readonly" value="${(school.dicname)?if_exists}" />
					</td>
					<td>
						<span class="span-title">学院：</span>
						<input type="text"readonly="readonly" value="${(college.dicname)?if_exists}" />					
					</td>
	 			</tr>
	 			<tr class="info">
					<td >
						<span class="span-title">角色：</span>
						<input type="text"readonly="readonly" value="${(obj.roleVo.rolename)?if_exists}" />					
					</td>
				</tr>
	 			<tr class="info">
					<td >
						<span class="span-title">用户头像：</span>
						<img src="${pic.uploadfilepath}" width="120px" alt="" />
					</td>
				</tr>
			</table>
		</div>
		<script src="<@operating.js />/business/user.js"></script>
<@operating.bottom />
		