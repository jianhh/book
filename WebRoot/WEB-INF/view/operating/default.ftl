	<@operating.top "" />
	<link rel="stylesheet" href="<@operating.css />/login/ace.min.css" />
	<link rel="stylesheet" href="<@operating.css />/css-update.css" />
	<div class="container-fluid" id="main-container">
	      <#-- 市人员进入显示所有区，区人员进入暂时不显示，隐藏div>id="page-content"   -->
	      
		 
				
		
				
			
			<#-- 统计分析 -->
            <#--<div id="" class="clearfix page-content">  
				<div class="page-header position-relative">
					<h1>
						数据统计 <small><i class="icon-double-angle-right"></i> </small>
					</h1>
				</div>
			

				<div class="row-fluid">

					<div class="space-6"></div>
					<div class="row-fluid">					
						
						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>
												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Area2D.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'>
													<set name='1' value='999' color='AFD8F8'/>
													<set name='2' value='8570' color='F6BD0F'/>
													<set name='3' value='671' color='8BBA00'/>
													<set name='4' value='494' color='FF8E46'/>
													<set name='5' value='761' color='008E8E'/>
													<set name='6' value='960' color='D64646'/>
													<set name='7' value='629' color='8E468E'/>
													<set name='8' value='622' color='588526'/>
													<set name='9' value='376' color='B3AA00'/>
													<set name='10' value='494' color='008ED6'/>
													<set name='11' value='761' color='9D080D'/>
													<set name='12' value='960' color='A186BE'/>
													</graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Area2D.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>

												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Column3D.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='461' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Column3D.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

					

						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>
												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Doughnut2D.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Doughnut2D.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>

												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Doughnut3D.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Doughnut3D.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>

												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/SSGrid.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/SSGrid.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>

												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Pie3D.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Pie3D.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

						<div class="center">
							<div style="float:left;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>

												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Pie2D.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Pie2D.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div style="float:right;">
								<table border="0" width="50%">
									<tbody>
										<tr>
											<td>

												
												<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="myNext" height="300" width="500">
													<param name="allowScriptAccess" value="always">
													<param name="movie" value="<@operating.basePath/>/resource/background/FusionCharts/Line.swf">
													<param name="FlashVars" value="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang=">
													<param name="quality" value="high">
													<param name="wmode" value="">
													<param name="bgcolor" value="">
													<embed src="<@operating.basePath/>/resource/background/FusionCharts/Line.swf" flashvars="chartWidth=500&amp;chartHeight=300&amp;DOMId=myNext&amp;debugMode=0&amp;registerWithJS=0&amp;dataXML=<graph caption='对比表' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='1' value='462' color='AFD8F8'/><set name='2' value='857' color='F6BD0F'/><set name='3' value='671' color='8BBA00'/><set name='4' value='494' color='FF8E46'/><set name='5' value='761' color='008E8E'/><set name='6' value='960' color='D64646'/><set name='7' value='629' color='8E468E'/><set name='8' value='622' color='588526'/><set name='9' value='376' color='B3AA00'/><set name='10' value='494' color='008ED6'/><set name='11' value='761' color='9D080D'/><set name='12' value='960' color='A186BE'/></graph>&amp;scaleMode=&amp;lang="
													quality="high" name="myNext" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" wmode="transparent" bgcolor="" height="300" width="500">
												</object>
												
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					</div>
				
		</div>
		
	</div>-->
	<!-- /.fluid-container#main-container -->
	<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse" style="float:right;">
	 <i class="icon-double-angle-up icon-only"></i>
	</a>
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src=<@operating.js />/jquery-1.7.2.js'>\x3C/script>");
	</script>

	<script src="<@operating.js />/bootstrap.min.js"></script>
	<!--  page specific plugin scripts -->

	<!--[if lt IE 9]>
		<script type="text/javascript" src="<@operating.js />/login/excanvas.min.js"></script>
		<![endif]-->
	<script type="text/javascript" src="<@operating.js />/login/jquery-ui-1.10.2.custom.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.easy-pie-chart.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.sparkline.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.flot.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.flot.pie.min.js"></script>
	<script type="text/javascript" src="<@operating.js />/login/jquery.flot.resize.min.js"></script>
	<!-- ace scripts -->
	<script src="<@operating.js />/ace-elements.min.js"></script>
	<script src="<@operating.js />/ace.min.js"></script>
	<!-- inline scripts related to this page -->


	<script type="text/javascript">

		$(top.hangge());
	
		$(function() {
			$('.dialogs,.comments').slimScroll({
				height : '300px'
			});

			$('#tasks').sortable();
			$('#tasks').disableSelection();
			$('#tasks input:checkbox').removeAttr('checked').on('click',
					function() {
						if (this.checked)
							$(this).closest('li').addClass('selected');
						else
							$(this).closest('li').removeClass('selected');
					});
			var oldie = $.browser.msie && $.browser.version < 9;
			$('.easy-pie-chart.percentage')
					.each(
							function() {
								var $box = $(this).closest('.infobox');
								var barColor = $(this).data('color')
										|| (!$box.hasClass('infobox-dark') ? $box
												.css('color')
												: 'rgba(255,255,255,0.95)');
								var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
										: '#E2E2E2';
								var size = parseInt($(this).data('size')) || 50;
								$(this).easyPieChart({
									barColor : barColor,
									trackColor : trackColor,
									scaleColor : false,
									lineCap : 'butt',
									lineWidth : parseInt(size / 10),
									animate : oldie ? false : 1000,
									size : size
								});
							})
			$('.sparkline').each(
					function() {
						var $box = $(this).closest('.infobox');
						var barColor = !$box.hasClass('infobox-dark') ? $box
								.css('color') : '#FFF';
						$(this).sparkline('html', {
							tagValuesAttribute : 'data-values',
							type : 'bar',
							barColor : barColor,
							chartRangeMin : $(this).data('min') || 0
						});
					});

			var data = [ {
				label : "social networks",
				data : 38.7,
				color : "#68BC31"
			}, {
				label : "search engines",
				data : 24.5,
				color : "#2091CF"
			}, {
				label : "ad campaings",
				data : 8.2,
				color : "#AF4E96"
			}, {
				label : "direct traffic",
				data : 18.6,
				color : "#DA5430"
			}, {
				label : "other",
				data : 10,
				color : "#FEE074"
			} ];
			var placeholder = $('#piechart-placeholder').css({
				'width' : '90%',
				'min-height' : '150px'
			});
			$.plot(placeholder, data, {

				series : {
					pie : {
						show : true,
						tilt : 0.8,
						highlight : {
							opacity : 0.25
						},
						stroke : {
							color : '#fff',
							width : 2
						},
						startAngle : 2

					}
				},
				legend : {
					show : true,
					position : "ne",
					labelBoxBorderColor : null,
					margin : [ -30, 15 ]
				},
				grid : {
					hoverable : true,
					clickable : true
				},
				tooltip : true, //activate tooltip
				tooltipOpts : {
					content : "%s : %y.1",
					shifts : {
						x : -30,
						y : -50
					}
				}

			});

			var $tooltip = $(
					"<div class='tooltip top in' style='display:none;'><div class='tooltip-inner'></div></div>")
					.appendTo('body');
			placeholder.data('tooltip', $tooltip);
			var previousPoint = null;
			placeholder.on('plothover', function(event, pos, item) {
				if (item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : "
								+ item.series['percent'] + '%';
						$(this).data('tooltip').show().children(0).text(tip);
					}
					$(this).data('tooltip').css({
						top : pos.pageY + 10,
						left : pos.pageX + 10
					});
				} else {
					$(this).data('tooltip').hide();
					previousPoint = null;
				}

			});
			var d1 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.5) {
				d1.push([ i, Math.sin(i) ]);
			}
			var d2 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.5) {
				d2.push([ i, Math.cos(i) ]);
			}
			var d3 = [];
			for (var i = 0; i < Math.PI * 2; i += 0.2) {
				d3.push([ i, Math.tan(i) ]);
			}

			var sales_charts = $('#sales-charts').css({
				'width' : '100%',
				'height' : '220px'
			});
			$.plot("#sales-charts", [ {
				label : "Domains",
				data : d1
			}, {
				label : "Hosting",
				data : d2
			}, {
				label : "Services",
				data : d3
			} ], {
				hoverable : true,
				shadowSize : 0,
				series : {
					lines : {
						show : true
					},
					points : {
						show : true
					}
				},
				xaxis : {
					tickLength : 0
				},
				yaxis : {
					ticks : 10,
					min : -2,
					max : 2,
					tickDecimals : 3
				},
				grid : {
					backgroundColor : {
						colors : [ "#fff", "#fff" ]
					},
					borderWidth : 1,
					borderColor : '#555'
				}
			});
			$('[data-rel="tooltip"]').tooltip();
		})
	</script>
	
<@operating.bottom />
