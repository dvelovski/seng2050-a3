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
import java.util.*;

import org.a3.beans.CommentsBean;
import org.a3.services.JDBCUtil;

public class CommentsQuery {
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

    public void addCommentToReport(int reportID, String commentText, boolean markResolved){
        /* todo stub */
    }
}

