package org.a3.actions;

import org.a3.beans.UserType;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;
import java.io.File;

public class IssueSubmitAction extends BaseSessionAwareAction{
    private List<File> issueFiles;
    private List<String> issueFilesContentType;
    private List<String> issueFilesFileName;

    @Override
    public String doExecute() {
        for (int i = 0, len = issueFiles.size(); i < len; i++){
            System.out.println(issueFiles.get(i));
            System.out.println(issueFilesContentType.get(i));
            System.out.println(issueFilesFileName.get(i));
        }


        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)) {
            if (sm.getUserType(userSessionObject) == UserType.Staff){
                return ResponseCodes.UNAUTHORIZED;
            }else{
                /* do server-side validation */
                boolean validation = false;
                if (validation){
                    return SUCCESS;
                }else{
                    return ERROR;
                }
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
}
