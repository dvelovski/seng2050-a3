<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="body_box body_widget link_buttons transparent">
    <s:if test="userType == 'Staff'">
        <div class="input_container_inner flex_reverse clickable">
            <button type="button">Open issues</button>
            <div class="input_container icon unselectable left_side green">&#x1F50E;&#xFE0E;</div>
        </div>
    </s:if>
    <s:else>
        <s:url action="reportissue" var="rep_link"/>
        <div class="input_container_inner flex_reverse clickable" onclick="window.location='<s:property value="rep_link" />'">
            <button type="button">Report an issue</button>
            <div class="input_container icon unselectable left_side green">&#10133;</div>
        </div>
    </s:else>

    <s:url action="knowledgebaseindex" var="kb_link"/>
    <div class="input_container_inner flex_reverse clickable"  onclick="window.location='<s:property value="kb_link" />'">
        <button type="button">Knowledge Base</button>
        <div class="input_container icon unselectable left_side green">&#129504;</div>
    </div>
</div>