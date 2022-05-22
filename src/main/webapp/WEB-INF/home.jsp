<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="styles/assi3.css">
</head>
<body>
    <s:include value="common/header.jsp" />
    <div class="body_main">
        <div class="body_box body_widget link_buttons transparent">
            <s:if test="userType == 'Staff'">
                <div class="input_container_inner flex_reverse clickable">
                    <button type="button">Open issues</button>
                    <div class="input_container icon unselectable left_side green">&#x1F50E;&#xFE0E;</div>
                </div>
            </s:if>
            <s:else>
                <s:url action="reportissue" var="rep_link"/>
                <s:a href="%{#rep_link}" class="input_container_inner flex_reverse clickable">
                    <button type="button">Report an issue</button>
                    <div class="input_container icon unselectable left_side green">&#10133;</div>
                </s:a>
            </s:else>

            <s:url action="knowledgebaseindex" var="kb_link"/>
            <s:a href="%{#kb_link}" class="input_container_inner flex_reverse clickable">
                <button type="button">Knowledge Base</button>
                <div class="input_container icon unselectable left_side green">&#129504;</div>
            </s:a>
        </div>

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
                <!-- TODO add the "stress" metrics -->
                <div class="tracker_table">
                    <div class="table_content_row">
                        <div class="table_row_inner header">
                            <span></span>
                            <span>Category</span>
                            <span>Unsolved in last 7 days</span>
                            <span>Total unsolved</span>
                        </div>
                    </div>
                    <div class="table_content_row">
                        <div class="table_row_inner">
                            <span class="subcontent_expander">&#x25BC;</span>
                            <span>Network</span>
                            <span>1</span>
                            <span>15</span>
                        </div>
                        <div class="table_row_subcontent">
                            <div class="table_row_inner">
                                <span></span>
                                <span>Can't connect</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Speed</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Constant dropouts</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                        </div>
                    </div>
                    <div class="table_content_row">
                        <div class="table_row_inner">
                            <span class="subcontent_expander">&#x25BC;</span>
                            <span>Software</span>
                            <span>1</span>
                            <span>15</span>
                        </div>
                        <div class="table_row_subcontent">
                            <div class="table_row_inner">
                                <span></span>
                                <span>Slow to load</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Won't load at all</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                        </div>
                    </div>
                    <div class="table_content_row">
                        <div class="table_row_inner">
                            <span class="subcontent_expander">&#x25BC;</span>
                            <span>Hardware</span>
                            <span>1</span>
                            <span>15</span>
                        </div>
                        <div class="table_row_subcontent">
                            <div class="table_row_inner">
                                <span></span>
                                <span>Computer won't turn on</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Computer "blue screens"</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Disk drive</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Peripherals</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                        </div>
                    </div>
                    <div class="table_content_row">
                        <div class="table_row_inner">
                            <span class="subcontent_expander">&#x25BC;</span>
                            <span>Email</span>
                            <span>1</span>
                            <span>15</span>
                        </div>
                        <div class="table_row_subcontent">
                            <div class="table_row_inner">
                                <span></span>
                                <span>Can't send</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Can't receive</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Spam / Phishing</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                        </div>
                    </div>
                    <div class="table_content_row">
                        <div class="table_row_inner">
                            <span class="subcontent_expander">&#x25BC;</span>
                            <span>Account</span>
                            <span>1</span>
                            <span>15</span>
                        </div>
                        <div class="table_row_subcontent">
                            <div class="table_row_inner">
                                <span></span>
                                <span>Password reset</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                            <div class="table_row_inner">
                                <span></span>
                                <span>Wrong details</span>
                                <span>1</span>
                                <span>3</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:if>
    </div>
</body>
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
</html>