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
        <form id="av_form" action="#" class="form-horizontal">
            <div class="form-group">
                <label for="carrierInput" class="control-label col-sm-2">航司</label>

                <div class="col-sm-2">
                    <input type="text" name="carrier" class="form-control" id="carrierInput"/>
                </div>

                <label for="flightNumberInput" class="control-label col-sm-2">航班号</label>

                <div class="col-sm-2">
                    <input type="text" name="flightNumber" class="form-control" id="flightNumberInput"/>
                </div>
            </div>
            <div class="form-group">
                <label for="depAirportInput" class="control-label col-sm-2">出发</label>

                <div class="col-sm-2">
                    <input type="text" name="depAirport" class="form-control" id="depAirportInput"/>
                </div>
                <label for="arrAirportInput" class="control-label col-sm-2">到达</label>

                <div class="col-sm-2">
                    <input type="text" name="arrAirport" class="form-control" id="arrAirportInput"/>
                </div>
            </div>
            <div class="form-group">
                <label for="depTimeInput" class="control-label col-sm-2">起飞日期</label>

                <div class="col-sm-2">
                    <input type="text" name="depTime" class="form-control" id="depTimeInput"/>
                </div>
                <label for="arrTimeInput" class="control-label col-sm-2">到达日期</label>

                <div class="col-sm-2">
                    <input type="text" name="arrTime" class="form-control" id="arrTimeInput"/>
                </div>
            </div>
            <div class="form-group">
                <label for="stopCitiesInput" class="control-label col-sm-2">经停</label>

                <div class="col-sm-2">
                    <input type="text" name="stopCities" class="form-control" id="stopCitiesInput"/>
                </div>
                <label for="cabinInput" class="control-label col-sm-2">仓位</label>

                <div class="col-sm-2">
                    <input type="text" name="cabin" class="form-control" id="cabinInput"/>
                </div>
                <div class="col-sm-2">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="codeShare">是否共享
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-2">
                    <button id="add_av" class="btn btn-primary">添加</button>
                </div>
            </div>
        </form>
    </div>
    <hr/>
    <div class="row">
        <form action="#" id="query_form" class="form-inline">
            <%--<div class="form-group">--%>
                <%--<label for="queryCondInput">条件</label>--%>
                <%--<input type="text" name="xxx" class="form-control" id="queryCondInput">--%>
            <%--</div>--%>
            <div class="form-group">
                <label for="pageNumberInput">页数</label>
                <input type="text" name="pageNumber" class="form-control" id="pageNumberInput" value="1">
            </div>
            <div class="form-group">
                <label for="pageSizeInput">每页</label>
                <input type="text" name="pageSize" class="form-control" id="pageSizeInput" value="10">
            </div>
            <div class="form-group">
                <button id="query_av" class="btn btn-primary">查询</button>
            </div>
        </form>
    </div>
    <hr/>
    <div class="row">
        <table class="table table-bordered" id="av_list">
            <tr>
                <td>航司</td>
                <td>航班</td>
                <td>出发</td>
                <td>到达</td>
            </tr>
            <tr data-template>
                <td>{{carrier}}</td>
                <td>{{flightNumber}}</td>
                <td>{{depAirport}}</td>
                <td>{{arrAirport}}</td>
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
<script>
    var base = '<%=request.getAttribute("base")%>'
    function reloadAv() {
        $.ajax({
            url: base + "/av/query",
            type:"POST",
            timeout:5000,
            data: $('query_form').serialize(),
            dataType: "json",
            success: function (data) {
                Tempo.prepare('av_list').render(data.list);
            }
        });
    }
    $(function () {
        reloadAv();
        //用户输入转换为大写
        $('#carrierInput,#flightNumberInput,#depAirportInput,#arrAirportInput,#cabinInput').blur(function(){
            $(this).val($(this).val().toUpperCase());
        });

        $('#query_av').click(function () {
            reloadAv();
        });
        //日期选择
        $('#depTimeInput,#arrTimeInput').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true,
            language: "zh-CN"
        });
        //添加
        $('#add_av').click(function () {
            $.ajax({
                url: base + "/av/add",
                data: $('#av_form').serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.ok) {
                        alert(data.msg);
                        reloadAv();
                    } else {
                        alert(data.msg);
                    }
                }

            });
        })
    });
</script>
</body>
</html>
