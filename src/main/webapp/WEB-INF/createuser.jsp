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
            <div class="body_box body_widget">
                <div class="form_header">
                    <h2>Create New User</h2>
                </div>
                <s:form action="usercreate" class="column article bar" method="post" name="userUpdateForm"
                        onsubmit="return validateUserUpdate(this)"> <!-- TODO validateUserCreation as we now have the user type field -->
                    <div class="input_container_outer">
                        <div class="input_container_desc">
                            <label for="uAcctType">New user type</label>
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <select class="tb_general" name="uAcctType" id="uAcctType">
                                <option value="0" selected disabled hidden>Make a selection</option>
                                    <option value="1">Standard user</option>
                                    <option value="2">Staff member</option>
                            </select>
                        </div>
                        <div class="input_container_error"><s:property value="fieldErrors.get('uAcctType').get(0)" /></div>
                    </div>
                    <div class="input_container_outer">
                        <div>
                            <label for="uFirstName">First name</label>
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <s:textfield name="uFirstName" class="tb_general"/>
                        </div>
                        <div class="input_container_error">
                            <s:property value="fieldErrors.get('uFirstName').get(0)"/>
                        </div>
                    </div>
                    <div class="input_container_outer">
                        <div>
                            Last name
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <s:textfield name="uLastName" class="tb_general"/>
                            <label for="uLastName"></label>
                        </div>
                        <div class="input_container_error">
                            <div class="input_container_error">
                                <s:property value="fieldErrors.get('uLastName').get(0)"/>
                            </div>
                        </div>
                    </div>
                    <div class="input_container_outer">
                        <div>
                            E-mail address
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <s:textfield name="uEmail" class="tb_general"/>
                            <label for="uEmail"></label>
                        </div>
                        <div class="input_container_error">
                            <div class="input_container_error">
                                <s:property value="fieldErrors.get('uEmail').get(0)"/>
                            </div>
                        </div>
                    </div>
                    <div class="input_container_outer">
                        <div>
                            Phone number
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <s:textfield name="uPhone" class="tb_general" type="number"/>
                            <label for="uPhone"></label>
                        </div>
                        <div class="input_container_error">
                            <div class="input_container_error">
                                <s:property value="fieldErrors.get('uPhone').get(0)"/>
                            </div>
                        </div>
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_inner">
                            <input type="submit" value="Create">
                        </div>
                        <div class="inner_spacer"></div>

                        <s:url action="index" var="cancel_link" />
                        <div class="input_container_inner">
                            <input type="button" value="Cancel" class="no_right_border"
                                   onclick="window.location.href='<s:property value="cancel_link"/>'">
                        </div>
                    </div>
                    <input type="hidden" name="action" value="create">
                </s:form>
            </div>
        </div>
    </body>
</html>
