<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Homepage</title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp" />
        <div class="body_main">
            <s:include value="common/navbuttons.jsp" />

            <div class="body_box body_widget">
                <div class="form_header">
                    <h2><s:property value="homepageHeading" /></h2>
                </div>
                <div class="index_table">
                    <div class="outer_element utility headerbar">
                        Displaying items <span class="boldtext"><s:property value="resultStart" />-<s:property value="resultsOnPage" /></span>
                    </div>
                    <div class="outer_element contents">
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
                                <div class="truncated_text">
                                    <s:property escapeHtml="true" value="issueDescription" />
                                </div>
                            </div>
                        </s:iterator>
                    </div>
                    <div class="outer_element utility pagination">Pagination toolbar</div>
                </div>
            </div>
            <s:if test="showStatistics == true">
                <div class="body_box body_widget">
                    <div class="form_header">
                        <h2>Tracker Statistics</h2>
                    </div>
                    <div class="article bar rel">
                        <span class="boldtext">Stress rate</span> <s:property value="stressRate" />
                        <span class="description_tooltip">?</span>
                        <div class="tooltip_outer">
                            <div class="tooltip_inner">
                                Equal to:<br>
                                Unsolved Issues / Number of Staff * 5
                            </div>
                        </div>
                    </div>
                    <div class="tracker_table">
                        <div class="table_content_row">
                            <div class="table_row_inner header">
                                <span></span>
                                <span>Category</span>
                                <span>Total unsolved</span>
                                <span>Resolved in last 7 days</span>
                            </div>
                        </div>
                        <s:iterator value="issueCategories">
                            <div class="table_row_inner">
                                <span class="subcontent_expander">&#x25BC;</span>
                                <span><s:property value="categoryName" /></span>
                                <span><s:property value="totalUnresolved" /></span>
                                <span><s:property value="resolvedLastSevenDays" /></span>
                            </div>
                            <div class="table_row_subcontent">
                                <s:iterator value="subCategoryBeans">
                                    <div class="table_row_inner">
                                        <span></span>
                                        <span><s:property value="categoryName" /></span>
                                        <span><s:property value="totalUnresolved" /></span>
                                        <span><s:property value="resolvedLastSevenDays" /></span>
                                    </div>
                                </s:iterator>
                            </div>
                        </s:iterator>
                    </div>
                </div>
            </s:if>
        </div>

        <script>
            var expanders = document.getElementsByClassName("subcontent_expander");
            for (var i = 0; i < expanders.length; i++){
                let theExpander = expanders[i];
                let parent = theExpander.parentElement;
                let subContent = parent.nextElementSibling;

                theExpander.addEventListener("click", function(){
                    if (subContent.classList.contains("expanded")){
                        subContent.classList.remove("expanded");
                        theExpander.classList.remove("expanded");
                    }else{
                        subContent.classList.add("expanded");
                        theExpander.classList.add("expanded");
                    }
                });
            }
        </script>
    </body>
</html>