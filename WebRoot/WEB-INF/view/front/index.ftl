	<@front.top "首页-二手书"/>
	<style>
		.layout:after, .main-wrap:after, .col-sub:after, .col-extra:after, .tb-module:after, .clearfix:after {
content: '.';
display: block;
height: 0;
line-height: 0;
clear: both;
visibility: hidden;
}
	</style>
	<div class="g-banner">
		<div class="g-banner-content">
			
			<#if adList?if_exists>
				<#list adList as ad>
					<#if ad_index==0>
						<div class="banner-slide slide-active"
							style="background-image: url(${ad.picpath!}); display: none;cursor:pointer;">
							<a target="_blank" href="/index/adetail/${ad.articleid}" style="cursor: pointer;" class="click-help"></a>
							<div class="inner">
							</div>
						</div>
					<#else>
						<div class="banner-slide"
							style="background-image: url(${ad.picpath!}); display: none;cursor:pointer;">
							<a target="_blank" href="/index/adetail/${ad.articleid}" style="cursor: pointer;" class="click-help"></a>
							<div class="inner">
							</div>
						</div>
					</#if>
				</#list>
			<#else>
				<div class="banner-slide slide-active"
					style="background-image: url(http://www.imooc.com/static/img/index/banner2.jpg); display: block;">
					<a href="javascript:;" style="cursor: default" class="click-help"></a>
					<div class="inner">
					</div>
				</div>
				<div class="banner-slide"
					style="background-image: url(http://img.mukewang.com/56dd2f5c000174e620000600.jpg); display: none;">
					<a href="http://www.imooc.com/article/5321"
						data-ast="bigbanner_1_239" class="click-help"></a>
					<div class="inner">
					</div>
				</div>
			</#if>
		</div>
		<div class="banner-dots">
		</div>
		<a href="javascript:;" class="banner-anchor prev">上一张</a> 
		<a href="javascript:;" class="banner-anchor next">下一张</a>
	</div>
	<script type="text/javascript" src="<@front.js/>/scroll.js"></script>
	<!-- 主体 -->
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-title cdj-font tb-lazyload"
				style="background:none" id="cdj_floor2">
				<p class="cdj-title">新 · 书</p>
				<p class="cdj-desc">最新二手书，赶紧来购吧</p>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-trendy-items-promo tb-lazyload" style="background:none">
				<div class="cdj-container">
					<div class="cdj-box cdj-row cdj-row-4">
						<#list newList as book>
							<div class="cdj-col">
								<div class="cdj-item-list my-box-shadow" style="border-color: rgb(220, 220, 220);">
									<span class="cdj-flag">新书</span>
									<div class="cdj-img-warp">
										<a class="cdj-link" href="<@front.basePath/>/book/detail/${book.bookid?if_exists}"
											target="_blank" title="${book.bookname!}"><img style="width:288px;height:350px;" src="${book.bookpicpath!}">
										</a>
									</div>
									<p class="cdj-p">
										<a href="<@front.basePath/>/book/detail/${book.bookid?if_exists}"
											target="_blank" title="${book.bookname!}">${book.bookname!}</a>
									</p>
									<p class="cdj-price">
										纸书价格<b>${book.bookoldprice!}</b>元
									</p>
									<div class="cdj-mesg">${book.bookaddtime!}发布</div>
								</div>
							</div>
						</#list>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<!--[if gte IE 8]>
<style>
.module .cdj-2015-more-button .btn-discover i{
    display: none;
}
.module .cdj-2015-more-button .btn-discover:hover i{
    display: block;
}
</style>
<![endif]-->
			<div class="tb-module cdj-2015-more-button tb-lazyload"
				style="background:none">
				<div class="cdj-button">
					<a href="<@front.basePath/>/book" class="btn-discover"
						target="_blank" style="width: 20px;"><span class="l"></span><span
						class="r"></span><i class="txt"
						style="width: 238px; display: none;">还想看更多？点我试试！<b></b></i></a>
				</div>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-title cdj-font tb-lazyload"
				style="background:#f7f7f7" id="cdj_floor1">
				<p class="cdj-title">随便逛逛</p>
				<p class="cdj-desc">书中自有黄金屋，书中自有颜如玉</p>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-trendy-items-promo tb-lazyload"
				style="background:#f7f7f7">
				<div class="cdj-container">
					<div class="cdj-box cdj-row cdj-row-4">
						<#list randList as book>
							<div class="cdj-col">
								<div class="cdj-item-list my-box-shadow">
									<div class="cdj-img-warp">
										<a class="cdj-link"	href="<@front.basePath/>/book/detail/${book.bookid?if_exists}"
											target="_blank" title="${book.bookname!}"> <img style="width:288px;height:350px;"	src="${book.bookpicpath!}">
										</a>
									</div>
									<p class="cdj-p">
										<a
											href="<@front.basePath/>/book/detail/${book.bookid?if_exists}"
											target="_blank" title="${book.bookname!}">${book.bookname!}</a>
									</p>
									<p class="cdj-price">
										约<b>${book.bookoldprice}</b>元
									</p>
									<div class="cdj-mesg">
										${book.bookaddtime}发布
									</div>
								</div>
							</div>
						</#list>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<!--[if gte IE 8]>
<style>
.module .cdj-2015-more-button .btn-discover i{
    display: none;
}
.module .cdj-2015-more-button .btn-discover:hover i{
    display: block;
}
</style>
<![endif]-->
			<div class="tb-module cdj-2015-more-button tb-lazyload"
				style="background:#f7f7f7">
				<div class="cdj-button">
					<a href="<@front.basePath/>/book" class="btn-discover"
						target="_blank"><span class="l"></span><span class="r"></span><i
						class="txt"></i></a>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-title cdj-font tb-lazyload"
				style="background:none" id="cdj_floor3">
				<p class="cdj-title">最新公告</p>
				<p class="cdj-desc">本站公告，领略最新资讯</p>
			</div>
		</div>
	</div>
	<div class="J_Module skin-default">
		<div class="module">
			<div class="tb-module cdj-2015-trend-special-ver2 tb-lazyload">
				<div class="cdj-container sort-wrap">
					<ul class="sort-container cdj-font">
						<#list myadList as my>
							<li class="item size1">
								<a href="/article/detail/${my.articleid!}" target="_blank"> 
									<img src="${my.picpath!}">
									<span class="title">${my.articletitle!}</span> 
									<span class="ext">
										<i class="icon-clock"></i>
										<span class="date">${my.articletime!}</span>
									</span>
									<span class="desc"><@tools.subContents my.articlecontent?default('暂无简介') 35 /></span>
								</a>
							</li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
	</div>-->
	
	<!-- 底部 -->
	<@front.bottom/>