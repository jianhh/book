<@operating.top />
	<script type="text/javascript" src="<@operating.js />/framework.js"></script>
	<link href="<@operating.css/>/tab/basic.css" rel="stylesheet" type="text/css"/>
	<link href="<@operating.css/>/tab/code.css" rel="stylesheet" type="text/css"/>
	<link href="<@operating.css/>/tab/form.css" rel="stylesheet" type="text/css"/>
	<link href="<@operating.css/>/tab/position.css" rel="stylesheet" type="text/css"/>
	<link href="<@operating.css/>/tab/icon.css" rel="stylesheet" type="text/css"/>
	<link href="<@operating.css/>/tab/reset.css" rel="stylesheet" type="text/css"/>
	<link href="<@operating.css/>/tab/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" charset="utf-8" src="<@operating.js />/tab.js"></script>

<div id="tab_menu" style="height:30px;"></div>
<div style="width:100%;">
	<div id="page" style="width:100%;height:100%;">
	       
	</div>
</div>		
<script type="text/javascript">

function tabAddHandler(mid,mtitle,murl){
	tab.update({
		id :mid,
		title :mtitle,
		url :murl,
		isClosed :true
	});
	tab.add({
		id :mid,
		title :mtitle,
		url :murl,
		isClosed :true
	});

	tab.activate(mid);
}
 var tab;	
$( function() {
	 tab = new TabView( {
		containerId :'tab_menu',
		pageid :'page',
		cid :'tab1',
		position :"top"
	});
	tab.add( {
		id :'tab1_index1',
		title :"主页",	
		url :"<@operating.basePath />/operatingBack/default",
		isClosed :false
	});
	/**tab.add( {
		id :'tab1_index1',
		title :"主页",
		url :"/per/undoTask!gettwo",
		isClosed :false
	});
	**/
});

	function cmainFrameT(){
		var hmainT = document.getElementById("page");
		var bheightT = document.documentElement.clientHeight;
		hmainT .style.width = '100%';
		hmainT .style.height = (bheightT  - 51) + 'px';
	}
	cmainFrameT();
	window.onresize=function(){  
		cmainFrameT();
	};

</script>
<@operating.bottom />


