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
            <h2>Promote Issue - </h2>
            <div class="group right">
                <span class="issue_status_text resolved">resolved</span>
            </div>
        </div>
        <form class="column article bar">
            <div class="input_container_outer">
                <div class="input_container_desc">
                    Article title
                    <span class="description_tooltip">?</span>
                    <div class="tooltip_outer">
                        <div class="tooltip_inner">
                            A name that generally describes the issue, making it easy for users to find.<br>
                            This is the title as it will appear in the Knowledge Base.
                        </div>
                    </div>
                </div>
                <div class="input_container_inner shorter_elements">
                    <input type="text" class="tb_general" placeholder="Title">
                </div>
                <div class="input_container_error">Error</div>
            </div>
            <div class="input_container_outer">
                <div class="input_container_desc">
                    Issue category
                </div>
                <div class="boldtext">Selected category -> Selected subcategory</div>
            </div>
            <div class="input_container_outer">
                <div class="input_container_desc">
                    Issue description
                    <span class="description_tooltip">?</span>
                    <div class="tooltip_outer">
                        <div class="tooltip_inner">
                            Describe the issue with as much detail as you can.<br>
                            (max 4096 characters)
                        </div>
                    </div>
                </div>
                <div class="input_container_inner">
                    <textarea class="eight_lines tb_general" placeholder="Description of your issue" maxlength="4096"></textarea>
                </div>
                <div class="input_container_error">Error</div>
            </div>
            <div class="input_container_outer">
                <div class="input_container_desc">
                    Resolution
                    <span class="description_tooltip">?</span>
                    <div class="tooltip_outer">
                        <div class="tooltip_inner">
                            The steps to follow in order to resolve the issue.<br>
                            (max 4096 characters)
                        </div>
                    </div>
                </div>
                <div class="input_container_inner">
                    <textarea class="eight_lines tb_general" placeholder="Description of your issue" maxlength="4096"></textarea>
                </div>
                <div class="input_container_error">Error</div>
            </div>
            <div class="input_container_outer">
                <div class="input_container_desc">
                    Supporting files
                    <span class="description_tooltip">?</span>
                    <div class="tooltip_outer">
                        <div class="tooltip_inner">
                            These files were initially uploaded by the user to support their issue.
                        </div>
                    </div>
                </div>
                <div class="inner_attachmentbar">
                    <!-- todo iterator -->
                </div>
            </div>
            <div class="input_container_outer">
                <div class="input_container_inner">
                    <input type="submit" value="Add to Knowledge Base" class="no_right_border">
                    <div class="input_container icon unselectable right_side">&#10095;</div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
