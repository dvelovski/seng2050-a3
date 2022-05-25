<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Knowledge Base</title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp">
        </s:include>

        <div class="body_main">
            <div class="body_box body_widget">
                <div class="form_header flex_row">
                    <h2>TODO KB ARTICLE TITLE</h2>
                </div>

                <div class="article bar title">
                    <div class="group left">
                        <div class="ddd"><span>Issue created by </span><span class="boldtext">User</span> on <span>xx</span></div>
                        <div class="ddd"><span>Issue resolved by </span><span class="boldtext">User</span> on <span>xx</span></div>
                    </div>

                </div>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Description of the issue:</span>
                    <div>
                        First of all where in this tutorials ever written that you should use an IDE like IntelliJ? Second you
                        didn't follow or don't understand the tutorial. Third Struts2 tutorials are not for beginners, but for
                        experienced developers, who are familiar with web development, but lacked Struts knowledge. If you don't
                        know how or don't want to follow the tutorial, nobody can help with it.
                    </div>
                </div>
                <div class="divider"></div>
                <div class="article bar contents">
                    <span class="boldtext">Steps to resolve:</span>
                    <div>
                        1) Yes, you should probably use either Maven or Gradle to manage your dependencies (it makes it way
                        easier).

                        2) If you decide to use Maven, all you gotta do is put the pom.xml that the guide you are using has in
                        the root directory of your project. Here's the pom.

                        3) Typically the resources folder goes in src/main (src/main/resources).

                        4) Mkyong.com is a pretty good learning resource. Good luck!
                    </div>
                </div>
                <div class="divider"></div>
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
            </div>
        </div>
    </body>
</html>