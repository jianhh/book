	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
    
	<form id="saveForm" class="editor-form">
		<input type="hidden" name="advertiseId" value="${(obj.advertiseId)?if_exists}"/>
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					广告管理<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table>
				<tr class="info">
					<td colspan="2">
						<span class="span-title">广告名称：</span>
						<input type="text" name="advertiseName" style="width:364px;" value="${(obj.advertiseName)?if_exists}" class="{required: true, maxlength:32, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入广告名称"  />
						<span  style="color:red;">*</span>
					</td>
					
				</tr>
				<tr>
					<td>
						<span class="span-title">广告位置：</span>
						<select data-placeholder="广告位置" class="chzn-select {required: true}" name="advertisePosition" style="width:129px;">
					       <#list adlx as ad>
								<option value="${ad.code}" <#if obj?if_exists && obj.advertisePosition==ad.code>selected</#if> >${ad.value}</option>
						   </#list>
                        </select>
						<span  style="color:red;">*</span>
					</td>
					<td>
						<span class="span-title">是否启用：</span>
						<input type="radio" name="isEnable" value="0" <#if !(obj.isEnable)?exists || ((obj.isEnable)?exists && obj.isEnable != 1)>checked</#if>>
						<span class="lbl">&nbsp;不启用&nbsp;</span>
						<input type="radio" name="isEnable" value="1" <#if (obj.isEnable)?exists && obj.isEnable == 1>checked</#if>>
						<span class="lbl">&nbsp;启用&nbsp;</span>
						<span  style="color:red;"></span>
			        </td>				
				</tr>
				<tr class="info">
					<td  >
						<span class="span-title">链接URL地址：</span>
						<input type="text" name="advertiseUrl"   value="${(obj.advertiseUrl)?if_exists}" class="{required: true, maxlength:60, messageDirection:'bottom', messageBgColor:'#AE81FF'}" placeholder="输入链接地址"  />
						<span  style="color:red;">*</span>
					</td>
					<td  >
						<span class="span-title">排序码：</span>
						<select data-placeholder="广告位置"   class="chzn-select {required: true}" name="advertiseSort" placeholder="输入数字越小优先级越高" style="width:129px;">
					       <#list 1..10 as num>
				       			<option value="${num}">${num}</option>
					       </#list>
					       
                        </select>
                        <span  style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2" style="padding:5px 0px;">
						<span class="span-title">广告图片：</span>
						<span>
							<a class='btn btn-mini btn-info' title="上传图片"  id="advertisePic" >上传图片</a>
						</span>
						<span style="color:red;">*</span>
					</td>
				</tr>
				<tr class="info">
					<td colspan="2" style="padding:10px 0px;">
						<span class="span-title"></span>
						<span>
							<img src="${(obj.advertisePic)?if_exists}" id="imgresNewPath"  style="<#if obj?if_exists><#else>display:none;</#if>height:80px;width:100px;"/>
							<input id="advertisePic1" type="hidden"  name='advertisePic' value="${(obj.advertisePic)?if_exists}" />
						</span>
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
	<script type="text/javascript" src="<@base.js/>/z/zUpload.js"></script>
	<script src="<@operating.js />/business/advertise.js"></script>
	<script type="text/javascript">
		initEditor();
		
	</script>
		
<@operating.bottom />
		