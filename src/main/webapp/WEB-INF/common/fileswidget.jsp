<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="article bar attachments">
    <s:if test="issueFiles.size > 0">
        <span class="boldtext">Attachments (<s:property value="issueFiles.size"/>)</span>:
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
                        <s:property escapeHtml="true" value="fileName"/><br>
                        <s:property value="fileSizeString"/>
                    </div>
                </a>
            </s:iterator>
        </div>
    </s:if>
    <s:else>
        <span class="boldtext">Attachments:</span>
        <div class="inner_attachmentbar">
            No files were uploaded to this report.
        </div>
    </s:else>
</div>