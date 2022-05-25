<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create New Issue</title>
        <link rel="stylesheet" href="styles/assi3.css">
    </head>
    <body>
        <s:include value="common/header.jsp" />
        <div class="body_main">
            <div class="body_box body_widget">
                <div class="form_header flex_row">
                    <h2>Report a new issue</h2>
                    <div class="group right">
                        <span class="issue_status_text new">new</span>
                    </div>
                </div>
                <s:form action="submitissue" class="column article bar" onsubmit="return validateIssueReport(this)" method="post" enctype="multipart/form-data">
                    <div class="input_container_outer">
                        <div class="input_container_desc">
                            <label for="newIssueTitle">Issue title</label>
                            <span class="description_tooltip">?</span>
                            <div class="tooltip_outer">
                                <div class="tooltip_inner">
                                    Briefly identify the issue
                                </div>
                            </div>
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <input type="text" class="tb_general" placeholder="Title" name="newIssueTitle" id="newIssueTitle">
                        </div>
                        <div class="input_container_error"><s:property value="fieldErrors.get('newIssueTitle').get(0)" /></div>
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_desc">
                            <label for="newIssueCategory">Issue category</label>
                            <span class="description_tooltip">?</span>
                            <div class="tooltip_outer">
                                <div class="tooltip_inner">
                                    Choose the category that best matches the issue you're having
                                </div>
                            </div>
                        </div>
                        <div class="input_container_inner shorter_elements">
                            <select class="tb_general" name="newIssueCategory" id="newIssueCategory">
                                <option value="0" selected disabled hidden>Make a selection</option>
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
                        </div>
                        <div class="input_container_error"><s:property value="fieldErrors.get('newIssueCategory').get(0)" /></div>
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_desc">
                            <label for="newIssueDesc">Issue description</label>
                            <span class="description_tooltip">?</span>
                            <div class="tooltip_outer">
                                <div class="tooltip_inner">
                                    Describe the issue with as much detail as you can.<br>
                                    (max 4096 characters)
                                </div>
                            </div>
                        </div>
                        <div class="input_container_inner">
                            <textarea class="eight_lines tb_general" placeholder="Description of your issue" name="newIssueDesc" id="newIssueDesc" maxlength="4096"></textarea>
                        </div>
                        <div class="input_container_error"><s:property value="fieldErrors.get('newIssueDesc').get(0)" /></div>
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_desc">
                            <label for="newIssueFiles">Supporting files</label>
                            <span class="description_tooltip">?</span>
                            <div class="tooltip_outer">
                                <div class="tooltip_inner">
                                    Upload any relevant files to help us understand your issue.<br>
                                    Examples would be screenshots of any errors, or log files.
                                </div>
                            </div>
                        </div>
                        <div class="input_container_inner">
                            <input type="file" name="issueFiles" id="newIssueFiles" multiple>
                        </div>
                        <div class="input_container_error"><s:property value="fieldErrors.get('issueFiles').get(0)" /></div>
                        <div class="inner_attachmentbar"></div>
                    </div>
                    <div class="input_container_outer">
                        <div class="input_container_inner">
                            <input type="submit" value="Submit your issue" class="no_right_border">
                            <div class="input_container icon unselectable right_side">&#10095;</div>
                        </div>

                    </div>
                </s:form>
            </div>
        </div>
        <script>
            let fileUploader = document.querySelectorAll("input[type='file']")[0];
            let fileContainer = fileUploader.parentElement.nextElementSibling;
            fileUploader.addEventListener("change", function(){
                while (fileContainer.firstChild){
                    fileContainer.removeChild(fileContainer.firstChild);
                }
                let fileList = fileUploader.files;
                for (let i = 0; i < fileList.length; i++){
                    let fileDiv = createFileRepresentation(fileList[i]);
                    fileContainer.appendChild(fileDiv);
                }
            });
            function createFileRepresentation(file){
                let attachmentItem = document.createElement("div");
                attachmentItem.classList.add("inline_attachment_item");

                let attachmentIcon = document.createElement("div");
                attachmentIcon.classList.add("attachment_icon");
                attachmentIcon.innerHTML = "&#128206;";

                let attachmentDetails = document.createElement("div");
                attachmentDetails.classList.add("attachment_details");
                attachmentDetails.innerHTML = file.name + "<br>" + file.size;

                attachmentItem.appendChild(attachmentIcon);
                attachmentItem.appendChild(attachmentDetails);

                //TODO size units for files

                return attachmentItem;
            }
        </script>
    </body>
</html>
