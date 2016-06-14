
<#-- 获取管理后台用户信息 -->
<#function getBackUser>
	<#if Session.back_login_account?exists>
		<#return Session.back_login_account />
	</#if>
	
	<#return null />

</#function>

<#-- 获取资源运营后台用户信息 -->
<#function getFrontUser>
	<#if Session.front_login_account?exists>
		<#return Session.front_login_account />
	</#if>
	
	<#return null />

</#function>

<#-- 判断是否管理员 -->
<#function isAdmin>
   <#if Session.MANAGER_USER?exists>
	<#return tools.getUser().roleIsAdmin == "1" />
   </#if>	
</#function>

<#-- 判断是否社会用户 -->
<#function isSociologyUser>
   <#if Session.MANAGER_USER?exists>
	<#return tools.getUser().roleType == "sociology" />
  </#if>	
</#function>



<#-- 以逗号分隔字符串后判断数组里是否含有指定字符 -->
<#function strHasVal str val>
	<#if str?exists>
		<#return str?split(",")?seq_contains(val?string) />
	<#else>
		<#return false />
	</#if>
</#function>

<#-- 替换html标签 -->
<#function replaceHtml str>
		<#local str = str?replace(">\\s*<", "><", "ir") />
		<#local str = str?replace("&nbsp;", " ", "ir") />
		<#local str = str?replace("<br ?/?>", "\n", "ir") />
		<#local str = str?replace("<([^<>]+)>", "", "ir") />
		<#local str = str?replace("\\s\\s\\s*", " ", "ir") />
		<#local str = str?replace("^\\s*", "", "ir") />
		<#local str = str?replace("\\s*$", "", "ir") />
		<#local str = str?replace(" +", " ", "ir") />
		
		<#return str />
</#function>

<#-- 把数字转成中文 -->
<#function tranNumber2ZH x>
		<#switch x>
         <#case x = 1><#local x ="一" /><#break>
         <#case x = 2><#local x ="二" /><#break>  
		</#switch>
		<#return x />
</#function>


<#-- 替换文章里已被转换的html标签 -->
<#function replaceHtmlForArticle str>
	<#--
	<#local str = str?replace("&lt;", "<", "ir") />
	<#local str = str?replace("&gt;", ">", "ir") />
	-->
	
	<#local str = tools.replaceHtml(str) />
	
	<#return str />
</#function>

<#-- 判断字符串是否为空 -->
<#function exis str = "">
	<#if str?exists && str?string?trim != "">
			<#return true />
	</#if>
	
	<#return false />
</#function>

<#-- 权限检验 -->
<#-- 示例
	// 普通用法
	<#if tools.checkPower("/admin/doadd")>
	    拥有“增加管理员”这个权限即可
	</#if>
	
	// 多条件用法
		// 或关系
	<#if tools.checkPower("/admin/dodel || /admin/doresetpwd")>
	   “删除管理员”或者“重置密码”两个权限拥有其中一个即可
	</#if>
			// 并关系
	<#if tools.checkPower("/admin/dodel && /admin/doresetpwd")>
	   “删除管理员”和“重置密码”两个权限都同时拥有才行
	</#if>
-->
<#function checkPower key>

	<#-- 如果是超级管理员，直接通过 -->
	<#if (Session["adminLoginVo"].adminRoleName)?exists && Session["adminLoginVo"].adminRoleName == "Administrator">
		<#return true />
	</#if>
	
	<#if (Session["adminLoginVo"].adminColumnOperateList)?exists>
	
		<#if (key?index_of("||") > -1)>
			<#-- Or -->
			<#list Session["adminLoginVo"].adminColumnOperateList as op>
				<#list key?split("||") as k>
					<#if k?trim == op.adminColumnOperateCode>
						<#return true />
					</#if>
				</#list>
			</#list>
			
		<#elseif (key?index_of("&&") > -1)>
			<#-- And -->
			<#assign and = 0 />
			<#list key?split("&&") as k>
				<#list Session["adminLoginVo"].adminColumnOperateList as op>
					<#if k?trim == op.adminColumnOperateCode>
						<#assign and = and + 1 />
						<#break />
					</#if>
				</#list>
			</#list>
			<#if and == key?split("&&")?size>
				<#return true />
			</#if>
			
		<#else>
			<#-- Normal -->
			<#list Session["adminLoginVo"].adminColumnOperateList as op>
				<#if key == op.adminColumnOperateCode>
					<#return true />
				</#if>
			</#list>
		
		</#if>
	</#if>
	
	<#return false />

</#function>

<#-- 统计分析柱状图颜色管理  -->
<#function getColor key>
   <#assign colorVal = ["AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526" , "B3AA00", "008ED6", "9D080D", "A186BE"]>
   <#return colorVal[key] />
</#function>
<#-- 统计分析柱状图颜色管理  -->
<#function getColorTwo key>
   <#assign colorVal = ["8E468E", "588526" , "B3AA00", "008ED6", "9D080D", "A186BE"]>
   <#return colorVal[key] />
</#function>
<#-- 统计分析柱状图颜色管理  -->
<#function getColorThree key>
   <#assign colorVal = ["008ED6", "9D080D", "A186BE"]>
   <#return colorVal[key] />
</#function>
<#-- 截取字段 -->
<#macro subContents contents,length>
	<#if contents?if_exists && (contents?length > length)>${contents[0..length]}···<#else>${contents}</#if>
</#macro>
<#-- 截取时间 -->
<#macro subDate contents length>
	<#if contents?if_exists && (contents?length > length)>${contents[0..length]}<#else>${contents}</#if>
</#macro>
<#-- 判断是否为空，为空填充0 -->
<#macro formatNum number>
	<#if number?has_content>${number}<#else>0</#if>
</#macro>
