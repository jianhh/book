	<@front.top "公告-二手书"/>
	<script src="/resource/common/js/laypage/laypage.js"></script>
	<style>
.module{
	width:1130px;
	margin:50px auto;
	padding:20px;
	padding-left:30px;
	padding-right:30px;
}
.article-about {
margin-top: 8px;
display: table;
width: 100%;
font-size: 12px;
}
.article-about .col-left {
vertical-align: middle;
text-align: left;
position: relative;
}
.article-about .col-left>div {
position: relative;
display: inline-block;
}
.article-about>div>div>.u-photo {
width: 28px;
height: 28px;
border-radius: 50%;
overflow: hidden;
margin-right: 5px;
display: inline-block;
vertical-align: middle;
}
.article-about .u-photo img {
display: block;
}
img {
max-width: 100%;
}
.ask-item-r-foot span, .article-about span {
font-size: 12px;
}
.article-about .col-left>.u-datetime {
margin: 0 10px;
}
.article-about .hot-tag-group {
display: inline-block;
vertical-align: middle;
}
.module h1 {
font-size: 26px;
font-weight: 700;
margin: 20px 0;
}
.hot-tag, .hot-tag-igroup .hot-tag:hover {
display: inline-block;
height: 24px;
line-height: 24px;
padding: 0 5px;
border-radius: 2px;
vertical-align: middle;
background-color: #dee7e5;
color: #999;
font-size: 12px;
}
	</style>
	
	<div class="J_Module skin-default">
		<div class="my-box-shadow module">
			<h1>${ad.articletitle}</h1>
			<div class="article-about">
				<div class="col-left">
					<div class="zyhideP" info-layer-index="0">
						<a href="javascript:;" target="_blank" class="u-photo u-photo-btn">
							<img src="${ad.userVo.userpicpath}" alt="">
						</a>
					</div>
					<span class="u-name">${ad.userVo.username}</span>
					<span class="u-datetime">${ad.articletime}</span>
					<span class="hot-tag-group">
						<a class="hot-tag" href="javascript:;" target="_blank">公告</a>
					</span>
				</div>
				<div>
					${ad.articlecontent}
				</div>
			</div>
		</div>
	</div>
	
	<!-- 底部 -->
	<@front.bottom/>