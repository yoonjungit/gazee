<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 메인 페이지</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" type="text/css" href="../../resources/css/adminMain.css"/>
</head>
<body>
<script>
    $(function () {
        $("#contents_container").load("dashboard.do");
    });

    function loadDashboard() {
        $("#contents_container").load("dashboard.do");
    }

    function loadSetting() {
        $("#contents_container").load("info.do");
    }

    function loadMember() {
        $("#contents_container").load("member.do");
    }

    function loadProduct() {
        $("#contents_container").load("product.do");
    }

    function loadOrder() {
        $("#contents_container").load("order.do");
    }

    function loadPay() {
        $("#contents_container").load("pay.do");
    }

    function loadCs() {
        $("#contents_container").load("cs.do");
    }

    function loadReport() {
        $("#contents_container").load("report.do");
    }

    function orderList() {
        $("#order_container").load("orderList.do");
    }

    function returnToSetList() {
        $("#contents_container").load("order.do");
    }

</script>
<div class="container">
    <div class="navigation">
        <ul>
            <li>
                <a href="main.do">
                    <span class="title">
                        <img src="/resources/img/gazee_admin_logo_white.png" alt="logo"
                             style="margin-top: 10px; width: 100%;">
                        </span>
                </a>
            </li>
            <li>
                <a href="gazee.do">
                    <span class="icon"><i class="fa fa-home" aria-hidden="true"></i></span>
                    <span class="title">사이트로 바로가기</span>
                </a>
            </li>
            <li>
                <hr>
            </li>
            <li>
                <a href=# id="nav_dashboard" onclick="loadDashboard()">
                    <span class="icon"><i class="fa fa-dashboard" aria-hidden="true"></i></span>
                    <span class="title">대시보드</span>
                </a>
            </li>
            <li>
                <a href=# id="nav_member" onclick="loadMember()">
                    <span class="icon"><i class="fa fa-users" aria-hidden="true"></i></span>
                    <span class="title">회원 관리</span>
                </a>
            </li>
            <li>
                <a href=# id="nav_product" onclick="loadProduct()">
                    <span class="icon"><i class="fa fa-shopping-cart" aria-hidden="true"></i></span>
                    <span class="title">상품 관리</span>
                </a>
            </li>
            <li>
                <a href=# id="nav_order" onclick="loadOrder()">
                    <span class="icon"><i class="fa fa-handshake-o" aria-hidden="true"></i></span>
                    <span class="title">거래 관리</span>
                </a>
            </li>
            <li>
                <a href=# id="nav_pay" onclick="loadPay()">
                    <span class="icon"><i class="fa fa-credit-card" aria-hidden="true"></i></span>
                    <span class="title">가지페이 관리</span>
                </a>
            </li>
            <li>
                <a href=# id="nav_cs" onclick="loadCs()">
                    <span class="icon"><i class="fa fa-phone" aria-hidden="true"></i></span>
                    <span class="title">문의 관리</span>
                </a>
            </li>
            <li>
                <a href=# id="nav_report" onclick="loadReport()">
                    <span class="icon"><i class="fa fa-user-secret" aria-hidden="true"></i></span>
                    <span class="title">신고 관리</span>
                </a>
            </li>
            <li>
                <hr>
            </li>
            <li>
                <a href=# id="nav_setting" onclick="loadSetting()">
                    <span class="icon"><i class="fa fa-user-circle" aria-hidden="true"></i></span>
                    <span class="title">관리자 설정</span>
                </a>
            </li>
            <li>
                <a href="logout.do">
                    <span class="icon"><i class="fa fa-sign-out" aria-hidden="true"></i></span>
                    <span class="title">로그아웃</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="top-bar">
            <div class="toggle" onclick="toggleMenu()"></div>
            <%--  <div class="search">
                  <label>
                      <input type="text" placeholder="검색어를 입력하세요">
                      <i class="fa fa-search" aria-hidden="true"></i>
                  </label>
              </div>--%>
        </div>
        <div class="contents" id="contents_container">
        </div>
    </div>
</div>
</body>
<script>
    function toggleMenu() {
        let toggle = document.querySelector(".toggle");
        let navigation = document.querySelector(".navigation");
        let main = document.querySelector(".main");
        toggle.classList.toggle("active");
        navigation.classList.toggle("active");
        main.classList.toggle("active");
    }
</script>
</html>