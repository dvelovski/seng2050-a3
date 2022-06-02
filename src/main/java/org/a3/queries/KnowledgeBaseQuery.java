package org.a3.queries;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

import org.a3.beans.KnowledgeBaseBean;

public class KnowledgeBaseQuery extends BaseAutoCloseableQuery{

    //Method for retrieving all info of single KB article on kbview.jsp
    public KnowledgeBaseBean getKBArticle () {
        String query = "SELECT * FROM KnowledgeBase";
        KnowledgeBaseBean article = new KnowledgeBaseBean();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        try (PreparedStatement articleFetchStatement = getConnection().prepareStatement(query)) {

            ResultSet result = articleFetchStatement.executeQuery(query);

            article.setArticleId(result.getInt(1));
            article.setInitialIssue(result.getString(2));
            article.setArticleName(result.getString(3));
            article.setArticleText(result.getString(4));
            article.setSolutionText(result.getString(5));
            Date resolutionTime = result.getTimestamp(6);
            article.setReportedTime(dateFormat.format(resolutionTime) + " at " + timeFormat.format(resolutionTime));
            Date reportedTime = result.getTimestamp(7);
            article.setReportedTime(dateFormat.format(reportedTime) + " at " + timeFormat.format(reportedTime));
            article.setCategory(result.getInt(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    //Method for displaying articles on kbindex.jsp
    public List<KnowledgeBaseBean> getKBArticleIndex (int offset, int count) {
        List<KnowledgeBaseBean> articleIndex = new ArrayList<>();
        String query = "SELECT id, initialIssue, articleName, category" +
                "FROM KnowledgeBase" +
                "ORDER BY resolutionTime DESC ";
        try (PreparedStatement articleFetchStatement = getConnection().prepareStatement(query)) {
            ResultSet result = articleFetchStatement.executeQuery(query);
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                KnowledgeBaseBean article = new KnowledgeBaseBean();
                article.setArticleId(result.getInt(1));
                article.setInitialIssue(result.getString(2));
                article.setArticleName(result.getString(3));
                article.setCategory(result.getInt(8));
                articleIndex.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleIndex;
    }

    //Method to delete KB article tuple from DB
    public void deleteKBArticle (int id) {
        String query = "DELETE FROM KnowledgeBase WHERE id = " + id;

        try (PreparedStatement articleDeleteStatement = getConnection().prepareStatement(query))
        {
            articleDeleteStatement.executeQuery(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
