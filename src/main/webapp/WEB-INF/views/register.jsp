<%--
  Created by IntelliJ IDEA.
  User: HSK
  Date: 2018-06-27
  Time: 오후 5:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="css/css-mint.min.css">
    <link rel="stylesheet" href="css/basic.css">
    <script type="text/css">
    </script>
</head>
<body>
<div id="div_login" align="center">
    <main>
        <section>
            <h2>회원 가입</h2>
            <form id="frm_register" name="frm_register" action="registerProcess" method="post">
                <!-- input fields -->
                <input id="txt_id" name="id" type="text" class="cm-input" placeholder="ID"/><br/>
                <input id="txt_pw" name="pw" type="password" class="cm-input" placeholder="Password"/><br/>
                <input id="txt_name" name="name" type="text" class="cm-input" placeholder="Name"/><br/>
                <input id="txt_email" name="email" type="text" class="cm-input" placeholder="E-mail"/><br/>
                <!-- buttons -->
                <button id="btn_submit" name="submit" type="submit" class="cm-btn success" disabled="true" value="btn_submit">Submit</button>
                <button id="btn_cancel" name="cancel" class="cm-btn error" value="btn_cancel">Cancel</button><br/>
                <!-- error messages -->
                <span id="error_id" name="error_id" class="cm-alert error" style="margin-top: 50px;"></span>
                <span id="error_pw" name="error_pw" class="cm-alert error" style="margin-top: 50px;"></span>
                <span id="error_name" name="error_name" class="cm-alert error" style="margin-top: 50px;"></span>
                <span id="error_email" name="error_email" class="cm-alert error" style="margin-top: 50px;"></span>
                <span id="error_msg" name="error_msg" class="cm-alert error" style="margin-top: 50px;"></span>
            </form>
        </section>
    </main>
</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
    // disable button when input field is empty.
    toggleDisableSubmit(!checkFieldEmpty());

    // disable submit after button clicked.
    $("#btn_submit").submit(function () {
        $(this).attr("disabled", true);
        return true;
    });

    // show span
    showSpan(undefined);

    // check input datas after input focused out and keyup
    // then button toggle
    $("input").each(function() {
        $(this).focusout(function() {
            toggleDisableSubmit(checkValidUser());
        });

        $(this).keyup(function() {
            toggleDisableSubmit(checkValidUser());
        });
    });

    // show error message if message exists.
    showErrorSpan("${msg}");
});

function showSpan(objSpanMsg) {
    $("span").each(function () {
        var text = objSpanMsg === undefined ? undefined : objSpanMsg[this.id];

        if (text === undefined) {
            $(this).css("display", "none");
            this.innerText = "";
        } else {
            $(this).css("display", "inline-block");
            this.innerText = text;
        }
    });
}

function showErrorSpan(msg) {
    if (msg.length == 0) {
        $("#error_msg").css("display", "none");
        $("#error_msg").text("");
    } else {
        $("#error_msg").css("display", "inline-block");
        $("#error_msg").text(msg);
    }
}

function checkFieldEmpty() {
    var frm = document.frm_register;
    return frm.id.value.length == 0 || frm.pw.value.length == 0 ||
            frm.name.value.length == 0 || frm.email.value.length == 0
            ? true : false;
}

function checkValidUser() {
    var validUser = true;
    var errorMessage = "";
    var objSpanMsg = {};

    // check if input type is valid
    if (!checkValidId()) {
        errorMessage = "ID는 소문자 알파벳과 숫자의 조합이며, 첫 글자는 알파벳으로 시작해야 합니다\n"
                        + "5~20글자만 가능합니다.";
        objSpanMsg["error_id"] = errorMessage;
        showSpan(objSpanMsg);

        return validUser = false;
    }
    if (!checkValidPw()) {
        errorMessage = "비밀번호는 8~20글자로 가능하고, 숫자, 알파벳(대문자, 소문자 모두 가능),\n"
                        + "허용된 특수문자(!@#$%^&*)의 조합입니다.";
        objSpanMsg["error_pw"] = errorMessage;
        showSpan(objSpanMsg);

        return validUser = false;
    }
    if (!checkValidName()) {
        errorMessage = "이름은 한글 이름 또는 영문 이름으로 등록이 가능합니다.\n"
                        + "한글은 30글자까지, 영문은 공백을 두고 first name은 1~15, last name은 1~15글자 가능합니다. (예: \"Donald Trump\")";
        objSpanMsg["error_name"] = errorMessage;
        showSpan(objSpanMsg);

        return validUser = false;
    }
    if (!checkValidEmail()) {
        errorMessage = "이메일은 account@domain.com처럼 이메일의 형식만 가능합니다.";
        objSpanMsg["error_email"] = errorMessage;
        showSpan(objSpanMsg);

        return validUser = false;
    }

    showSpan(objSpanMsg);
    return validUser;
}

function toggleDisableSubmit(validUser) {   // validUser : boolean
    var frm = document.frm_register;
    frm.submit.disabled = !validUser;
    // $("#btn_submit").attr("disabled", validUser);
}

/**
 * 1. id의 길이는 5 이상 20 이하.
 * 2. id는 소문자 알파벳과 숫자의 조합.
 * 3. 첫 글자는 알파벳.
 * @returns {boolean}
 */
function checkValidId() {
    var validId = true;
    var frm = document.frm_register;

    /** @see <a href="https://stackoverflow.com/a/24304748/4284814">https://stackoverflow.com/a/24304748/4284814</a> */
    if (!frm.id.value.match(/^([a-z])[a-z0-9]{4,20}$/g)) {
        validId = false;
    }

    return validId;
}

/**
 * 1. pw의 길이는 8 이상 20 이하.
 * 2. pw는 숫자, 알파벳(대문자, 소문자 모두 가능), 허용된 특수문자(!@#$%^&*)의 조합.
 * @returns {boolean}
 */
function checkValidPw() {
    var validPw = true;
    var frm = document.frm_register;

    if (!frm.pw.value.match(/^(?=.*[0-9A-Za-z])[\w!@#$%^&*]{7,20}$/g)) {
        validPw = false;
    }

    return validPw;
}

/**
 * 1. 이름은 한글 또는 영문 가능
 * 2. 한글은 1~30글자 가능
 * 3. 영문은 공백을 두고 first name은 1~15, last name은 1~15글자 가능. (예: "Donald Trump")
 * @returns {boolean}
 */
function checkValidName() {
    var validName = true;
    var frm = document.frm_register;

    if (!frm.name.value.match(/^([가-힣]{1,30}|[a-zA-Z]{1,15}\s[a-zA-Z]{1,15})$/g)) {
        validName = false;
    }

    return validName;
}

/**
 * 이메일은 "account@domain.com" 형식만 허용
 * @returns {boolean}
 */
function checkValidEmail() {
    var validEmail = true;
    var frm = document.frm_register;

    if (!frm.email.value.match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/g)) {
        validEmail = false;
    }

    return validEmail;
}
</script>
</html>