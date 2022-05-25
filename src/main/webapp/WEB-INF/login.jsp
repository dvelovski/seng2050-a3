<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp" />
        <div class="body_main">
            <div class="body_box login_box">
                <div class="form_header">
                    <h2>Log in</h2>
                </div>
                <s:form action="login" class="column" onsubmit="return validateLoginForm(this)">
                    <div class="input_container_outer">
                        <div class="input_container_inner flex_reverse">
                            <input type="text" id="loginUsername" name="loginUsername" class="tb_general hide_left_border" placeholder="Username"/>
                            <div class="input_container icon unselectable left_side"><label for="loginUsername">&#128100;</label></div>

                        </div>
                        <div class="input_container_error">
                            <s:property value="fieldErrors.get('loginUsername').get(0)" />
                        </div>
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_inner flex_reverse">
                            <input type="password" id="loginPassword" name="loginPassword" class="tb_general hide_left_border" placeholder="Password" />
                            <div class="input_container icon unselectable left_side">&#128274;</div>
                            <label for="loginPassword"></label>
                        </div>
                        <div class="input_container_error">
                            <s:property value="fieldErrors.get('loginPassword').get(0)" />
                        </div>
                    </div>
                    <div class="input_container_error">
                        <s:property value="fieldErrors.get('loginGeneral').get(0)" />
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_inner">
                            <input type="submit" class="login_submit_button" value="Log in">
                            <div class="input_container icon unselectable right_side">&#10095;</div>
                        </div>
                    </div>
                </s:form>
                <h4>
                    New to our system?
                </h4>
                <p>Please contact a member of IT staff to create a profile for you.</p>
            </div>
        </div>
    </body>
</html>