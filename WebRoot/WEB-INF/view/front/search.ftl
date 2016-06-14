	<@front.top "搜索-二手书"/>
	<link rel="stylesheet" href="<@front.css/>/search.css" type="text/css">
	<style>
		.btn-text-clear:hover{
		   background-position: 0 -14px;
		}
	</style>
	<div id="main" style="display:block;background:url(/resource/front/images/sbg1.jpg);">
		<div class="search-container">
			<!--banner start-->
			<div class="search-banner">
				<div class="search-box clearfix" data-search="search-page">
					<div class="suggest-input-box l">
						<input id="search_key" class="suggest-input J-suggest-input"
							 placeholder="请输入想搜索的内容..."
							type="text" value=""> <s
							class="btn-text-clear" id="clear-content" title="清空">清空</s>
					</div>
					<input type="button" class="btn-search"
						id="search_btn" value="搜索">
				</div>
			</div>
		</div>
	</div>
	<!-- 搜索主体开始 -->
	<div id="doc" class="page">

		<div id="bd" class="bd">

			<div class="bd-wrap">
				<div class="body">

					<div class="main">

						<div class="left-side">
							<div class="main-hd">
								<div class="search-toolbar " style="display: block;">
									<a href="javascript:;" data-od="0"
										class="filter-item filter-relative cur"> 相关 <span
										class="icon"></span>
									</a> <a href="javascript:;" class="filter-item filter-sale ">热度 <span class="icon"></span>
									</a> <a href="javascript:;" class="filter-item double-filter-item price-filter-item ">
										价格 <span class="icon"></span>
									</a> <a href="javascript:;"  class="filter-item double-filter-item publish-filter-item ">
										出版时间 <span class="icon"></span>
									</a>  <a href="javascript:;" data-style="list"
										class="style-way list cur-style"> <span class="icon"></span>
									</a>

								</div>
							</div>
							<div class="main-bd">
								<div class="main-content">
									<div class="yd-search-page-up"></div>
									<div class="yd-search-tips">为您找到相关的书籍<span id="totalBook"></span>本</div>
									<div class="yd-search-books-wrap">
										<div id="data_list" class="yd-search-books-content">
											
										</div>
										<#-- 模板 -->
										<div id="data_temp" style="display:none;" class="yui-ebook-full listbook-item">
											<a class="ebook-cover ebook-item log-xsend"
												href="/book/detail/" data-id target="_blank">
												<img src="" class="defbook" data-img>
											</a> 
											<a class="ebook-title ebook-item log-xsend"
												href="/book/detail/" data-id target="_blank" "><em data-name></em></a> 
												<span class="ebook-publisher" data-publish></span>
											<span class="ebook-score ebook-item"> </span> 
											<span class="ebook-author ebook-item"> <label>作者</label><span data-author></span></span>
											<span class="ebook-abstract ebook-item"> <label>介绍</label><span data-intro></span></span>
											<div class="ebook-price ebook-item">
												<span data-price></span>
												<div class="op-wrap">
													<span id="ca"></span>
													<#--<a href="javascript:;" dataid target="_blank" class="buybook yui-btn yui-btn-white2 yui-btn-small read-btn log-xsend" >加入书袋</a>-->
													<a href="javascript:;" dataid target="_blank" class="sellbook yui-btn yui-btn-white2 yui-btn-small read-btn log-xsend" >出售</a>
												</div>
											</div>
										</div>
									</div>
									<div class="yd-search-page-down">
										<div class="yui-pager pager-center">
											<div class="pager">
												<div class="pager-inner">
													<#--分页 -->
													<div id="pager" style="margin-bottom: 30px;">
														
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="right-side">
							<div class="recom-books-wrap">
								<p class="tip-text">精彩图书推荐</p>
								<div class="recom-books-list">
									<#if randList?if_exists>
										<#list randList as rand>
											<div class="yui-ebook-mini">
												<a class="ebook-cover ebook-item log-xsend"
													href="/book/detail/${rand.bookid!}" target="_blank"
													title="${rand.bookname!}"> <img src="${rand.bookpicpath!}">
												</a> <a class="ebook-title ebook-item log-xsend"
													href="/book/detail/${rand.bookid!}" target="_blank"
													title="${rand.bookname!}"><@tools.subContents rand.bookname?default('佚名')  10/></a> <span style="display:block" class="ebook-score ebook-item">
													<span class="ebook-author ebook-item"> <label>作者</label>${rand.bookauthor!}
												</span> <span class="ebook-abstract ebook-item"> <label>简介</label>
												</span> <span class="ebook-price ebook-item"> <label>￥</label>${rand.bookoldprice}
												</span>
											</div>
										</#list>
									</#if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!--  -->
	<form id="search_form">
		<input type="hidden" name="innerPPAndRss" value="BOOKNAME#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKISBN#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKAUTHOR#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKPUBLISH#like" />
		<input type="hidden" name="innerPPAndRss" value="BOOKINTRO#like" />
		<input type="hidden" name="innerKeyOrValue" value="${key!}"/>
		<input type="hidden" name="pageNumber" value="1" />
		<input type="hidden" name="pageSize" value="5" />
		<input type="hidden" name="orderBy" value=""/>
	</form>
	<!-- 搜索主体结束 -->
	<script src="/resource/front/js/jquery.js"></script>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<script src="/resource/front/js/business/search.js"></script>
	<script>
		search();
	</script>
	<!-- 底部 -->
	<@front.rightBox/>
	<@front.bottom/>