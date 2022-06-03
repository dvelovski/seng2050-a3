package org.a3.queries;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerStatement;
import org.a3.beans.KnowledgeBaseBean;

public class KnowledgeBaseQuery extends BaseAutoCloseableQuery{

    //Method for retrieving all info of single KB article on kbview.jsp
    public KnowledgeBaseBean getKBArticle (int articleID) {
        String query = "SELECT id, " +
                "initialIssue, " +
                "articleName, " +
                "articleText, " +
                "solutionText, " +
                "resolutionTime, " +
                "reportedTime, " +
                "(SELECT Category.categoryName FROM Category WHERE Category.id IN (SELECT subCategoryOf FROM SubCategory WHERE SubCategory.id = KB.category)), " +
                "(SELECT SubCategory.categoryName FROM SubCategory WHERE SubCategory.id = KB.category), " +
                "category FROM KnowledgeBase KB " +
                "WHERE id = ?";
        KnowledgeBaseBean article = null;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        try (PreparedStatement articleFetchStatement = getConnection().prepareStatement(query)) {
            articleFetchStatement.setInt(1, articleID);

            ResultSet result = articleFetchStatement.executeQuery();
            if (result.next()){
                article = new KnowledgeBaseBean();

                article.setArticleId(result.getInt(1));
                article.setInitialIssue(result.getInt(2));
                article.setArticleName(result.getString(3));
                article.setArticleText(result.getString(4));
                article.setSolutionText(result.getString(5));
                Date resolutionTime = result.getTimestamp(6);
                if (resolutionTime != null){
                    article.setResolutionTime(dateFormat.format(resolutionTime) + " at " + timeFormat.format(resolutionTime));
                }
                Date reportedTime = result.getTimestamp(7);
                if (reportedTime != null){
                    article.setReportedTime(dateFormat.format(reportedTime) + " at " + timeFormat.format(reportedTime));
                }
                article.setCategoryName(result.getString(8));
                article.setSubCategoryName(result.getString(9));
                article.setCategory(result.getInt(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    //Method for displaying articles on kbindex.jsp
    public List<KnowledgeBaseBean> getKBArticleIndex (int categoryFilter) {

        List<KnowledgeBaseBean> articleIndex = new ArrayList<>();
        String query = "SELECT id, " +
                "initialIssue, " +
                "articleName, " +
                "category, " +
                "articleText, " +
                "(SELECT Category.categoryName FROM Category WHERE Category.id IN (SELECT subCategoryOf FROM SubCategory WHERE SubCategory.id = KB.category)), " +
                "(SELECT SubCategory.categoryName FROM SubCategory WHERE SubCategory.id = KB.category) " +
                "FROM KnowledgeBase KB " + (categoryFilter > 0 ? "WHERE category = ? " : "") +
                "ORDER BY resolutionTime DESC ";
        try (PreparedStatement articleFetchStatement = getConnection().prepareStatement(query)) {
            if (categoryFilter > 0){
                articleFetchStatement.setInt(1, categoryFilter);
            }
            ResultSet result = articleFetchStatement.executeQuery();
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                KnowledgeBaseBean article = new KnowledgeBaseBean();
                article.setArticleId(result.getInt(1));
                article.setInitialIssue(result.getInt(2));
                article.setArticleName(result.getString(3));
                article.setCategory(result.getInt(4));
                article.setArticleText(result.getString(5));
                article.setCategoryName(result.getString(6));
                article.setSubCategoryName(result.getString(7));
                articleIndex.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleIndex;
    }

    public List<KnowledgeBaseBean> searchKB(int categoryFilter, String searchTerms){
        List<KnowledgeBaseBean> searchResults = new ArrayList<>();

        String query = "SELECT id, " +
                "initialIssue, " +
                "articleName, " +
                "category, " +
                "articleText, " +
                "(SELECT Category.categoryName FROM Category WHERE Category.id IN (SELECT subCategoryOf FROM SubCategory WHERE SubCategory.id = KB.category)), " +
                "(SELECT SubCategory.categoryName FROM SubCategory WHERE SubCategory.id = KB.category) " +
                "FROM KnowledgeBase KB " + (categoryFilter > 0 ? "WHERE category = ? AND " : "WHERE ") +
                "FREETEXT ((articleName, articleText, solutionText), ?) " +
                "ORDER BY resolutionTime DESC ";
        try (PreparedStatement articleFetchStatement = getConnection().prepareStatement(query)) {
            int textIndex = 1;
            if (categoryFilter > 0){
                articleFetchStatement.setInt(1, categoryFilter);
                textIndex = 2;
            }
            articleFetchStatement.setString(textIndex, searchTerms);

            ResultSet result = articleFetchStatement.executeQuery();
            while (result.next()) //Iterate over and collect all data from each tuple in database
            {
                KnowledgeBaseBean article = new KnowledgeBaseBean();
                article.setArticleId(result.getInt(1));
                article.setInitialIssue(result.getInt(2));
                article.setArticleName(result.getString(3));
                article.setCategory(result.getInt(4));
                article.setArticleText(result.getString(5));
                article.setCategoryName(result.getString(6));
                article.setSubCategoryName(result.getString(7));
                searchResults.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    //Method to delete KB article tuple from DB
    public boolean deleteKBArticle (int id) {
        String query = "DELETE FROM KnowledgeBase WHERE id = " + id;

        try (PreparedStatement articleDeleteStatement = getConnection().prepareStatement(query))
        {
            articleDeleteStatement.execute();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public int createKBArticle(int initialIssue, String articleName, String articleText, String solutionText){
        //grab the resolution time and reported time from the thing itself since the Bean stores it as a String not a date
        int resultID = -1;

        String creationQuery = "INSERT INTO KnowledgeBase (initialIssue, articleName, articleText, solutionText, resolutionTime, reportedTime, category) " +
                "VALUES (?, ?, ?, ?, " +
                "(SELECT IR.resolvedAt FROM IssueReports IR WHERE IR.id = ?), " +
                "(SELECT IR.reportedAt FROM IssueReports IR WHERE IR.id = ?),  " +
                "(SELECT IR.category FROM IssueReports IR WHERE IR.id = ?))";
        try (PreparedStatement creationStmt = getConnection().prepareStatement(creationQuery, SQLServerStatement.RETURN_GENERATED_KEYS)){
            creationStmt.setInt(1, initialIssue);
            creationStmt.setString(2, articleName);
            creationStmt.setString(3, articleText);
            creationStmt.setString(4, solutionText);
            creationStmt.setInt(5, initialIssue);
            creationStmt.setInt(6, initialIssue);
            creationStmt.setInt(7, initialIssue);

            creationStmt.executeUpdate();
            ResultSet generatedKeys = creationStmt.getGeneratedKeys();
            if (generatedKeys.next()){
                resultID = (int) generatedKeys.getLong(1);
            }

            System.out.println(resultID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (resultID != -1){
            String updateIssueQuery = "UPDATE IssueReports SET knowledgeBaseArticleID = ? WHERE id = ?";
            try (PreparedStatement issueUpdateStmt = getConnection().prepareStatement(updateIssueQuery)){
                issueUpdateStmt.setInt(1, resultID);
                issueUpdateStmt.setInt(2, initialIssue);
                issueUpdateStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultID;
    }
}
