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
                    </div>
                    <div class="group right">
                        <s:if test="showKBPromotion == true">
                            <s:form action="promotekb" method="post">
                                <input type="hidden" name="reportID" value="<s:property value="issueReport.id"/>"/>
                                <s:url action="promotekb" var="promo_link">
                                    <s:param name="reportID" value="issueReport.id" />
                                </s:url>
                                <div class="input_container_inner flex_reverse clickable">
                                    <input type="submit" value="Send to knowledge base">
                                </div>
                            </s:form>

                        </s:if>
                        <s:if test="showAssignmentOption == true">
                            <s:form action="updateissue" method="post">
                                <input type="hidden" name="action" value="i.assign" />
                                <input type="hidden" name="requestorID" value="<s:property value="#session.userBean.userIdentification"/>"/>
                                <input type="hidden" name="issueID" value="<s:property value="issueReport.id"/>"/>
                                <div class="input_container_inner flex_reverse clickable">
                                    <input type="submit" value="Assign to me">
                                </div>
                            </s:form>
                        </s:if>
                    </div>
                </div>
                <s:if test="showKBSegment == true">
                    <div class="in_article_kb_hero">
                        <s:url action="knowledgebase" var="kb_hero_link">
                            <s:param name="articleID" value="issueReport.knowledgeBaseArticleId" />
                        </s:url>
                        This issue has been added to the <a href="${kb_hero_link}">Knowledge Base</a>.
                    </div>
                </s:if>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Issue description:</span>
                    <div><s:property escapeHtml="true" value="issueReport.issueDescription" /></div>
                </div>
                <div class="divider"></div>
                <s:include value="common/fileswidget.jsp" />
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
                            <s:if test="commentID == issueReport.proposedSolution">
                                <s:set var="reply_class">article_reply_inner proposed</s:set>
                            </s:if>
                            <s:elseif test="commentID == issueReport.acceptedSolution">
                                <s:set var="reply_class">article_reply_inner accepted</s:set>
                            </s:elseif>
                            <s:else>
                                <s:set var="reply_class">article_reply_inner</s:set>
                            </s:else>
                            <div class="<s:property value="#reply_class"/>">
                                <a id="comment<s:property value="commentID" />"></a>
                                <div class="article_reply_header">
                                    <span class="boldtext"><s:property escapeHtml="true" value="postedByName"/></span> <span class="small_italic"><s:property value="postedAt" default="unknown"/></span>
                                </div>
                                <div class="article_reply_comment">
                                    <s:property escapeHtml="true" value="content" />
                                </div>
                                <s:if test="showAcceptanceOptions && commentID == issueReport.proposedSolution">
                                    <s:form action="updateissue" method="post" name="acceptance_form">
                                        <div class="solution_acceptance_options">
                                            <div class="solution_button accept" title="This resolves my issue" onclick="updateFormAndSubmit('acceptance_form', 's.accept')">Accept</div>
                                            <div class="solution_button reject" title="This did not resolve my issue" onclick="updateFormAndSubmit('acceptance_form', 's.reject')">Reject</div>
                                        </div>
                                        <input type="hidden" name="action" value="">
                                        <input type="hidden" name="issueID" value="<s:property value="issueReport.id" />">
                                        <input type="hidden" name="commentID" value="<s:property value="commentID" />">
                                    </s:form>
                                </s:if>
                            </div>
                        </s:iterator>
                    </div>
                    <s:if test="allowCommentInput == true">
                        <div class="divider"></div>
                        <s:form action="issuecomment" class="column" method="post" onsubmit="/*return validateCommentForm(this)*/">
                            <div class="input_container_outer">
                                <div><label for="iNewComment">Add a new comment:</label></div>
                                <div class="input_container_inner">
                                    <s:textarea class="four_lines tb_general" id="iNewComment" name="cCommentText" />
                                    <div class="input_container_error">
                                    </div>
                                </div>
                            </div>

                            <s:if test="allowCommentMarkAsSolution == true">
                                <div class="input_container_outer">
                                    <s:checkbox name="cResolvesIssue" fieldValue="true" value="false"/>
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