<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Knowledge Base - <s:property value="searchQuery" /> </title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
<body>
<s:include value="common/header.jsp" />
<div class="body_main">
    <div class="body_box body_widget search_box">
        <div class="form_header">
            <h2>Knowledge Base</h2>
        </div>
        <s:include value="common/kbsearchwidget.jsp">
            <s:param name="validateQuery" value="false" />
            <s:param name="searchTarget"><s:url action="knowledgebaseindex"/></s:param>
        </s:include>
        <s:if test="results.size > 0">
            <div class="index_table">
                <div class="outer_element contents tall">
                    <s:iterator value="results">
                        <s:url action="knowledgebase" var="article_view">
                            <s:param name="articleID" value="articleId" />
                        </s:url>
                        <div class="index_entry" title="Click to view" onclick="window.location = '${article_view}'">
                            <div class="titlebar">
                                <span class="title_text"><s:property escapeHtml="true" value="articleName"/></span>
                            </div>
                            <div class="subtitle">
                                <span class="boldtext"><s:property value="categoryName" /></span> &#x3e; <span class="boldtext"><s:property value="subCategoryName" /></span>
                            </div>
                            <div class="truncated_text">
                                <s:property escapeHtml="true" value="articleText" />
                            </div>
                        </div>
                    </s:iterator>
                </div>
            </div>
        </s:if>
        <s:else>
            <s:if test="searchQuery.isEmpty == true && fCatInt > 0">
                <h3>
                    There are no knowledge base articles matching your selections.
                </h3>
            </s:if>
        </s:else>
    </div>
</div>
</body>
</html>