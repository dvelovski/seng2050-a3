<%@ taglib prefix="s" uri="/struts-tags" %>
<s:url action="userview" var="user_link">
    <s:param name="userID" value="#session.userBean.userIdentification" />
</s:url>
<s:url action="usercreate" var="management_link" />
<s:url action="index" var="index_link" />
<script src="scripts/validators.js"></script>
<header>
    <div class="header_inner">
        <div class="hgroup left">
            <s:a href="%{#index_link}" class="header_item logo clickable">
                SENG2050_A3
            </s:a>
            <s:property value="#session.userBean.userIdentification"/>
        </div>
        <s:if test="#session.loggedIn != 0">
            <div class="hgroup search">
                <s:form action="search" class="header_item" method="GET" onsubmit="return validateSearchForm(this)">
                    <input type="search" name="searchQuery" id="header_search" class="tb_general form_input search" placeholder="Search...">
                    <input type="submit" class="form_button search no_left_border" value="&#x1F50E;&#xFE0E;">
                    <label for="header_search"></label>
                </s:form>
            </div>
            <div class="hgroup right">
                <s:a href="%{#user_link}" class="header_item user_menu hoverable clickable unselectable">
                    User
                </s:a>
                <s:if test="#session.userBean.userType.toString == 'Staff'">
                    <s:a href="%{#management_link}" class="header_item user_menu hoverable clickable unselectable">
                        Management
                    </s:a>
                </s:if>
                <s:a href="logout" class="header_item user_logout hoverable clickable unselectable">
                    Logout
                </s:a>
            </div>
        </s:if>
    </div>
</header>