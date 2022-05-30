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
                        Displaying items <span class="boldtext">1-10</span> of <span class="boldtext">xx</span>
                    </div>
                    <div class="outer_element contents">
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                I used to call this “the weird WebKit flexbox way”, but in an extra weird twist, the spec now includes this as part of the overflow module, old flexbox and all.
                            </div>
                        </div>
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                And, Firefox implemented it just like that. And with Edge-gone-Chromium, this weird technique has gotten a lot more useful instead of less.
                            </div>
                        </div>
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                A boolean value that, if true, indicates that the function specified by listener will never call preventDefault(). If a passive listener does call preventDefault(), the user agent will do nothing other than generate a console warning.
                            </div>
                        </div>
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                When I built the project in IntelliJ, File > New Project > Java > Web Application > Struts 2. This created the setup you will see in the picture above. It appears to have added all needed files and structure to the project, with one exception, Step 4 - Add Logging(log4j). As far as I can tell, the IntelliJ Struts2 setup pulled in most/all of the needed dependencies, except for:
                            </div>
                        </div>
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                1) Yes, you should probably use either Maven or Gradle to manage your dependencies (it makes it way easier).

                                2) If you decide to use Maven, all you gotta do is put the pom.xml that the guide you are using has in the root directory of your project. Here's the pom.
                            </div>
                        </div>
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                • How secure is your system? Is there any way to break your system (SQL injection, XXS, modifying
                                request parameters)?
                                • Does your submission contain all the file necessary to install and run your system? In particular,
                                does it contain the scripts and data files needed to create and populate the database, and does
                                it contain a readme file briefly explaining how to install the system?
                            </div>
                        </div>
                        <div class="index_entry">
                            <div class="titlebar">
                                <span class="title_text">Issue title</span>
                                <span class="issue_status_text">Status</span>
                            </div>
                            <div class="truncated_text">
                                #f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5#f5f5f5
                            </div>
                        </div>
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
                    <!-- TODO add the "stress" metrics -->
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