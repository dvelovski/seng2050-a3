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
                Displaying items <span class="boldtext"><s:property value="resultStart" />-<s:property value="resultsOnPage" /> of <span class="boldtext"><s:property value="resultCount" /> </span></span>
                <s:if test="catGroupByStatus == true">
                    (grouped by status)
                </s:if>
                <s:else>
                    (sorted by date reported)
                </s:else>
                <s:form action="issuecat" id="icat1" class="index_table inner_table" method="post">
                    <div class="group left">
                        <div>
                            <s:checkbox name="catGroupByStatus" id="catGroupByStatus" fieldValue="true" value="catGroupByStatus"/>
                            <label for="catGroupByStatus">Group issues by status</label>
                        </div>
                        <div>
                            <s:checkbox name="showResolved" id="showResolved" fieldValue="true" value="showResolved"/>
                            <label for="showResolved">Display resolved issues</label>
                        </div>
                    </div>
                    <div class="group right">
                        <div class="input_container_inner">
                            <input type="submit" value="Update filters">
                        </div>
                    </div>
                </s:form>
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
                <s:form action="issuecat" id="icat2" method="post" name="pagination_form">
                    <div class="pagination_buttons">
                        <div class="pagination_button" title="Back to page 1" onclick="updateFormAndSubmit('pagination_form', 'p.first')">&lt;&lt;</div> <!-- page #0 -->
                        <div class="pagination_button" title="Back 1 page" onclick="updateFormAndSubmit('pagination_form', 'p.prev')">&lt;</div>
                        <div class="pagination_button" title="Forward one page" onclick="updateFormAndSubmit('pagination_form', 'p.next')">&gt;</div>
                        <div class="pagination_button" title="Go to last page" onclick="updateFormAndSubmit('pagination_form', 'p.last')">&gt;&gt;</div>
                    </div>
                    <input type="hidden" name="action" value="">
                    <input type="hidden" name="pageNumber" value="<s:property value="pageNumber" />">
                    <input type="hidden" name="catGroupByStatus" value="<s:property value="catGroupByStatus" />">
                    <input type="hidden" name="showResolved" value="<s:property value="showResolved" />">
                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>