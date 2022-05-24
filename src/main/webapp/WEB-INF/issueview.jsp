<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Issue details</title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp">
        </s:include>

        <div class="body_main">
            <s:include value="common/navbuttons.jsp" />

            <div class="body_box body_widget">
                <div class="form_header flex_row">
                    <h2>Issue title</h2>
                    <div class="group right">
                        <span class="issue_status_text">Status</span>
                    </div>
                </div>

                <div class="article bar title">
                    <div class="group left">
                        <div class="ddd"><span>Posted by </span><span class="boldtext">User</span> on <span>xx</span></div>
                        <div class="ddd"><span>Assigned to </span><span class="boldtext">User</span>, last update at <span>xx</span></div>

                    </div>

                </div>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Issue description:</span>
                    <div>
                        First of all where in this tutorials ever written that you should use an IDE like IntelliJ? Second you didn't follow or don't understand the tutorial. Third Struts2 tutorials are not for beginners, but for experienced developers, who are familiar with web development, but lacked Struts knowledge. If you don't know how or don't want to follow the tutorial, nobody can help with it.
                        1) Yes, you should probably use either Maven or Gradle to manage your dependencies (it makes it way easier).

                        2) If you decide to use Maven, all you gotta do is put the pom.xml that the guide you are using has in the root directory of your project. Here's the pom.

                        3) Typically the resources folder goes in src/main (src/main/resources).

                        4) Mkyong.com is a pretty good learning resource. Good luck!
                    </div>
                </div>
                <div class="article bar attachments">
                    <span>Attachments <span class="boldtext">(5)</span>:</span>
                    <div class="inner_attachmentbar">
                        <div class="inline_attachment_item">
                            <div class="attachment_icon">
                                &#128206;
                            </div>
                            <div class="attachment_details">
                                Filename<br>
                                xxx KB
                            </div>
                        </div>
                        <div class="inline_attachment_item">
                            <div class="attachment_icon">
                                &#128206;
                            </div>
                            <div class="attachment_details">
                                Filename<br>
                                xxx KB
                            </div>
                        </div>
                        <div class="inline_attachment_item">
                            <div class="attachment_icon">
                                &#128206;
                            </div>
                            <div class="attachment_details">
                                Filename<br>
                                xxx KB
                            </div>
                        </div>
                        <div class="inline_attachment_item">
                            <div class="attachment_icon">
                                &#128206;
                            </div>
                            <div class="attachment_details">
                                Filename<br>
                                xxx KB
                            </div>
                        </div>
                        <div class="inline_attachment_item">
                            <div class="attachment_icon">
                                &#128206;
                            </div>
                            <div class="attachment_details">
                                Filename<br>
                                xxx KB
                            </div>
                        </div>
                    </div>
                </div>
                <div class="divider"></div>
                <div class="article bar replies">
                    <span>Replies <span class="boldtext">(3)</span>:</span>
                    <div class="article replies_outer">
                        <div class="article_reply_inner">
                            <div class="article_reply_header boldtext">
                                Lol
                            </div>
                            <div class="article_reply_comment">
                                123
                            </div>
                        </div>
                        <div class="article_reply_inner">
                            <div class="article_reply_header boldtext">
                                Lol
                            </div>
                            <div class="article_reply_comment">
                                123
                            </div>
                        </div>
                        <div class="article_reply_inner accepted">
                            <div class="article_reply_header boldtext">
                                Lol
                            </div>
                            <div class="article_reply_comment">
                                123
                            </div>
                        </div>
                    </div>
                    <div class="divider"></div>
                    <s:form action="issuecomment" class="column">
                        <div class="input_container_outer">
                            <div>Add a new reply:</div>
                            <div class="input_container_inner">
                                <s:textarea class="four_lines tb_general" id="iNewComment" name="cCommentText" />
                                <label for="iNewComment"></label>
                                <div class="input_container_error">
                                    Error!
                                </div>
                            </div>
                        </div>

                        <!-- TODO for staff only -->
                        <div class="input_container_outer">
                            <input type="checkbox" id="cResolvesIssue" name="cResolvesIssue" />
                            <label for="cResolvesIssue">Mark as Resolved</label>
                        </div>
                        <div class="input_container_outer">
                            <div class="input_container_inner">
                                <input type="submit" value="Add comment" class="no_right_border">
                                <div class="input_container icon unselectable right_side">&#10095;</div>
                            </div>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>