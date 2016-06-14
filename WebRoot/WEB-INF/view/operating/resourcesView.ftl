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
					资源管理<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table  border= "1">
				<tr class="info">
					<td>
						<span class="span-title">原文件名：</span>
						${obj.uploadoldfilename}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">新文件名：</span>
						${obj.uploadnewfilename}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">文件路径：</span>
						${obj.uploadfilepath}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">文件大小：</span>
						${obj.uploadfilesize}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">转换大小：</span>
						${obj.uploadtransize}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">文件后缀：</span>
						${obj.uploadfileext}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">文件类型：</span>
						${obj.uploadmimetype}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">上传时间：</span>
						${obj.uploadtime}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">上传ip地址：</span>
						${obj.uploadip}
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">上传状态：</span>
						<#if obj.uploadstatus=='0'>临时保存<#elseif obj.uploadstatus=='1'>正式文件<#elseif obj.uploadstatus=='2'>永远保留文件<#elseif obj.uploadstatus=='3'>待删除<#else>立马删除</#if>
					</td>
				</tr>
	 			<tr class="info">
					<td >
						<span class="span-title">用户头像：</span>
						<img src="${obj.uploadfilepath}" width="120px" alt="" />
					</td>
				</tr>
			</table>
		</div>
		<script src="<@operating.js />/business/user.js"></script>
<@operating.bottom />
		