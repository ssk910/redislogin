<%--
  Created by IntelliJ IDEA.
  User: HSK
  Date: 2018-06-26
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Index</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="css/css-mint.min.css">
    <link rel="stylesheet" href="css/basic.css">
</head>
<body>
<div id="div_login" align="center">
    <main>
        <section>
            <h2>로그인</h2>
            <form id="frm_login" name="frm_login" action="loginProcess" method="post">
                <input id="txt_id" name="id" type="text" class="cm-input" placeholder="ID"/><br/>
                <input id="txt_pw" name="pw" type="password" class="cm-input" placeholder="Password"/><br/>
                <button id="btn_login" name="login" type="submit" class="cm-btn primary" value="btn_login" disabled="true">Log in</button>
                <button id="btn_join" name="join" class="cm-btn primary line" value="btn_join">Join</button><br/>
            </form>
            <span id="error_msg" name="error_msg" class="cm-alert error" style="margin-top:50px;"></span>
        </section>
    </main>
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
    // disable button when input field is empty.
    $("input").each(function() {
        $(this).keyup(function() {
            toggleDisableSubmit(checkFieldEmpty());
        });
    });

    // disable submit after button clicked.
    $("#btn_login").submit(function () {
        $(this).attr("disabled", true);
        return true;
    });

    showSpan("${msg}");
});

function showSpan(msg) {
    if (msg.length == 0) {
        $("#error_msg").css("display", "none");
        $("#error_msg").text("");
    } else {
        $("#error_msg").css("display", "inline-block");
        $("#error_msg").text(msg);
    }
}

function checkFieldEmpty() {
    var frm = document.frm_login;
    return frm.id.value.length == 0 || frm.pw.value.length == 0 ? true : false;
}

function toggleDisableSubmit(fieldEmpty) {   // fieldEmpty : boolean
    var frm = document.frm_login;
    frm.login.disabled = fieldEmpty;
}
</script>
</html>
