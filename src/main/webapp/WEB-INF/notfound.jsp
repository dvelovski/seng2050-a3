<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 - Not Found</title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
<body>
<s:include value="common/header.jsp" />
<div class="body_main">
    <div class="body_box body_widget">
        <div class="form_header flex_row">
            <h2>Not Found</h2>
            <div class="group right">
                <span class="issue_status_text">404</span>
            </div>
        </div>
        <div class="article bar">
            The resource requested could not be found.
        </div>
    </div>
</div>
</body>
</html>