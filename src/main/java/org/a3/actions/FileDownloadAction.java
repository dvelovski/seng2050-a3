package org.a3.actions;

import org.a3.queries.IssueReportsQuery;
import org.a3.services.FileDownloadData;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.io.InputStream;

public class FileDownloadAction extends BaseSessionAwareAction{
    private int requestedFileID;
    private InputStream fileInputStream;
    private String dFileName;
    private long dFileSize;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();
        if (sm.isLoggedIn(userSessionObject)){
            FileDownloadData dlData = new IssueReportsQuery().getFileStream(requestedFileID);
            if (dlData == null){
                return ResponseCodes.NOTFOUND;
            }
            if (dlData.fileInputStream == null){
                return ERROR;
            }

            fileInputStream = dlData.fileInputStream;
            dFileName = dlData.fileName;
            dFileSize = dlData.fileSize;

            return SUCCESS;
        }
        return ResponseCodes.UNAUTHORIZED;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String getdFileName() {
        return dFileName;
    }

    public void setdFileName(String dFileName) {
        this.dFileName = dFileName;
    }

    public int getRequestedFileID() {
        return requestedFileID;
    }

    public void setRequestedFileID(int requestedFileID) {
        this.requestedFileID = requestedFileID;
    }

    public long getdFileSize() {
        return dFileSize;
    }

    public void setdFileSize(long dFileSize) {
        this.dFileSize = dFileSize;
    }
}
