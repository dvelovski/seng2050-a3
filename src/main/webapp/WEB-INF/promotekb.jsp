<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Promote issue to Knowledge Base Article</title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
<body>
<s:include value="common/header.jsp" />
<div class="body_main">
    <div class="body_box body_widget">
        <div class="form_header flex_row">
            <h2>Promote Issue - <s:property value="issueReport.title"/></h2>
            <div class="group right">
                <span class="<s:property value="statusClass" />"><s:property value="issueReport.issueStatusString" /></span>
            </div>
        </div>
        <s:if test="displayForm == true">
            <s:form class="column article bar" onsubmit="return validateKnowledgeBasePromotion(this)" method="post" action="submitkb">
                <div class="input_container_outer">
                    <div class="input_container_desc">
                        <label for="kbArticleName">Article title</label>
                        <span class="description_tooltip">?</span>
                        <div class="tooltip_outer">
                            <div class="tooltip_inner">
                                A name that generally describes the issue, making it easy for users to find.<br>
                                This is the title as it will appear in the Knowledge Base.
                            </div>
                        </div>
                    </div>
                    <div class="input_container_inner shorter_elements">
                        <input type="text" class="tb_general" id="kbArticleName" name="kbArticleName" placeholder="Title" value="<s:property value="issueReport.title" />">
                    </div>
                    <div class="input_container_error"></div>
                </div>
                <div class="input_container_outer">
                    <div class="input_container_desc">
                        Category
                    </div>
                    <div>
                        <span class="boldtext"><s:property value="issueReport.category" /></span> &#x3e; <span class="boldtext"><s:property value="issueReport.subCategory" /></span>
                    </div>
                </div>
                <div class="input_container_outer">
                    <div class="input_container_desc">
                        <label for="kbArticleDesc">Issue description</label>
                        <span class="description_tooltip">?</span>
                        <div class="tooltip_outer">
                            <div class="tooltip_inner">
                                Describe the issue with as much detail as you can.<br>
                                (max 4096 characters)
                            </div>
                        </div>
                    </div>
                    <div class="input_container_inner">
                        <textarea class="eight_lines tb_general" name="kbArticleDesc" id="kbArticleDesc" placeholder="Description of the issue" maxlength="4096"><s:property escapeHtml="true" value="issueReport.issueDescription" /></textarea>
                    </div>
                    <div class="input_container_error"></div>
                </div>
                <div class="input_container_outer">
                    <div class="input_container_desc">
                        <label for="kbArticleResolution">Steps to resolve</label>
                        <span class="description_tooltip">?</span>
                        <div class="tooltip_outer">
                            <div class="tooltip_inner">
                                The steps to follow in order to resolve the issue.<br>
                                (max 4096 characters)
                            </div>
                        </div>
                    </div>
                    <div class="input_container_inner">
                        <textarea class="eight_lines tb_general" name="kbArticleResolution" id="kbArticleResolution" placeholder="Description of the resolution steps" maxlength="4096"><s:property escapeHtml="true" value="acceptedSolutionText" /></textarea>
                    </div>
                    <div class="input_container_error"></div>
                </div>
                <div class="input_container_outer">
                    <div class="input_container_desc">
                        <div>Supporting files <span class="boldtext">(<s:property value="issueFiles.size" />)</span>:</div>
                        <span class="description_tooltip">?</span>
                        <div class="tooltip_outer">
                            <div class="tooltip_inner">
                                These files were initially uploaded by the user to support their issue.
                            </div>
                        </div>
                    </div>
                    <div class="inner_attachmentbar">
                        <s:if test="issueFiles.size > 0">
                            <s:iterator value="issueFiles">
                                <div class="inline_attachment_item">
                                    <div class="attachment_icon">
                                        &#128206;
                                    </div>
                                    <div class="attachment_details" title="<s:property escapeHtml="true" value="fileName" />">
                                        <s:property value="fileName" /><br>
                                        <s:property value="fileSizeString" />
                                    </div>
                                </div>
                            </s:iterator>
                        </s:if>
                        <s:else>
                            <s:property value="issueReport.createdBy" /> did not upload any files.
                        </s:else>
                    </div>
                </div>
                <div class="input_container_outer">
                    <div class="input_container_inner">
                        <input type="submit" value="Add to Knowledge Base" class="no_right_border">
                        <div class="input_container icon unselectable right_side">&#10095;</div>
                    </div>
                </div>
                <input type="hidden" name="issueID" value="<s:property value="reportID"/>">
            </s:form>
        </s:if>
        <s:else>
            <div class="colum article bar"><s:property value="creationErrorMessage" /></div>
        </s:else>
    </div>
</div>
</body>
</html>
