package org.a3.beans; /**
* @author (Dane Cowburn)
* @version (1.0)
* @date (2022-05-18)
* @course (SENG2050)
* @assignment (3.2)
* @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
* @studentNumber (c3232962)
* @description: model class for existins issue reports that can be viewed and modified/updated.
*/

import java.io.Serializable;

public class IssueReportBean implements Serializable
{
	private String createdBy;
	private String title;
	private String issueDescription;
	private boolean issueStatus;
	private String reportedAt;
	private String resolvedAt;
	private boolean locked;
	private boolean acceptedSolution;
	private String category;

	public IssueReportBean() {}

	//Accessor and Mutator methods for the name of the user who created the report
	public String getCreatedBy ()
	{
		return this.createdBy;
	}
	public void setCreatedBy (String createdBy)
	{
		this.createdBy = createdBy;
	}

	//Accessor and Mutator methods for title of report
	public String getTitle ()
	{
		return this.title;
	}
	public void setTitle (String title)
	{
		this.title = title;
	}

	//Accessor and Mutator methods for description of issue
	public String getIssueDescription ()
	{
		return this.issueDescription;
	}
	public void setIssueDescription (String issueDescription)
	{
		this.issueDescription = issueDescription;
	}

	//Accessor and Mutator methods for issue status of report
	public boolean getIssueStatus ()
	{
		return this.issueStatus;
	}
	public void setIssueStatus (boolean issueStatus)
	{
		this.issueStatus = issueStatus;
	}

	//Accessor and Mutator methods for date and time that report was created
	public String getReportedAt ()
	{
		return this.reportedAt;
	}
	public void setReportedAt (String reportedAt)
	{
		this.reportedAt = reportedAt;
	}

	//Accessor and Mutator methods for date and time that issue was resolved
	public String getResolvedAt ()
	{
		return this.resolvedAt;
	}
	public void setResolvedAt (String resolvedAt)
	{
		this.resolvedAt = resolvedAt;
	}

	//Accessor and Mutator methods for whether issue report is locked to modification
	public boolean getLocked ()
	{
		return this.locked;
	}
	public void setLocked (boolean locked)
	{
		this.locked = locked;
	}

	//Accessor and Mutator methods for whether solution supplied to issue report is accepted by client
	public boolean getAcceptedSolution ()
	{
		return this.acceptedSolution;
	}
	public void setAcceptedSolution (boolean acceptedSolution)
	{
		this.acceptedSolution = acceptedSolution;
	}

	//Accessor and Mutator methods for issue category
	public String getCategory ()
	{
		return this.category;
	}
	public void setCategory (String category)
	{
		this.category = category;
	}

} 
