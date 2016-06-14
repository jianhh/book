
<#-- 公共管理 宏模块 -->

<!-- 绝对路径 -->
<#macro basePath>${(BASE_PATH)?default("")}</#macro>

<#macro css><@common.basePath/>/resource/common/css</#macro>
<#macro img><@common.basePath/>/resource/common/images</#macro>
<#macro js><@common.basePath/>/resource/common/js</#macro>

<#macro ckeditorJs><#if springMacroRequestContext.getContextPath()=="/ckeditor"><#else><@common.basePath/>/ckeditor</#if></#macro>
<#macro ckfinderJs><#if springMacroRequestContext.getContextPath()=="/ckfinder"><#else><@common.basePath/>/ckfinder</#if></#macro>


<#macro include>

</#macro>
 
 <!-- 分页使用的宏 -->
<#macro pager pager>

<div class="pagerBar">
	<label><@msg.message "background.pageShowCount"/>:</label>
	<select name="pageSize" id="pageSize">
		<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
			10
		</option>
		<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
			20
		</option>
		<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
			50
		</option>
		<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
			100
		</option>
	</select>&nbsp;&nbsp;
	<span id="pager"></span>
	<input type="hidden" name="pageNumber" id="pageNumber" value="${pager.pageNumber}" />
	<input type="hidden" name="orderBy" id="orderBy" value="${pager.orderBy}" />
	<input type="hidden" name="orderType" id="order" value="${pager.orderType}" />
</div>

<script type="text/javascript" src="<@base.adminJs/>/adminList.js"></script>
<script type="text/javascript">
$().ready( function() {
	$("#pager").pager({
		pagenumber: ${pager.pageNumber},
		pagecount: ${pager.pageCount},
		buttonClickCallback: $.gotoPage
	});
})
</script>

</#macro>
