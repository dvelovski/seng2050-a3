package org.a3.beans;

import java.io.Serializable;

public class KnowledgeBaseBean implements Serializable {
	private int articleId;
	private String articleName;
	private String articleText;
	private String initialIssue;
	private String solutionText;
	private String resolutionTime;
	private String reportedTime;
	private int category;

	public KnowledgeBaseBean() {
		this.articleId = 0;
		this.articleName = null;
		this.articleText = null;
		this.initialIssue = null;
		this.solutionText = null;
		this.resolutionTime = null;
		this.reportedTime = null;
		this.category = 0;
    }
	
	public void setArticleId (int newArticleId) {
		this.articleId = newArticleId;
	}

	public void setArticleName (String newArticleName) {
		this.articleName = newArticleName;
	}

	public void setArticleText (String newArticleText) {
		this.articleText = newArticleText;
	}

	public void setInitialIssue (String newInitialIssue) {
		this.initialIssue = newInitialIssue;
	}

	public void setSolutionText (String newSolutionText) {
		this.solutionText = newSolutionText;
	}

	public void setResolutionTime (String newResolutionTime) {
		this.resolutionTime = newResolutionTime;
	}
	public void setReportedTime (String newReportedTime) { this.reportedTime = newReportedTime; }
	public void setCategory (int newCategory) { this.category = newCategory; }

	public int getArticleId () {
		return this.articleId;
	}

	public String getArticleName () {
		return this.articleName;
	}

	public String getArticleText () {
		return this.articleText;
	}

	public String getInitialissue () {
		return this.initialIssue;
	}

	public String getSolutionText () {
		return this.solutionText;
	}

	public String getResolutionTime () {
		return this.resolutionTime;
	}
	public String getReportedTime () { return  this.reportedTime; }
	public int getCategory () { return  this.category; }
}