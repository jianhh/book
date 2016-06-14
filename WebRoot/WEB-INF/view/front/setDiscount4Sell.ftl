	<link rel="stylesheet" href="<@front.css/>/bookdetail/index.css">
	<link rel="stylesheet"	href="<@front.css/>/bookdetail/jquery.spinner.css">
	<style>
		.btn-red{
			width: 80px;
			height: 40px;
			background: #469E3A;
			text-align: center;
			color: center;
			line-height: 40px;
			border-radius: 5px;
			margin:0 auto;
			font-size: 13px;
			color: #fff;
			font-weight: bold;
		}
		body{background:#fff;}
		.btn-red:hover{background:#39B129;}
		body{font: 14px/1.5 "Microsoft Yahei", "Hiragino Sans GB", Helvetica, "Helvetica Neue", Tahoma, Arial, sans-serif;}
	</style>
	<div style="background:#fff" class="detail-summary" id="J_Summary">

		<div class="detail-images" id="J_ImagesSlide">
			<div class="detail-main-image">
				<div class="tab-content">
					<div class="tab-pannel" style="display: block;">
						<img style="width:230px;height:320px;" src="${book.bookpicpath!}">
					</div>
				</div>
			</div>
		</div>
		<form id="setForm">
		<input type="hidden" name="bookid" value="${book.bookid!}" />
		<div class="detail-info-wrap">
			<div class="detail-info">
				<div class="d-title cdj-font">${book.bookname!}</div>
				<div class="d-desc">
					<@tools.subContents book.bookintro?default("暂无简介") 80/>
				</div>
				<div class="d-line">
					<div class="d-date">出版信息：	${book.bookpublish!}(${book.bookpublishtime!})</div>
				</div>
				<div class="d-price">
					原　　价：<em>${book.bookoldprice}</em>元 　　
				</div>
				<div class="d-keywords" style="display: flex;">
					出售数量： <input type="text" name="num" maxlength="2" class="spinner" /> 本
				</div>
				<div class="d-keywords" style="display: inline-flex;">
					折　　扣： <input type="text" name="discount" maxlength="1" class="spinner" /> 折
				</div>
				<div class="d-keywords" style="display: inline-flex;">
					备　　注： <textarea name="remark" cols="30" rows="4" placeholder="该备注提供给买家查看"></textarea>
				</div>
				<br>
				<div class="d-more-info"></div>
			</div>
		</div>
		</form>
			<span style="width:100%;" class="J_Like cdj-item-like">
				<div class="btn-red tbc-like-wrap">
					出售
				</div>
			</span>
		
		<div class="alarm-tip">
			<span class="lx-close"></span>
		</div>
	</div>
	
	<script type="text/javascript" src="<@front.js/>/jquery.js"></script>
	<script type="text/javascript" src="<@front.js/>/jquery.spinner.js"></script>
	<script src="/resource/common/js/layer/layer.js"></script>
	<script>
		$('.spinner').spinner();
		$(".btn-red").click(function(){
			
			$.post("/user/dosell",$("#setForm").serialize(),function(data){
				if(data.result){
					parent.layer.msg("出售成功");
					parent.layer.alert("出售成功",{icon:1},function(index){parent.layer.closeAll();});
				}else{
					parent.layer.msg(data.message);
				}
			});
		});
		$(".decrease").click(function(){return false;});
		$(".increase").click(function(){return false;});
	</script>

	<!-- 底部 -->