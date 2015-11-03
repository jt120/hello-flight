<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="description" content="easy flight">
<meta name="author" content="jt">
<link rel="icon" href="/resources/favicon.ico">
    <title>easy flight</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen"></head>
    <link href="/resources/css/main.css" rel="stylesheet"></head>
<body>
<jsp:include page="/inc/header.jsp"/>

<div class="container">
    <div>
    <form action="#" id="user_form">
        <div class="form-group">
            <label for="nameInput">用户名</label>
            <input type="text" name="name" class="form-control" id="nameInput"/>
        </div>
        <div class="form-group">
            <label for="birthInput">生日</label>
            <input type="text" name="birth" class="form-control" id="birthInput"/>
        </div>
    </form>
    </div>
    <button id="add_user" class="btn btn-primary">添加</button>
</div>

<jsp:include page="/inc/footer.jsp"/>

<script type="text/javascript" src="/resources/js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>
    var base = '<%=request.getAttribute("base")%>'
    $(function() {
        $("#add_user").click(function () {
            $.ajax({
                url: base + "/user/add",
                data: $("#user_form").serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.ok) {
                        alert(data.msg);
                    }

                }

            });
        });

        $('#birthInput').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            autoclose: true,
            todayBtn:true,
            todayHighlight:true,
            language:"zh-CN"
        });
    })
</script>
</body>
</html>
