<%--
  Created by IntelliJ IDEA.
  User: HSK
  Date: 2018-06-26
  Time: 오후 4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="css/css-mint.min.css">
    <link rel="stylesheet" href="css/basic.css">
</head>
<body>
<div id="div_main" align="center">
    <main>
        <section>
            <h2>유저 정보</h2>
            <div id="div_tbl" class="cm-grid">
                <div class="cm-row">
                    <div class="cm-col-sm-4" style="float:none;">
                        <table class="primary">
                            <tbody>
                            <tr>
                                <th>ID</th>
                                <td>${user.id}</td>
                            </tr>
                            <tr>
                                <th>Password</th>
                                <td>${user.pw}</td>
                            </tr>
                            <tr>
                                <th>Name</th>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <th>E-mail</th>
                                <td>${user.email}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <br/>
            <form id="frm_main" name="frm_main" action="mainProcess" method="post">
                <button id="btn_edit" name="edit" type="submit" class="cm-btn primary line" value="btn_edit">Edit</button>
                <button id="btn_logout" name="logout" type="submit" class="cm-btn primary" value="btn_logout">Log Out</button>
                <button id="btn_delete" name="delete" type="submit" class="cm-btn error" onclick="return checkSubmit(this);" value="btn_delete">Delete My Account</button>
            </form>
        </section>
    </main>
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {

});

function checkSubmit(elemButton) {
    var confirmed = false;

    switch (elemButton.name) {
        case "edit":
            break;
        case "logout":
            break;
        case "delete":
            confirmed = confirm("계정을 삭제하시겠습니까?");
            break;
    }

    return confirmed;
}
</script>
</html>