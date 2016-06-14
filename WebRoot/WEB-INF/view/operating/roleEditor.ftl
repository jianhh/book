	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
    
	<form id="saveForm" class="editor-form">
		<input type="hidden" name="roleid" value="${(obj.roleid)?if_exists}"/>
		
	    <div id="" class="clearfix page-content">
			<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					角色管理相关资料填写 <small><i class="icon-double-angle-right"></i></small>
				</h1>					
	  		 </div>
			<table>			
				<tr class="info">
					<td colspan="2">
						<span class="span-title">角色名称：</span>
					   	<input type="text" name="rolename" class="{required: true, maxlength: 30, messageDirection:'bottom', messageBgColor:'#AE81FF'}" value="${(obj.rolename)?if_exists}" placeholder="输入角色名称" title="角色名称"/>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2">
						<span class="span-title">是否启用：</span>
	                   	<input type="radio" name="roleisenable" value="1" <#if !(obj.roleisenable)?exists || obj.roleisenable == 1>checked</#if>>
	                   	<span class="lbl">&nbsp;&nbsp;是&nbsp;&nbsp;</span>
						<input type="radio" name="roleisenable" value="0" <#if (obj.roleisenable)?exists && obj.roleisenable == 0>checked</#if>>
						<span class="lbl">&nbsp;&nbsp;否&nbsp;&nbsp;</span>
						<span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2">
						<span class="span-title">选择栏目权限：</span>
						<select multiple data-placeholder="栏目列表" name="privileges" class="chzn-select {required: true, messageDirection:'bottom', messageBgColor:'#AE81FF'}" style="vertical-align:top; margin-bottom:0 !important; width:390px;">
							<option value=""></option>
							<#if columnList?exists>
								<#list columnList as cl>
									<optgroup label="${cl.privilegename}">
										<#if (cl.list)?exists && cl.list?size gt 0>
											<#list cl.list as son>
												<#assign isSelected = "" />
												<#if (obj.list)?exists>
													<#list obj.list as c>
														<#if c.privilegeid == son.privilegeid>
															<#assign isSelected = "selected" />
														</#if>
													</#list>
												</#if>
											
												<option value="${son.privilegeid}" ${isSelected}>
													${son.privilegename}
												</option>
											</#list>
										</#if>
									</optgroup>
								</#list>
							</#if>
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
	
	<!-- 引入 -->
	
	<script type="text/javascript" src="<@operating.js />/business/role.js"></script>
	
	<script type="text/javascript">
		initEditor();
	</script>
	
		
<@operating.bottom />
		