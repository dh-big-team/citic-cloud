<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here...</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
var basePath = "/cloud/";
describeDisks();

/**
 * 查询云硬盘实例列表 
 */
function describeDisks(){
	var jsonParam = {
		citicInfo:{
			orgId:"11122",
			userId:"11222",
			appBaseId:"dgs333",
			appBaseName:"dsagsd2",
			costCenterId:"33432d",
			costCenterName:"erwer",
		},
		serviceId:"cbs",
		params:{
			Region:"ap-guangzhou"
		},
		instanceId:"dsfsdew",
		otherInfo:{},
		requestId:"3432rwerf"
	};
	$.ajax({
	    url:basePath+'api/cbs/DescribeDisks',
	    type:'POST',
	    timeout:5000,   
	    async:false,  
	    dataType:'json',  
	    contentType : 'application/json',
	    data:JSON.stringify(jsonParam),
	    success:function(data,textStatus,jqXHR){
	    	console.info("data:"+data);
	    },
	    error:function(xhr,textStatus){
	    	console.info("error");
	    }
	});
}



</script>
</body>
</html>