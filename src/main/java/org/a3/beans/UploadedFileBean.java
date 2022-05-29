package org.a3.beans;

public class UploadedFileBean {
    private int fileID;
    private String fileName;
    private long fileSize;
    private String fileSizeString;

    public UploadedFileBean(){

    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSizeString() {
        return fileSizeString;
    }

    public void setFileSizeString(String fileSizeString) {
        this.fileSizeString = fileSizeString;
    }
}
