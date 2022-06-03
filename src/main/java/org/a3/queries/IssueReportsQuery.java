package org.a3.queries; /*
 * @version (1.0)
 * @date (2022-05-20)
 * @course (SENG2050)
 * @assignment (3.2)
 * @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
 * @description: database query class for existing issue reports.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;
import org.a3.beans.UploadedFileBean;
import org.a3.services.FileDownloadData;
import org.a3.services.JDBCUtil;
import org.a3.beans.IssueReportBean;

import static java.sql.Types.NULL;

public class IssueReportsQuery extends BaseAutoCloseableQuery
{
    //Method for grabbing data of all stored reports from database
    public static List<IssueReportBean> getAllIssueReports ()
    {
        String query = "SELECT * FROM IssueReports";
        List<IssueReportBean> reports = new LinkedList<>();
        try (Connection connection = JDBCUtil.get().createConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query))
        {
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                IssueReportBean report = new IssueReportBean();
                report.setCreatedBy(result.getString(1));
                report.setTitle(result.getString(2));
                report.setIssueDescription(result.getString(3));
                //report.setIssueStatus(result.getBoolean(4));
                report.setReportedAt(result.getString(5));
                report.setResolvedAt(result.getString(6));
                report.setLocked(result.getBoolean(7));
                //report.setAcceptedSolution(result.getBoolean(8));
                report.setCategory(result.getString(9));
                reports.add(report);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return reports;
    }

    public int getIssueReportCount(int creatorID, int assignedTo, boolean includeResolved){
        int returnCount = 0;

        String queryToUse = "SELECT COUNT(1) FROM IssueReports ";
        queryToUse += (creatorID != -1 ? "WHERE createdBy = ?" : (assignedTo != -1 ? "WHERE assignedTo = ?" : "")) ;

        if (creatorID == -1 && assignedTo == -1) {
            if (!includeResolved){
                queryToUse += " WHERE issueStatus < 4";
            }
        }else{
            if (!includeResolved){
                queryToUse += " AND issueStatus < 4";
            }
        }

        int uidParameter = 0;
        boolean setUID = true;

        if (creatorID == -1){
            if (assignedTo > -1){
                uidParameter = assignedTo;
            }else{
                setUID = false;
            }
        }else{
            uidParameter = creatorID;
        }

        //System.out.println(queryToUse);

        try (PreparedStatement countQuery = getConnection().prepareStatement(queryToUse)) {
            if (setUID){
                countQuery.setInt(1, uidParameter);
            }

            ResultSet set = countQuery.executeQuery();
            if (set.next()){
                returnCount = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnCount;
    }

    public List<IssueReportBean> getIssueReports(int creatorID, int assignedTo, int offset, int count, boolean showResolved, boolean groupByStatus){
        /*
            the front-end only needs
             - issueID for navigation
             - user's first and last names (concatenated is easier, see getIssueReport())
             - issueStatus as a varchar (refer getIssueReport())
             - issuestatus as an int
             - issueDescription
             
             - if creatorID is -1, it'll just be any and all of them
        */

        List<IssueReportBean> results = new ArrayList<>();
        String queryToUse = "SELECT id, " +
                "createdBy, " +
                "(SELECT TOP 1 CONCAT(Users.firstName, ' ', Users.lastName) FROM Users WHERE Users.id = IR.createdBy)," +
                "title," +
                "issueDescription," +
                "issueStatus," +
                "(SELECT statusName FROM Status WHERE Status.id = IR.issueStatus)," +
                "category, " +
                "(SELECT Category.categoryName FROM Category WHERE category.id IN (\n" +
                "    SELECT subCategoryOf FROM SubCategory WHERE SubCategory.id = IR.category\n" +
                "))," +
                "(SELECT SubCategory.categoryName FROM SubCategory WHERE SubCategory.id = IR.category) " +
                "FROM IssueReports IR " +
                (creatorID != -1 ? "WHERE IR.createdBy = ?" : (assignedTo != -1 ? "WHERE IR.assignedTo = ?" : "")) +
                (!showResolved ? (creatorID == -1 && assignedTo == -1 ? " WHERE " : " AND ") + " issueStatus < 4 " : " ") +
                "ORDER BY " + (groupByStatus ? "issueStatus ASC " : "reportedAt DESC ") +
                "OFFSET " + offset + " ROWS FETCH NEXT " + count + " ROWS ONLY";

        int uidParameter = 0;
        boolean setUID = true;

        if (creatorID == -1){
            if (assignedTo > -1){
                uidParameter = assignedTo;
            }else{
                setUID = false;
            }
        }else{
            uidParameter = creatorID;
        }

        //System.out.println(queryToUse);

        try (PreparedStatement conn = getConnection().prepareStatement(queryToUse)){
            if (setUID){
                conn.setInt(1, uidParameter);
            }

            ResultSet issueResults = conn.executeQuery();
            while (issueResults.next()){
                IssueReportBean irBean = new IssueReportBean();
                irBean.setId(issueResults.getInt(1));
                irBean.setCreatorID(issueResults.getInt(2));
                irBean.setCreatedBy(issueResults.getString(3));
                irBean.setTitle(issueResults.getString(4));
                irBean.setIssueDescription(issueResults.getString(5));
                irBean.setIssueStatus(issueResults.getInt(6));
                irBean.setIssueStatusString(issueResults.getString(7));
                irBean.setCategoryID(issueResults.getInt(8));
                irBean.setCategory(issueResults.getString(9));
                irBean.setSubCategory(issueResults.getString(10));

                results.add(irBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
    public IssueReportBean getIssueReport(int issueID){
        IssueReportBean report = null;

        String fetchQuery = "SELECT id,\n" +
                "       createdBy,\n" +
                "       (SELECT TOP 1 CONCAT(Users.firstName, ' ', Users.lastName) FROM Users WHERE Users.id = IR.createdBy),\n" +
                "       title,\n" +
                "       issueDescription,\n" +
                "       issueStatus,\n" +
                "       (SELECT statusName FROM Status WHERE Status.id = IR.issueStatus),\n" +
                "       reportedAt,\n" +
                "       resolvedAt,\n" +
                "       locked,\n" +
                "       proposedSolution,\n" +
                "       acceptedSolution,\n" +
                "       knowledgeBaseArticleID,\n" +
                "       category,\n" +
                "       (SELECT Category.categoryName FROM Category WHERE category.id IN (\n" +
                "            SELECT subCategoryOf FROM SubCategory WHERE SubCategory.id = IR.category\n" +
                "       )),\n" +
                "       (SELECT SubCategory.categoryName FROM SubCategory WHERE SubCategory.id = IR.category),\n" +
                "       (SELECT TOP 1 CONCAT(Users.firstName, ' ', Users.lastName) FROM Users WHERE Users.id = IR.assignedTo),\n" +
                "       assignedTo\n" +
                "FROM IssueReports IR\n" +
                "WHERE id = ?;";
        try (PreparedStatement fetchStmt = getConnection().prepareStatement(fetchQuery)){
            fetchStmt.setInt(1, issueID);

            ResultSet result = fetchStmt.executeQuery();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            while (result.next()){
                report = new IssueReportBean();
                report.setId(result.getInt(1));
                report.setCreatorID(result.getInt(2));
                report.setCreatedBy(result.getString(3));
                report.setTitle(result.getString(4));
                report.setIssueDescription(result.getString(5));
                report.setIssueStatus(result.getInt(6));
                report.setIssueStatusString(result.getString(7));

                Date reportedAt = result.getTimestamp(8);
                report.setReportedAt(dateFormat.format(reportedAt) + " at " + timeFormat.format(reportedAt));

                Date resolvedAt = result.getTimestamp(9);
                if (resolvedAt != null){
                    report.setResolvedAt(dateFormat.format(resolvedAt) + " at " + timeFormat.format(resolvedAt));
                }else{
                    report.setResolvedAt("");
                }

                report.setLocked(result.getBoolean(10));
                report.setProposedSolution(result.getInt(11));
                report.setAcceptedSolution(result.getInt(12));
                report.setKnowledgeBaseArticleID(result.getInt(13));
                report.setCategoryID(result.getInt(14));
                report.setCategory(result.getString(15));
                report.setSubCategory(result.getString(16));
                report.setAssignedToName(result.getString(17));
                report.setAssignedToID(result.getInt(18));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return report;
    }
    public int createIssueReport(String iTitle, String iDesc, int iCategory, int iAuthor, List<File> files, List<String> fileNames, List<String> fileMimes){
        int issueResultID = -1;

        String creationQuery = "INSERT INTO IssueReports (createdBy, title, issueDescription, category, reportedAt, issueStatus) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, 1)";
        try (PreparedStatement insertStmt = getConnection().prepareStatement(creationQuery, SQLServerStatement.RETURN_GENERATED_KEYS)){
            insertStmt.setInt(1, iAuthor);
            insertStmt.setString(2, iTitle);
            insertStmt.setString(3, iDesc);
            insertStmt.setInt(4, iCategory);

            insertStmt.executeUpdate();
            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()){
                issueResultID = (int) generatedKeys.getLong(1);
            }
            //System.out.println("new issue ID: " + issueResultID);

            insertStmt.close();

            //insert the files if they are PRESENT lol
            if (files != null && !files.isEmpty()){
                String fileUploadQuery = "INSERT INTO UploadedFiles (issueID, uploadedBy, mime, fileName, fileData, fileSize) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ulStmt = getConnection().prepareStatement(fileUploadQuery);

                for (int f = 0, fSize = files.size(); f < fSize; f++){
                    ulStmt.setInt(1, issueResultID);
                    ulStmt.setInt(2, iAuthor);
                    ulStmt.setString(3, fileMimes.get(f));
                    ulStmt.setString(4, fileNames.get(f));

                    Path p = files.get(f).toPath();
                    try {
                        long size = Files.size(p);
                        ulStmt.setBinaryStream(5, Files.newInputStream(p));
                        ulStmt.setLong(6, size);
                        //separate try-catch because i don't want to halt the process if one file fails.
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //System.out.println("Uploading: " + fileNames.get(f));
                    ulStmt.execute();
                }

                ulStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return issueResultID;
    }

    public boolean setIssueStatus(int issueID, int newStatus){
        boolean iResult = false;

        String updateQuery = "UPDATE IssueReports SET issueStatus = ? WHERE id = ?";
        try (PreparedStatement updateStatement = getConnection().prepareStatement(updateQuery)){
            updateStatement.setInt(1, newStatus);
            updateStatement.setInt(2, issueID);

            if (updateStatement.execute()){
                iResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iResult;
    }
    public boolean setIssueAssignedStaffMember(int issueID, int staffMember){
        boolean iResult = false;

        String updateQuery = "UPDATE IssueReports SET assignedTo = ? WHERE id = ?";
        try (PreparedStatement updateStatement = getConnection().prepareStatement(updateQuery)){
            updateStatement.setInt(1, staffMember);
            updateStatement.setInt(2, issueID);

            if (updateStatement.execute()){
                iResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iResult;
    }

    public boolean clearKnowledgeBaseArticleID(int issueID){
        boolean iResult = false;
        String updateQuery = "UPDATE IssueReports SET knowledgeBaseArticleID = NULL WHERE id = ?";
        try (PreparedStatement removeStatement = getConnection().prepareStatement(updateQuery)){
            removeStatement.setInt(1, issueID);
            if (removeStatement.execute()){
                iResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return iResult;
    }

    public boolean setIssueProposedSolution(int issueID, int commentID){
        boolean iResult = false;

        String updateQuery = "UPDATE IssueReports SET proposedSolution = ?, resolvedAt = CURRENT_TIMESTAMP WHERE id = ?";
        try (PreparedStatement updateStatement = getConnection().prepareStatement(updateQuery)){
            if (commentID == -1){
                updateStatement.setNull(1, NULL);
            }else{
                updateStatement.setInt(1, commentID);
            }
            updateStatement.setInt(2, issueID);

            if (updateStatement.execute()){
                iResult = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iResult;
    }

    public boolean setIssueAcceptedSolution(int issueID, int commentID){
        boolean iResult = false;

        String updateQuery = "UPDATE IssueReports SET acceptedSolution = ?, proposedSolution = NULL, locked = 1, resolvedAt = CURRENT_TIMESTAMP WHERE id = ?";
        try (PreparedStatement updateStatement = getConnection().prepareStatement(updateQuery)){
            updateStatement.setInt(1, commentID);
            updateStatement.setInt(2, issueID);

            if (updateStatement.execute()){
                iResult = true;
            }

            System.out.println("issueID " + issueID + " with accepted solution " + commentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return iResult;
    }

    public FileDownloadData getFileStream(int fileID){
        FileDownloadData result = null;

        try (Connection conn = JDBCUtil.get().createConnection()){
            String retrievalQuery = "SELECT fileData,fileSize,fileName FROM UploadedFiles WHERE id = ?";
            PreparedStatement fileStmt = conn.prepareStatement(retrievalQuery);
            fileStmt.setInt(1, fileID);

            ResultSet returnedFiles = fileStmt.executeQuery();
            if (returnedFiles.next()) {
                result = new FileDownloadData();
                result.fileInputStream = new ByteArrayInputStream(returnedFiles.getBinaryStream(1).readAllBytes());
                result.fileSize = returnedFiles.getLong(2);
                result.fileName = returnedFiles.getString(3);
            }

            fileStmt.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<UploadedFileBean> getFilesForReport(int id){
        List<UploadedFileBean> results = new ArrayList<>();
        try (Connection conn = JDBCUtil.get().createConnection()){
            String creationQuery = "SELECT id,fileName,fileSize FROM UploadedFiles WHERE issueID = ?";
            PreparedStatement fileStmt = conn.prepareStatement(creationQuery);
            fileStmt.setInt(1, id);

            ResultSet returnedFiles = fileStmt.executeQuery();
            while (returnedFiles.next()) {
                UploadedFileBean fileBean = new UploadedFileBean();
                fileBean.setFileID(returnedFiles.getInt(1));
                fileBean.setFileName(returnedFiles.getString(2));
                fileBean.setFileSize(returnedFiles.getLong(3));

                String sizeString = String.format("%,d", fileBean.getFileSize()) + " B";
                fileBean.setFileSizeString(sizeString);
                results.add(fileBean);
            }

            fileStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


}