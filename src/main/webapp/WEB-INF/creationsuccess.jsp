<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create user</title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp">
        </s:include>

        <div class="body_main">
            <div class="body_box body_widget link_buttons transparent">
                <s:url action="usercreate" var="create_link"/>
                <div class="input_container_inner flex_reverse clickable" onclick="window.location='<s:property value="create_link" />'">
                    <button type="button">Create another user</button>
                    <div class="input_container icon unselectable left_side green">&#10133;</div>
                </div>
            </div>

            <div class="body_box body_widget">
                <div class="form_header">
                    <h2>User Created</h2>
                </div>
                <div class="article bar contents">
                    <span class="boldtext">New user details</span>
                    <div>
                        TODO: new user name goes here!
                    </div>
                    <div>
                        TODO: new user pwd goes here!
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
