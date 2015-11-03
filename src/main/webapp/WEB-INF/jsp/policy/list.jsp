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
    <h3>添加政策</h3>
    <div>
        <form id="policy_form" action="#" class="form-horizontal">
            <div class="form-group">
                <label for="carrierInput" class="control-label col-sm-1">航司</label>
                <div class="col-sm-2">
                    <input type="text" name="carrier" class="form-control" id="carrierInput"/>
                </div>

                <label for="flightNumberInput" class="control-label col-sm-1">航班号</label>
                <div class="col-sm-2">
                    <input type="text" name="flightNumber" class="form-control" id="flightNumberInput"/>
                </div>

                <label for="depAirportInput" class="control-label col-sm-1">出发</label>
                <div class="col-sm-2">
                    <input type="text" name="depAirport" class="form-control" id="depAirportInput"/>
                </div>

                <label for="arrAirportInput" class="control-label col-sm-1">到达</label>
                <div class="col-sm-2">
                    <input type="text" name="arrAirport" class="form-control" id="arrAirportInput"/>
                </div>
            </div>

            <div class="form-group">

                <label class="control-label col-sm-1">有效期</label>

                <div class="col-sm-2">
                    <input type="text" name="startDate" class="form-control" id="startDateInput"/>
                </div>
                <lable class="control-label col-sm-1">至</lable>
                <div class="col-sm-2">
                    <input type="text" name="endDate" class="form-control" id="endDateInput"/>
                </div>
                <div class="col-sm-1"></div>

                <div class="col-sm-2">
                    <label class="radio-inline">
                        <input type="radio" name="flyType" value="0"/>单程
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="flyType" value="1"/>往返
                    </label>
                </div>


            <%--出票时限--%>
                <label for="ticketTimeLimitInput" class="control-label col-sm-1">出票时限</label>
                <div class="col-sm-2">
                    <input type="text" name="ticketTimeLimit" class="form-control" id="ticketTimeLimitInput"/>
                </div>

            </div>

            <div class="form-group">
                <label for="adultPriceInput" class="control-label col-sm-1">成人价</label>

                <div class="col-sm-2">
                    <input type="text" name="adultPrice" class="form-control" id="adultPriceInput"/>
                </div>
                <label for="childPriceInput" class="control-label col-sm-1">儿童价</label>

                <div class="col-sm-2">
                    <input type="text" name="childPrice" class="form-control" id="childPriceInput"/>
                </div>
                <label for="adultTaxInput" class="control-label col-sm-1">成人税</label>

                <div class="col-sm-2">
                    <input type="text" name="adultTax" class="form-control" id="adultTaxInput"/>
                </div>
                <label for="childTaxInput" class="control-label col-sm-1">儿童税</label>

                <div class="col-sm-2">
                    <input type="text" name="childTax" class="form-control" id="childTaxInput"/>
                </div>
            </div>
            <div class="form-group">

                <label for="currencyInput" class="control-label col-sm-1">货币类型</label>
                <div class="col-sm-2">
                    <input type="text" name="currency" class="form-control" id="currencyInput" value="RMB"/>
                </div>


                <label for="nationalityInput" class="control-label col-sm-1">国籍</label>
                <div class="col-sm-2">
                    <input type="text" name="nationality" class="form-control" id="nationalityInput"/>
                </div>
                <div class="col-sm-2">
                    <select name="nationalityType" class="form-control">
                        <option value="0">不考虑</option>
                        <option value="1">适用</option>
                        <option value="2">不适用</option>
                    </select>
                </div>
                <label for="suitAgeInput" class="control-label col-sm-1">适用年龄</label>
                <div class="col-sm-2">
                    <input type="text" name="suitAge" class="form-control" id="suitAgeInput"/>
                </div>
            </div>
            <div class="form-group">

                <div class="col-sm-2">
                        <label class="radio-inline">
                            <input type="radio" name="priceType" value="0"/>成人
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="priceType" value="1"/>留学生
                        </label>
                </div>

                <%--价格类型--%>
                <div class="col-sm-2">
                        <label class="radio-inline">
                            <input type="radio" name="applyType" value="0"/>申请价
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="applyType" value="1"/>预定价
                        </label>
                </div>

                <%--税费--%>
                <div class="col-sm-2">
                        <label class="radio-inline">
                            <input type="radio" name="adultTaxType" value="0"/>未含税
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="adultTaxType" value="1"/>已含税
                        </label>
                </div>

                <%--行程单--%>
                <div class="col-sm-2">
                    <label class="radio-inline">
                        <input type="radio" name="ticketInvoiceType" value="0"/>行程单
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="ticketInvoiceType" value="1"/>旅行发票
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                    <button id="add_policy" class="btn btn-primary">添加</button>
                </div>
            </div>
        </form>
    </div>
    <hr/>
    <h3>查询政策</h3>
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
                <button id="query_policy" class="btn btn-primary">查询</button>
            </div>
        </form>
    </div>
    <hr/>
    <h3>查询结果</h3>
    <div class="row">
        <table class="table table-bordered" id="policy_list">
            <tr>
                <td>航司</td>
                <td>航班</td>
                <td>有效期开始</td>
                <td>有效期结束</td>
                <td>成人价</td>
            </tr>

            <tr data-template>
                <td>{{carrier}}</td>
                <td>{{flightNumber}}</td>
                <td>{{startDate|date 'YYYY-MM-DD'}}</td>
                <td>{{endDate|date 'YYYY-MM-DD'}}</td>
                <td>{{adultPrice}}</td>
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
    function reloadPolicy() {
        $.ajax({
            url: base + "/policy/query",
            type:"POST",
            timeout:5000,
            data: $('query_form').serialize(),
            dataType: "json",
            success: function (data) {
                Tempo.prepare('policy_list').render(data.list);

            }
        });
    }
    $(function () {
        reloadPolicy();
        //用户输入转换为大写
        $('#carrierInput,#flightNumberInput,#depAirportInput,#arrAirportInput').blur(function(){
            $(this).val($(this).val().toUpperCase());
        });

        $('#query_policy').click(function () {
            reloadPolicy();
        });
        //日期选择
        $('#startDateInput,#endDateInput').datetimepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            todayHighlight: true,
            language: "zh-CN",
            minView: 2
        });
        //添加
        $('#add_policy').click(function () {
            $.ajax({
                url: base + "/policy/add",
                data: $('#policy_form').serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.ok) {
                        reloadPolicy();
                    }
                }

            });
        })
    });
</script>
</body>
</html>
