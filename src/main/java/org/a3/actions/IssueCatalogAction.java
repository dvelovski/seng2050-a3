package org.a3.actions;

import org.a3.beans.IssueReportBean;
import org.a3.beans.UserBean;
import org.a3.beans.UserType;
import org.a3.queries.IssueReportsQuery;
import org.a3.services.SessionManager;
import org.a3.services.constants.ResponseCodes;

import java.util.List;

public class IssueCatalogAction extends BaseSessionAwareAction{
    private int pageNumber = 0;
    private int resultsPerPage = 10;
    private int resultsOnPage;
    private int resultStart;
    private int resultCount;

    private int nextPage;
    private int prevPage;

    private String action = "";
    private boolean showResolved = false;
    private boolean assignedToMe = false;

    private List<IssueReportBean> issueReports;
    private String loadError = "";

    private boolean catGroupByStatus = false;

    @Override
    public String doExecute() {
        SessionManager sm = SessionManager.get();

        if (sm.isLoggedIn(userSessionObject)){
            UserBean user = sm.getUserBean(userSessionObject);
            if (user.getUserType() == UserType.Staff){
                try (IssueReportsQuery iQuery = new IssueReportsQuery()){
                    resultCount = iQuery.getIssueReportCount(-1, (assignedToMe ? user.getUserIdentification() : -1), showResolved);
                    int maxPages = Math.floorDiv(resultCount, resultsPerPage);

                    switch (action){
                        case "p.first":
                            pageNumber = 0;
                            break;
                        case "p.prev":
                            pageNumber -= 1;
                            break;
                        case "p.next":
                            pageNumber += 1;
                            break;
                        case "p.last":
                            pageNumber = maxPages;
                            break;
                    }

                    pageNumber = Math.min(Math.max(0, pageNumber), maxPages);

                    prevPage = Math.max(pageNumber - 1, 0);
                    nextPage = Math.min(pageNumber + 1, maxPages);

                    //System.out.println("group by status? " + catGroupByStatus + " + show resolved? " + showResolved);

                    issueReports = iQuery.getIssueReports(
                            -1,
                            (assignedToMe ? user.getUserIdentification() : -1),
                            resultsPerPage * pageNumber,
                            resultsPerPage,
                            showResolved,
                            catGroupByStatus
                    );

                    resultsOnPage = Math.min(issueReports.size(), resultsPerPage) + (pageNumber * resultsPerPage);
                    resultStart = (issueReports.isEmpty() ? 0 : (resultsPerPage * pageNumber) + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    loadError = "";
                }

                return SUCCESS;
            }
            return ResponseCodes.FORBIDDEN;
        }

        return ResponseCodes.UNAUTHORIZED;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getResultsOnPage() {
        return resultsOnPage;
    }

    public int getResultStart() {
        return resultStart;
    }

    public int getResultCount() {
        return resultCount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean getShowResolved() {
        return showResolved;
    }

    public void setShowResolved(boolean showResolved) {
        this.showResolved = showResolved;
    }

    public boolean getAssignedToMe() {
        return assignedToMe;
    }

    public List<IssueReportBean> getIssueReports() {
        return issueReports;
    }

    public boolean getCatGroupByStatus(){
        return catGroupByStatus;
    }

    public void setCatGroupByStatus(boolean catGroupByStatus) {
        this.catGroupByStatus = catGroupByStatus;
    }
}
