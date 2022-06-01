<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Issue Catalog</title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
<body>
<s:include value="common/header.jsp" />
<div class="body_main">
    <s:include value="common/navbuttons.jsp" />

    <div class="body_box body_widget">
        <div class="form_header">
            <h2>Browsing issue reports</h2>
        </div>
        <div class="index_table">
            <div class="outer_element utility headerbar">
                Displaying items <span class="boldtext"><s:property value="resultStart" />-<s:property value="resultsOnPage" /> of <span class="boldtext"><s:property value="resultCount" /></span></span>
            </div>
            <div class="outer_element contents tall">
                <s:iterator value="issueReports">
                    <s:url action="viewissue" var="issue_view">
                        <s:param name="id" value="id" />
                    </s:url>
                    <s:if test="issueStatus == 1">
                        <s:set var="status_class">issue_status_text new</s:set>
                    </s:if>
                    <s:elseif test="issueStatus == 2">
                        <s:set var="status_class">issue_status_text inprogress</s:set>
                    </s:elseif>
                    <s:elseif test="issueStatus == 3">
                        <s:set var="status_class">issue_status_text resolved</s:set>
                    </s:elseif>
                    <s:elseif test="issueStatus == 4">
                        <s:set var="status_class">issue_status_text completed</s:set>
                    </s:elseif>
                    <s:else>
                        <s:set var="status_class">issue_status_text</s:set>
                    </s:else>
                    <div class="index_entry" onclick="window.location = '${issue_view}'" title="Click to navigate to this issue">
                        <div class="titlebar">
                            <span class="title_text"><s:property value="title" /></span>
                            <span class="${status_class}"><s:property value="issueStatusString" /></span>
                        </div>
                        <div class="subtitle">
                            <span class="boldtext"><s:property value="category" /></span> &#x3e; <span class="boldtext"><s:property value="subCategory" /></span>
                        </div>
                        <div class="truncated_text">
                            <s:property escapeHtml="true" value="issueDescription" />
                        </div>
                    </div>
                </s:iterator>
            </div>
            <div class="outer_element utility pagination">
                <s:form action="issuecat" method="post" name="pagination_form">
                    <div class="pagination_buttons">
                        <div class="pagination_button" title="Back to page 1" onclick="updateFormAndSubmit('pagination_form', 'p.first')"><<</div> <!-- page #0 -->
                        <div class="pagination_button" title="Back 1 page" onclick="updateFormAndSubmit('pagination_form', 'p.prev')"><</div>
                        <div class="pagination_button" title="Forward one page" onclick="updateFormAndSubmit('pagination_form', 'p.next')">></div>
                        <div class="pagination_button" title="Go to last page" onclick="updateFormAndSubmit('pagination_form', 'p.last')">>></div>
                    </div>
                    <input type="hidden" name="action" value="">
                    <input type="hidden" name="pageNumber" value="<s:property value="pageNumber" />">
                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>