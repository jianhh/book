<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="<@front.css/>/order-address.css" />
  <script src="<@front.js/>/jquery.min.js"></script>
  <script src="<@base.js/>/layer/layer.js"></script>
 </head>
 <body>
  <div class="form-box">
	<form class="saveForm">
	<input type="hidden" name="addressid" value="${(obj.addressid)?if_exists}" />
	<div class="item item-postcode" id="J_ItemPostCode">
		<span class="item-label tsl" data-phase-id="d_p_postCode">所在地区 </span>
		<div class="item-warp">
			<select name="province" title="省" id="pe"  onchange="doChangep(this)">
				<#if list?if_exists>
					<#list list as a>
						<option value="${a.code!}" <#if obj?if_exists && a.code==obj.province>selected</#if>>${a.area!}</option>
					</#list>
				</#if>
			</select>
			<select name="city" title="市" id="city" onchange="doChangec(this)">
				<#if clist?if_exists>
					<#list clist as c>
						<option value="${c.code!}" <#if obj?if_exists &&  c.code==obj.city>selected</#if>>${c.area!}</option>
					</#list>
				</#if>
			</select>
			<select name="county" title="区" id="country">
				<#if colist?if_exists>
					<#list colist as c>
						<option value="${c.code!}" <#if obj?if_exists &&  c.code==obj.county>selected</#if>>${c.area!}</option>
					</#list>
				</#if>
			</select>
		</div>
		
		<div class="msg hide" id="J_MsgPostCode">
			<div class="msg-cnt tsl"></div>
		</div>
	</div>

	<div class="item item-street" id="J_ItemStreet">
		<span class="item-label tsl" data-phase-id="d_p_addressDetail">详细地址 <i>*</i></span>
		
		<div class="ks-combobox" id="J_ComboboxStreet" aria-pressed="false">
			<div class="ks-combobox-input-wrap">
				<textarea class="ks-combobox-input i-ta tsl" name="detailaddress" id="J_Street" placeholder="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息">${(obj.detailaddress)?if_exists}</textarea>
			</div>
		</div>
		<div class="msg-box">
			<div class="msg msg-error hide" id="J_MsgStreet">
				<i></i>
				<div class="msg-cnt"></div>
			</div>
		</div>
	</div>
	
	<div class="item item-postcode" id="J_ItemPostCode">
		<span class="item-label tsl">邮政编码 </span>
		<div class="item-warp">
		<input name="postalcode" maxlength="16" placeholder="如您不清楚邮递区号，请填写000000"  class="i-text tsl" type="text" value="${(obj.postalcode)?if_exists}">
		</div>
		
		<div class="msg hide" id="J_MsgPostCode">
			<div class="msg-cnt tsl"></div>
		</div>
	</div>

	<div class="item item-name" id="J_ItemName">
		<span class="item-label tsl">收货人姓名 <i>*</i></span>
		<div class="item-warp">
		<input name="consignee" class="i-text tsl" type="text" placeholder="长度不超过25个字符" value="${(obj.consignee)?if_exists}">
		</div>
		
		<div class="msg hide" id="J_MsgName">
			<i></i>

			<div class="msg-cnt"></div>
		</div>
	</div>

	<div class="item item-name" id="J_ItemName">
		<span class="item-label tsl" data-phase-id="d_p_receiverName">手机号码 <i>*</i></span>
		<div class="item-warp">
		<input name="phonenumber" class="i-text tsl" type="text" placeholder="填写手机号码" value="${(obj.phonenumber)?if_exists}">
		</div>
		
		<div class="msg hide" id="J_MsgName">
			<i></i>

			<div class="msg-cnt"></div>
		</div>
	</div>
	
	<div class="item">
		<button type="button" id="saveAddress" onclick="saveAddress()" class="btn  tsl">保存</button>
		<div class="msg hide" id="J_MsgSubmit">
			<i></i>
			<div class="msg-cnt"></div>
		</div>
	</div>
	</form>

  </div>

  <script type="text/javascript">
        
        $("#saveAddress").click(function(){
        	$.post("/user/editAddress",$(".saveForm").serialize(),function(data){
	  			if(data.result){
	  				layer.alert("保存成功",{icon:1},function(index){
	  					layer.close(index);
	  					parent.location.reload();
	  					parent.layer.closeAll();
	  				});
	  			}else{
	  				layer.msg(data.message);
	  			}
  			});
  			
        });
        
        function doChangep(obj){
        	$("#city").empty();
        	$("#country").empty();
        	$.post("/area/getList",{fatherid:$(obj).val()},function(data){
        		if(data.result){
        			$.each(data.list,function(i,o){
        				if(i==0){
        					country = o.code;
        				}
        				$("#city").append("<option value='"+o.code+"'>"+o.area+"</option>");
        			});
        			doChangec($("#city").get(0));
        		}
        	});
        }
        
        function doChangec(obj){
        	$("#country").empty();
        	
        	$.post("/area/getList",{fatherid:$(obj).val()},function(data){
        		if(data.result){
        			$.each(data.list,function(i,o){
        				$("#country").append("<option value='"+o.code+"'>"+o.area+"</option>");
        			});
        		}
        	});
        }
</script>
 </body>
</html>
