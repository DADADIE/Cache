<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <style type="text/css">
        html {
            height: 100%;
        }

        body {
            display: -webkit-box;  /* 老版本语法: Safari,  iOS, Android browser, older WebKit browsers.  */
            display: -moz-box;    /* 老版本语法: Firefox (buggy) */
            display: -ms-flexbox;  /* 混合版本语法: IE 10 */
            display: -webkit-flex;  /* 新版本语法： Chrome 21+ */
            display: flex;       /* 新版本语法： Opera 12.1, Firefox 22+ */

            /*垂直居中*/
            /*老版本语法*/
            -webkit-box-align: center;
            -moz-box-align: center;
            /*混合版本语法*/
            -ms-flex-align: center;
            /*新版本语法*/
            -webkit-align-items: center;
            align-items: center;

            /*水平居中*/
            /*老版本语法*/
            -webkit-box-pack: center;
            -moz-box-pack: center;
            /*混合版本语法*/
            -ms-flex-pack: center;
            /*新版本语法*/
            -webkit-justify-content: center;
            justify-content: center;

            margin: 0;
            height: 100%;
            width: 100% /* needed for Firefox */
        }

    </style>
</head>
<body>
<h2>This is Phone Client!</h2>
<input type="text" id="balance" value="0"/>
<input type="text" id="memberId" value="${memberId}"/>
</body>
<script type="text/javascript" src="${static_abs}js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${static_abs}js/socket.io.js"></script>
<script type="text/javascript" src="${static_abs}js/socketUtil.js"></script>
<script type="text/javascript">
    window.wsUrl = '${static_js_ws}';
    $(function () {
        //链接消息服务器成功接口
        var initCallback = function (sessionId) {
            //将memberId和sessionId对应关系缓存起来
            var memberId = $("#memberId").val().trim();
            cacheMemberSessionId(memberId,sessionId);
        };
        //接收消息服务器消息接口
        var notifyCallback = function (data) {
            $("#balance").val(data.data.balance);
        };
        //链接消息推送服务器
        connSocket(initCallback, notifyCallback);
    });

    function cacheMemberSessionId(memberId,sessionId){
        var data = {
            "memberId" : memberId,
            "sessionId" : sessionId
        }

        $.ajax({
            url :"${static_abs}redis/setMemberSessionId",
            type:'post',
            data : data,
            dataType : "json",
            success : function(res) {
                alert("success."+res);
            },
            error : function(){
                alert("error.");
            }
        });
    }
</script>
</html>
