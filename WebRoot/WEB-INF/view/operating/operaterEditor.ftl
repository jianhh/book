	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
    
	<form id="saveForm" class="editor-form">
		<input type="hidden" name="operaterId" value="${(obj.operaterId)?if_exists}"/>
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					运营用户相关信息<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table>
				<tr class="info">
					<td>
						<span class="span-title">登录名/工号：</span>
						<input type="text" name="operaterAccount" value="${(obj.operaterAccount)?if_exists}" class="{required: true, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入登录名/工号" title="用作登录平台"/>
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">姓名：</span>
						<input type="text" name="operaterRealName" value="${(obj.operaterRealName)?if_exists}" class="{required: true, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入姓名" title="真实姓名"/>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">性别：</span>
						<input type="radio" name="operaterSex" value="0" <#if !(obj.operaterSex)?exists || obj.operaterSex == 0>checked</#if>>
						<span class="lbl">&nbsp;男&nbsp;</span>
						<input type="radio" name="operaterSex" value="1" <#if (obj.operaterSex)?exists && obj.operaterSex == 1>checked</#if>>
						<span class="lbl">&nbsp;女&nbsp;</span>
						<span  style="color:red;">*</span>
					</td>
					
					<td>
						<span class="span-title">电话：</span>
						<input type="text" name="operaterTel" value="${(obj.operaterTel)?if_exists}" class="{required: true,mobile: true,maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入电话" title="用户手机号码"/>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">邮箱：</span>
						<input type="text" name="operaterEmail" value="${(obj.operaterEmail)?if_exists}" class="{required: true,email:true,maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入邮箱" title="用户的电子邮箱"/>
						<span  style="color:red;">*</span>
					</td>
					<td colspan="2">
						<span class="span-title">登录密码：</span>
						<input type="password" name="operaterPwd" value="${(obj.operaterPwd)?if_exists}" class="{required: true, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入登录密码" title="平台用户的登录密码"/>
						<span  style="color:red;">*</span>
					</td>
				</tr>	
				<tr class="info">
					<td colspan="3">
						<span class="span-title">角色选择：</span>
							<select name="roleId" placeholder="角色选择" title="角色选择" style="height:27px" >
								<#list sysRolelist as sysRole>
									<option value="${(sysRole.roleId)?if_exists}" <#if (sysUserRole.roleId2)?exists && (sysRole.roleId)?exists && sysUserRole.roleId2 == sysRole.roleId>selected = "selected"</#if>>${(sysRole.roleName)?if_exists}</option>
								</#list>
							</select>
						<span  style="color:red;">*</span>
					</td>
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
	
	<div id="save_loading" class="center" style="display:none">
		<br/><br/><br/><br/>
		<img src="<@operating.img />/jiazai.gif" />
		<br/>
		<h4 class="lighter block green"></h4>
	</div>
	
	<!-- 引入 -->
	<script src="<@operating.js />/business/operater.js"></script>
	<script type="text/javascript">
		initEditor();
		
	</script>
		
<@operating.bottom />
		