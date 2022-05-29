package org.a3.actions;

import com.opensymphony.xwork2.interceptor.Interceptor;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;
import java.io.File;

public class IssueSubmitAction extends BaseSessionAwareAction{
    private List<File> issueFiles;
    private List<String> issueFilesContentType;
    private List<String> issueFilesFileName;

    private String newIssueTitle;
    private String newIssueDesc;
    private String newIssueCategory;

    private int resultingIssueID = 0;

    @Override
    public String doExecute() {
        if (issueFiles != null){
            for (int i = 0, len = issueFiles.size(); i < len; i++){
                System.out.println(issueFiles.get(i));
                System.out.println(issueFilesContentType.get(i));
                System.out.println(issueFilesFileName.get(i));
            }
        }

        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            UserBean user = sm.getUserBean(userSessionObject);
            if (user.getUserType() == UserType.Staff){
                return ResponseCodes.UNAUTHORIZED;
            }else{
                /* do server-side validation */
                boolean validation = true;
                String innerResponseCode = ERROR;

                newIssueTitle = newIssueTitle.trim();
                newIssueDesc = newIssueDesc.trim();
                int catIdx = Integer.parseInt(newIssueCategory);

                if (newIssueTitle.isEmpty()){
                    addFieldError("newIssueTitle", "Issue title may not be blank");
                    validation = false;
                }
                if (catIdx <= 0 || catIdx >= 14){
                    addFieldError("newIssueCategory", "Invalid category selection");
                    validation = false;
                }
                if (newIssueDesc.isEmpty()){
                    addFieldError("newIssueDesc", "Description may not be blank");
                    validation = false;
                }else if (newIssueDesc.length() > 4096){
                    addFieldError("newIssueDesc", "Description exceeds maximum allowed length of 4096 characters.");
                    validation = false;
                }

                if (validation){
                    resultingIssueID = new IssueReportsQuery().createIssueReport(newIssueTitle, newIssueDesc, catIdx, user.getUserIdentification(), issueFiles, issueFilesFileName, issueFilesContentType);
                    innerResponseCode = SUCCESS;
                }

                return innerResponseCode;
            }
        }
        return ResponseCodes.FORBIDDEN;
    }

    public void setIssueFiles(List<File> issueFiles) {
        this.issueFiles = issueFiles;
    }

    public void setIssueFilesContentType(List<String> issueFilesContentType) {
        this.issueFilesContentType = issueFilesContentType;
    }

    public void setIssueFilesFileName(List<String> issueFilesFileName) {
        this.issueFilesFileName = issueFilesFileName;
    }

    public void setNewIssueCategory(String newIssueCategory) {
        this.newIssueCategory = newIssueCategory;
    }

    public void setNewIssueTitle(String newIssueTitle) {
        this.newIssueTitle = newIssueTitle;
    }

    public void setNewIssueDesc(String newIssueDesc) {
        this.newIssueDesc = newIssueDesc;
    }

    public int getResultingIssueID() {
        return resultingIssueID;
    }
}
