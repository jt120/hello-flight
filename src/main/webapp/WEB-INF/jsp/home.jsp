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
<link href="/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<link href="/resources/css/main.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/inc/header.jsp"/>

<div class="container">
    <div>
        <h3>搜索条件</h3>
        <form id="search_form" action="#" class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-2">
                    <label class="radio-inline">
                        <input type="radio" name="flyType" value="1" checked="checked"/>单程
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="flyType" value="2" id="retInput"/>往返
                    </label>
                </div>
                <label for="depInput" class="control-label col-sm-1">起飞</label>
                <div class="col-sm-2">
                    <input type="text" name="dep" class="form-control" id="depInput" value="PEK"/>
                </div>
                <label for="depTimeInput" class="control-label col-sm-1">起飞日期</label>

                <div class="col-sm-2">
                    <input type="text" name="depTime" class="form-control" id="depTimeInput"/>
                </div>

            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label for="arrInput" class="control-label col-sm-1">到达</label>

                <div class="col-sm-2">
                    <input type="text" name="arr" class="form-control" id="arrInput" value="HKG"/>
                </div>

                <label for="arrTimeInput" class="control-label col-sm-1">返程日期</label>

                <div class="col-sm-2">
                    <input type="text" name="arrTime" class="form-control" id="arrTimeInput"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-2">
                    <button id="search_btn" class="btn btn-primary">搜索</button>
                </div>
            </div>
        </form>
    </div>
    <hr/>
    <h3>搜索结果</h3>
    <div class="row">
        <table class="table table-bordered" id="search_result">
            <tr>
                <td>航司</td>
                <td>航班</td>
                <td>起飞时间</td>
                <td>到达时间</td>
                <td>成人票价</td>
                <td>成人税费</td>
                <td></td>
            </tr>

            <tr data-template>
                <td>{{carrier}}</td>
                <td>{{flightNumber}}</td>
                <td>{{depTime}}</td>
                <td>{{arrTime}}</td>
                <td>{{adultPrice}}</td>
                <td>{{adultTax}}</td>
                <td><a href="/booking/{{policyId}}" class="btn btn-default">预定</a></td>
            </tr>

        </table>
    </div>

</div>

<jsp:include page="/inc/footer.jsp"/>

<script type="text/javascript" src="/resources/js/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/tempo.js" charset="UTF-8"></script>
<script type="text/javascript" src="/resources/js/tempo.js" charset="UTF-8"></script>

<script>
    var base = '<%=request.getAttribute("base")%>'

    $(function () {
        //用户输入转换为大写
        $('#depInput,#arrInput').blur(function(){
            $(this).val($(this).val().toUpperCase());
        });
        //日期选择
        $('#depTimeInput,#arrTimeInput').datetimepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true,
            language: "zh-CN",
            minView:2
        });
        $('#arrTimeInput').attr("disabled", true);
        $('input[name=flyType]').click(function(){
            if($(this).attr("value") == '1') {
                $('#arrTimeInput').attr("disabled", true);
            } else{
                $('#arrTimeInput').attr("disabled", false);
            }
        });
        //添加
        $('#search_btn').click(function () {
            $.ajax({
                url: base + "/search/query",
                type:'POST',
                data: $('#search_form').serialize(),
                dataType: "json",
                success: function (data) {
                    Tempo.prepare('search_result').render(data.list);
                }

            });
        })
    });
</script>
</body>
</html>
