	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<style>
		.ace-file-input{
			margin-bottom:0px;
		}
	</style>
    
	    <div id="" class="clearfix page-content">
    		<div class="page-header position-relative" style="padding:0;">
				<h1 style="font-size:14px;">
					书本预览<small><i class="icon-double-angle-right"></i> </small>
				</h1>					
      		 </div>
			<table  border= "1">
				<tr class="info">
					<td>
						<span class="span-title">书名：</span>
						<input type="text" readonly="readonly" value="${(obj.bookname)?if_exists}"/>
					</td>
					<td>
						<span class="span-title">作者：</span>
						<input type="text" readonly="readonly" value="${(obj.bookauthor)?if_exists}"/>
					</td>
				</tr>
				<tr class="info">
					<td>
						<span class="span-title">发布者：</span>
						<input type="text" readonly="readonly" value="${(obj.bookusername)?if_exists}" readonly="readonly" />
					</td>
	 				<td style="padding:5px 0px;">
						<span class="span-title">isbn码：</span>
						<input type="text" readonly="readonly" value="${(obj.bookisbn)?if_exists}" readonly="readonly" />
					</td>
				</tr>
				<tr class="info">
					<td  >
						<span class="span-title">数量：</span>
						<input type="text" value="${(obj.booknum)?if_exists}" readonly="readonly" />
					</td>
					<td   style="padding:5px 0px;">
						<span class="span-title">原价：</span>
						<input type="text" readonly="readonly" value="${(obj.bookoldprice)?if_exists}" />
					</td>
				</tr>
			
				<tr class="info">
					<td >
						<span class="span-title">折扣：</span>
						<input type="text" readonly="readonly" value="${(obj.bookdiscount)?if_exists}"/>
					</td>
	 
					<td   style="padding:5px 0px;">
						<span class="span-title">现价：</span>
						<input type="text"readonly="readonly" value="${(obj.bookprice)?if_exists}" />
					</td>
				</tr>				
				<tr class="info">
					<td>
						<span class="span-title">发布时间：</span>
						<input type="text" readonly="readonly" value="${(obj.bookaddtime)?if_exists}" />
					</td>
					<td>
						<span class="span-title">出版社：</span>
						<input type="text"readonly="readonly" value="${(obj.bookpublish)?if_exists}" />					
					</td>
	 			</tr>
	 			<tr class="info">
					<td >
						<span class="span-title">页数：</span>
						<input type="text"readonly="readonly" value="${(obj.bookpagesize)?if_exists}" />					
					</td>
					<td >
						<span class="span-title">备注：</span>
						<input type="text"readonly="readonly" value="${(obj.bookremark)?if_exists}" />					
					</td>
				</tr>
	 			<tr class="info">
					<td colspan="2">
						<span class="span-title">书本简介：</span>
						<div style="width:500px">${(obj.bookintro)?if_exists}</div>
					</td>
				</tr>
	 			<tr class="info">
					<td colspan="2">
						<span class="span-title">书本封面：</span>
						<img src="${obj.bookpicpath}" width="120px" alt="" />
					</td>
				</tr>
			</table>
		</div>
<@operating.bottom />
		