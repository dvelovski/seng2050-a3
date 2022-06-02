<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
    <div class="hgroup search">
        <s:form action="search" method="post" class="column" onsubmit="return validateSearchForm(this)">
            <div class="search_util_grid_row">
                <label for="header_search">Query</label>
                <label for="filter_category">Category</label>
            </div>
            <div class="search_util_grid_row">
                <input type="search" name="searchQuery" id="header_search" class="tb_general form_input"
                       placeholder="Search..." value="<s:property value="searchQuery" />">
                <select class="tb_general" name="filterCategory" id="filter_category">
                    <option value="0">All</option>
                    <optgroup label="Network">
                        <option value="1">Can't connect</option>
                        <option value="2">Speed</option>
                        <option value="3">Constant dropouts</option>
                    </optgroup>
                    <optgroup label="Software">
                        <option value="4">Slow to load</option>
                        <option value="5">Won't load at all</option>
                    </optgroup>
                    <optgroup label="Hardware">
                        <option value="6">Computer won't turn on</option>
                        <option value="7">Computer "blue screens"</option>
                        <option value="8">Disk drive</option>
                        <option value="9">Peripherals</option>
                    </optgroup>
                    <optgroup label="Email">
                        <option value="10">Can't send</option>
                        <option value="11">Can't receive</option>
                        <option value="12">Spam / phishing</option>
                    </optgroup>
                    <optgroup label="Account">
                        <option value="13">Password reset</option>
                        <option value="14">Wrong details</option>
                    </optgroup>
                </select>
                <script>document.getElementById('filter_category').selectedIndex = <s:property escapeJavaScript="true" value="fCatInt" default="0" />;</script>
                <input type="submit" class="form_button search" value="&#x1F50E;&#xFE0E;">
            </div>
        </s:form>
    </div>
</div>