<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Knowledge Base - <s:property escapeHtml="true" value="articleBean.articleName" /></title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp">
        </s:include>

        <div class="body_main">
            <s:include value="common/navbuttons.jsp" />
            <div class="body_box body_widget">
                <div class="form_header flex_row">
                    <h2><s:property escapeHtml="true" value="articleBean.articleName" /></h2>
                </div>

                <div class="article bar title">
                    <div class="group left">
                        <div class="ddd"><span>Date / time of initial issue: </span><span class="boldtext"><s:property value="articleBean.reportedTime" default="unknown"/></span></div>
                        <div class="ddd"><span>Date / time of issue resolution: </span><span class="boldtext"><s:property value="articleBean.resolutionTime" default="unknown"/></span></div>
                        <div>
                            <span>Filed under: </span>
                            <span class="boldtext"><s:property value="articleBean.categoryName" /></span> &#x3e; <span class="boldtext"><s:property value="articleBean.subCategoryName" /></span>
                        </div>
                        <div>Reported by:
                            <span class="boldtext"><s:property value="articleBean.creatorName" /></span>
                        </div>
                    </div>
                </div>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Description of the issue:</span>
                    <div><s:property value="articleBean.articleText" escapeHtml="true"/></div>
                </div>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Steps to resolve:</span>
                    <div><s:property value="articleBean.solutionText" escapeHtml="true"/></div>
                </div>
                <div class="divider"></div>
                <s:include value="common/fileswidget.jsp" />
            </div>
        </div>
    </body>
</html>