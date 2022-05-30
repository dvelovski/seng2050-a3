package org.a3.queries;

/*
 * @author (Dane Cowburn)
 * @version (1.0)
 * @date (2022 - 05 - 20)
 * @course (SENG2050)
 * @assignment (3.2)
 * @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
 * @studentNumber (c3232962)
 * @description: database query helper class for comments
 */

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;
import org.a3.beans.CommentsBean;
import org.a3.beans.IssueReportBean;
import org.a3.services.JDBCUtil;

public class CommentsQuery extends BaseAutoCloseableQuery {

    public CommentsQuery() {
    }

    //Method for retrieving all stored comments from database
    public static List<CommentsBean> getComments() {
        String query = "SELECT * FROM Comments";
        List<CommentsBean> comments = new LinkedList<>();
        try (Connection connection = JDBCUtil.get().createConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) //Iterate over and collect all data from each tuple in table
            {
                CommentsBean comment = new CommentsBean();
                comment.setPostedBy(result.getInt(1));
                comment.setIssueID(result.getInt(2));
                comment.setContent(result.getString(3));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return comments;
    }

    public int createComment(int userID, int issueID, String commentText) throws SQLException {
        int returnID = -1;

        String commentInsertion = "INSERT INTO Comments (postedBy, issueID, content, postedAt) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        PreparedStatement commentInsertionStatement = getConnection().prepareStatement(commentInsertion, SQLServerStatement.RETURN_GENERATED_KEYS);
        commentInsertionStatement.setInt(1, userID);
        commentInsertionStatement.setInt(2, issueID);
        commentInsertionStatement.setString(3, commentText);

        System.out.println("user " + userID + " is posting on " + issueID + " with text " + commentText);

        commentInsertionStatement.executeUpdate();
        ResultSet generatedID = commentInsertionStatement.getGeneratedKeys();
        if (generatedID.next()){
            returnID = (int) generatedID.getLong(1);
        }
        commentInsertionStatement.close();
        return returnID;
    }

    public List<CommentsBean> getReportComments(int reportID) throws SQLException {
        List<CommentsBean> reportComments = new ArrayList<>();

        String commentQuery = "SELECT id, " +
                "        postedBy, " +
                "        (SELECT TOP 1 CONCAT(Users.firstName, ' ', Users.lastName) FROM Users WHERE Users.id = CM.postedBy), " +
                "        content, " +
                "        postedAt " +
                "        FROM Comments CM" +
                "        WHERE issueID = ?";


        try (PreparedStatement commentFetchStatement = getConnection().prepareStatement(commentQuery)) {
            commentFetchStatement.setInt(1, reportID);

            ResultSet commentSet = commentFetchStatement.executeQuery();

            while (commentSet.next()){
                CommentsBean cmBean = new CommentsBean();
                cmBean.setCommentID(commentSet.getInt(1));
                cmBean.setPostedBy(commentSet.getInt(2));
                cmBean.setPostedByName(commentSet.getString(3));
                cmBean.setContent(commentSet.getString(4));

                Date date = commentSet.getTimestamp(5);
                if (date != null){
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    cmBean.setPostedAt(dateFormat.format(date) + ", " + timeFormat.format(date));
                }else{
                    cmBean.setPostedAt(null);
                }

                cmBean.setIssueID(reportID);

                reportComments.add(cmBean);
            }
        }

        return reportComments;
    }

    public IssueReportBean getComment(int commentID){
        /* todo stub */
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}

