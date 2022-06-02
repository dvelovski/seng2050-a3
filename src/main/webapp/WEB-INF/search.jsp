<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results - <s:property value="searchQuery" /> </title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
<body>
<s:include value="common/header.jsp">
</s:include>
<div class="body_main">
    <div class="body_box body_widget search_box">
        <div class="form_header">
            <h2>Search Results - <s:property value="searchQuery" /></h2>
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
                <div class="outer_element utility pagination">
                    <s:form action="home" method="post" name="pagination_form">
                        <div class="pagination_buttons">
                            <div class="pagination_button" title="Back to page 1" onclick="updateFormAndSubmit('pagination_form', 'p.first')"><<</div> <!-- page #0 -->
                            <div class="pagination_button" title="Back 1 page" onclick="updateFormAndSubmit('pagination_form', 'p.prev')"><</div>
                            <div class="pagination_button" title="Forward one page" onclick="updateFormAndSubmit('pagination_form', 'p.next')">></div>
                            <div class="pagination_button" title="Go to last page" onclick="updateFormAndSubmit('pagination_form', 'p.last')">>></div>
                        </div>
                        <input type="hidden" name="action" value="">
                        <input type="hidden" name="searchQuery" value="<s:property value="searchQuery" />">
                        <input type="hidden" name="pageNumber" value="<s:property value="pageNumber" />">
                    </s:form>
                </div>
            </div>
        </s:if>
        <s:else>
            <h3>
                There were no results matching your query.
            </h3>
        </s:else>
    </div>
</div>
</body>
</html>