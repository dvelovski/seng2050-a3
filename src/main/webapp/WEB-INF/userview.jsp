<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User details</title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
    <body>
        <s:include value="common/header.jsp">
        </s:include>
        <div class="body_main">
            <div class="body_box body_widget">
                <div class="form_header flex_row no_margin">
                    <h2>User details</h2>
                </div>
                <div class="user_menu_outer">
                    <div class="user_menu_column left">
                        <div class="user_icon_large_outer">
                            <div class="user_icon_large_inner" id="userIcon">AD</div>
                        </div>
                    </div>
                    <div class="user_menu_column right">
                        <s:if test="action == 'editor'">
                            <form class="column article bar">
                                <div class="input_container_outer">
                                    <div>
                                        First name
                                    </div>
                                    <div class="input_container_inner shorter_elements">
                                        <input type="text" class="tb_general" placeholder="First name">
                                    </div>
                                    <div class="input_container_error">Error</div>
                                </div>
                                <div class="input_container_outer">
                                    <div>
                                        Last name
                                    </div>
                                    <div class="input_container_inner shorter_elements">
                                        <input type="text" class="tb_general" placeholder="Last name">
                                    </div>
                                    <div class="input_container_error">Error</div>
                                </div>
                                <div class="input_container_outer">
                                    <div>
                                        E-mail address
                                    </div>
                                    <div class="input_container_inner shorter_elements">
                                        <input type="text" class="tb_general" placeholder="Email address">
                                    </div>
                                    <div class="input_container_error">Error</div>
                                </div>
                                <div class="input_container_outer">
                                    <div>
                                        Phone number
                                    </div>
                                    <div class="input_container_inner shorter_elements">
                                        <input type="text" class="tb_general" placeholder="Phone number">
                                    </div>
                                    <div class="input_container_error">Error</div>
                                </div>
                                <div class="input_container_outer">
                                    <div class="input_container_inner">
                                        <input type="submit" value="Save changes" class="no_right_border">
                                        <div class="input_container icon unselectable right_side">&#10003;</div>
                                    </div>
                                    <div class="inner_spacer"></div>

                                    <s:url action="userview" var="cancel_link">
                                        <s:param name="userID"><s:property value="userID" /></s:param>
                                    </s:url>
                                    <s:a href="%{#cancel_link}" class="input_container_inner">
                                        <input type="button" value="Cancel" class="no_right_border">
    <%--                                    <s:a href="%{#cancel_link}">--%>

    <%--                                    </s:a>--%>
                                        <%--<div class="input_container icon unselectable right_side">&#10006;</div>--%>
                                    </s:a>
                                </div>
                                <input type="hidden" name="action" value="update">
                            </form>
                        </s:if>
                        <s:else>
                            <div class="column article bar">
                                <div class="input_container_outer">
                                    <div class="boldtext">First name</div>
                                    <div id="userFirstName"><s:property value="userBean.userFirstName"/></div>
                                </div>
                                <div class="input_container_outer">
                                    <div class="boldtext">Last name</div>
                                    <div id="userLastName"><s:property value="userBean.userLastName"/></div>
                                </div>
                                <div class="input_container_outer">
                                    <div class="boldtext">Email address</div>
                                    <div><s:property value="userBean.userEmail"/></div>
                                </div>
                                <div class="input_container_outer">
                                    <div class="boldtext">Phone number</div>
                                    <div><s:property value="userBean.userPhoneNumber"/></div>
                                </div>
                                <div class="input_container_outer">
                                    <s:url action="userview" var="edit_link">
                                        <s:param name="userID"><s:property value="userID" /></s:param>
                                        <s:param name="action">editor</s:param>
                                    </s:url>
                                    <s:a href="%{#edit_link}" class="input_container_inner">
                                        <input type="button" value="Edit details" class="no_right_border">
                                    </s:a>
                                </div>
                            </div>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        let uFirstName = document.getElementById("userFirstName").innerHTML;
        let uLastName = document.getElementById("userLastName").innerHTML;

        let uIcon = document.getElementById("userIcon");

        uIcon.innerHTML = uFirstName.charAt(0) + uLastName.charAt(0);
    </script>
</html>
