<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Issue details - <s:property value="issueReport.title" /></title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp">
        </s:include>

        <div class="body_main">
            <s:include value="common/navbuttons.jsp" />
            <div class="body_box body_widget">
                <div class="form_header flex_row">
                    <h2><s:property value="issueReport.title" /></h2>
                    <div class="group right">
                        <span class="<s:property value="statusClass" />"><s:property value="issueReport.issueStatusString" /></span>
                    </div>
                </div>

                <div class="article bar title">
                    <div class="group left">
                        <div class="ddd"><span>Posted by </span><span class="boldtext"><s:property escapeHtml="true" value="issueReport.createdBy" default="unknown"/></span> on <span><s:property value="issueReport.reportedAt" default="unknown" /></span></div>
                        <s:if test="showAssignedUser == true">
                            <div class="ddd"><span>Assigned to </span><span class="boldtext"><s:property escapeHtml="true" value="issueReport.assignedToName" default="unknown"/></span></div>
                        </s:if>
                        <div>
                            <span>Filed under: </span>
                            <span class="boldtext"><s:property value="issueReport.category" /></span> &#x3e; <span class="boldtext"><s:property value="issueReport.subCategory" /></span>
                        </div>
                        <s:if test="showKBSegment == true">
                            <div class="in_article_kb_hero">
                                This issue has been added to the Knowledge Base
                            </div>
                        </s:if>
                    </div>
                    <div class="group right">
                        <s:url action="promotekb" var="promo_link">
                            <s:param name="reportID" value="issueReport.id" />
                        </s:url>


                        <s:if test="showKBPromotion == true">
                            <div class="input_container_inner flex_reverse clickable" onclick="window.location='<s:property value="promo_link" />'">
                                <button type="button">Send to Knowledge Base</button>
                            </div>
                        </s:if>
                    </div>
                </div>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Issue description:</span>
                    <div><s:property escapeHtml="true" value="issueReport.issueDescription" /></div>
                </div>
                <div class="divider"></div>
                <div class="article bar attachments">
                    <s:if test="issueFiles.size > 0">
                        <span class="boldtext">Attachments (<s:property value="issueFiles.size" />)</span>:
                        <div class="inner_attachmentbar">
                        <s:iterator value="issueFiles">
                            <s:url action="download" var="dlLink">
                                <s:param name="requestedFileID"><s:property value="fileID"/></s:param>
                            </s:url>
                            <a class="inline_attachment_item" href="${dlLink}" download="<s:property value="fileName"/>">
                                <div class="attachment_icon">
                                    &#128206;
                                </div>
                                <div class="attachment_details" title="<s:property value="fileName" />">
                                    <s:property escapeHtml="true" value="fileName" /><br>
                                    <s:property value="fileSizeString" />
                                </div>
                            </a>
                        </s:iterator>
                        </div>
                    </s:if>
                    <s:else>
                        <span class="boldtext">Attachments:</span>
                        <div class="inner_attachmentbar">
                            <s:property value="issueReport.createdBy" /> did not upload any files.
                        </div>
                    </s:else>
                </div>
                <div class="divider"></div>
                <div class="article bar replies">
                    <span class="boldtext">Replies (<s:property value="issueComments.size" />)</span>:
                    <div class="article replies_outer">
                        <s:if test="issueComments.size == 0">
                            <div class="article_reply_inner">
                                No comments have been posted on this issue yet.
                            </div>
                        </s:if>
                        <s:iterator value="issueComments">
                            <div class="article_reply_inner">
                                <a id="comment<s:property value="commentID" />"></a>
                                <div class="article_reply_header">
                                    <span class="boldtext"><s:property escapeHtml="true" value="postedByName"/></span> <span class="small_italic"><s:property value="postedAt" default="unknown"/></span>
                                </div>
                                <div class="article_reply_comment">
                                    <s:property escapeHtml="true" value="content" />
                                </div>
                            </div>
                        </s:iterator>
                    </div>
                    <s:if test="allowCommentInput == true">
                        <div class="divider"></div>
                        <s:form action="issuecomment" class="column" method="post"> <!-- TODO validate -->
                            <div class="input_container_outer">
                                <div>Add a new reply:</div>
                                <div class="input_container_inner">
                                    <s:textarea class="four_lines tb_general" id="iNewComment" name="cCommentText" />
                                    <label for="iNewComment"></label>
                                    <div class="input_container_error">
                                    </div>
                                </div>
                            </div>

                            <s:if test="allowCommentMarkAsSolution == true">
                                <div class="input_container_outer">
                                    <input type="checkbox" id="cResolvesIssue" name="cResolvesIssue" />
                                    <label for="cResolvesIssue">Mark as Resolved</label>
                                </div>
                            </s:if>

                            <div class="input_container_outer">
                                <div class="input_container_inner">
                                    <input type="submit" value="Add comment" class="no_right_border">
                                    <div class="input_container icon unselectable right_side">&#10095;</div>
                                </div>
                            </div>

                            <input type="hidden" name="issueID" value="<s:property value="issueReport.id" />">
                        </s:form>
                    </s:if>

                </div>
            </div>
        </div>
    </body>
</html>