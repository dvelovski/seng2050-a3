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
<s:include value="common/header.jsp">
</s:include>
<div class="body_main">
    <div class="body_box body_widget search_box">
        <div class="form_header">
            <h2>Knowledge Base - <s:property value="searchQuery" /></h2>
        </div>
        <s:include value="common/kbsearchwidget.jsp" />
        <s:if test="results.size > 0">
            <div class="index_table">
                <div class="outer_element utility headerbar">
                    Displaying items <span class="boldtext">1-10</span> of
                    <span class="boldtext"><s:property value="results.size" /></span>
                </div>
                <div class="outer_element contents tall">
                    <s:iterator value="results">
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text"><s:property /></span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                <s:property />
                            </div>
                        </div>
                    </s:iterator>
                </div>
            </div>
        </s:if>
        <s:else>
            <h3>
                There are no knowledge base articles matching your selections.
            </h3>
        </s:else>
    </div>
</div>
</body>
</html>