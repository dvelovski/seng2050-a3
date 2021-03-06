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

                <s:if test="notFound == true">
                    <div class="form_header flex_row">
                        <h2><s:property value="pageTitle" /></h2>
                    </div>
                    <div class="colum article bar">The specified user was not found.</div>
                </s:if>
                <s:else>
                    <div class="form_header flex_row no_margin">
                        <h2><s:property value="pageTitle" /></h2>
                    </div>
                    <div class="user_menu_outer">
                        <div class="user_menu_column left">
                            <div class="user_icon_large_outer">
                                <div class="user_icon_large_inner" id="userIcon"></div>
                            </div>
                        </div>
                        <div class="user_menu_column right">
                            <s:if test="action == 'editor'">
                                <s:form action="userupdate" class="column article bar" method="post" name="userUpdateForm" onsubmit="return validateUserUpdate(this)">
                                    <div class="boldtext">
                                        User details
                                    </div>
                                    <div class="input_container_outer">
                                        <div>
                                            <label for="uFirstName">First name</label>
                                        </div>
                                        <div class="input_container_inner shorter_elements">
                                            <s:textfield name="uFirstName" class="tb_general" value="%{userBean.userFirstName}" />
                                        </div>
                                        <div class="input_container_error">
                                            <s:property value="fieldErrors.get('uFirstName').get(0)" />
                                        </div>
                                    </div>
                                    <div class="input_container_outer">
                                        <div>
                                            Last name
                                        </div>
                                        <div class="input_container_inner shorter_elements">
                                            <s:textfield name="uLastName" class="tb_general" value="%{userBean.userLastName}" />
                                            <label for="uLastName"></label>
                                        </div>
                                        <div class="input_container_error">
                                            <div class="input_container_error">
                                                <s:property value="fieldErrors.get('uLastName').get(0)" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input_container_outer">
                                        <div>
                                            E-mail address
                                        </div>
                                        <div class="input_container_inner shorter_elements">
                                            <s:textfield name="uEmail" class="tb_general" value="%{userBean.userEmail}" />
                                            <label for="uEmail"></label>
                                        </div>
                                        <div class="input_container_error">
                                            <div class="input_container_error">
                                                <s:property value="fieldErrors.get('uEmail').get(0)" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="input_container_outer">
                                        <div>
                                            Phone number
                                        </div>
                                        <div class="input_container_inner shorter_elements">
                                            <s:textfield name="uPhone" class="tb_general" value="%{userBean.userPhoneNumber}" type="number"/>
                                            <label for="uPhone"></label>
                                        </div>
                                        <div class="input_container_error">
                                            <div class="input_container_error">
                                                <s:property value="fieldErrors.get('uPhone').get(0)" />
                                            </div>
                                        </div>
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
                                        <div class="input_container_inner">
                                            <input type="button" value="Cancel" class="no_right_border" onclick="window.location.href='<s:property value="cancel_link" />'">
                                        </div>
                                    </div>
                                    <input type="hidden" name="userID" value="<s:property value="userID" />">
                                </s:form>
                                <script>
                                    let uIcon = document.getElementById("userIcon");
                                    let uForm = document.forms["userUpdateForm"];

                                    let tbFirstName = document.forms['userUpdateForm'].elements['uFirstName'];
                                    let tbLastName = document.forms['userUpdateForm'].elements['uLastName'];
                                    tbFirstName.addEventListener("input", function(){
                                        updateUserIcon();
                                    });
                                    tbLastName.addEventListener("input", function(){
                                        updateUserIcon();
                                    })

                                    function updateUserIcon(){
                                        uIcon.innerHTML = tbFirstName.value.charAt(0).toUpperCase() + tbLastName.value.charAt(0).toUpperCase();
                                    }
                                </script>
                            </s:if>
                            <s:else>
                                <div class="column article bar">
                                    <div class="input_container_outer">
                                        <div class="boldtext">System username</div>
                                        <div id="userUName"><s:property value="userBean.userName"/></div>
                                    </div>
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
                                <script>
                                    let uFirstName = document.getElementById("userFirstName").innerHTML;
                                    let uLastName = document.getElementById("userLastName").innerHTML;

                                    let uIcon = document.getElementById("userIcon");

                                    function updateUserIcon(){
                                        uIcon.innerHTML = uFirstName.charAt(0).toUpperCase() + uLastName.charAt(0).toUpperCase();
                                    }
                                </script>
                            </s:else>
                        </div>
                    </div>
                    <script>
                        updateUserIcon();
                    </script>
                </s:else>
            </div>
        </div>
    </body>
</html>
