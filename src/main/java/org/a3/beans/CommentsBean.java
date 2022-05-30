package org.a3.beans; /**
 * @author (Dane Cowburn)
 * @version (1.0)
 * @date (2022-05-18)
 * @course (SENG2050)
 * @assignment (3.2)
 * @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
 * @studentNumber (c3232962)
 * @description: model class for comments that are made in issue reports.
 */

import java.io.Serializable;
import java.util.*;

public class CommentsBean implements Serializable
{
    private int commentID;
    private int postedBy;
    private String postedByName;
    private int issueID;
    private String content;
    private String postedAt;

    public CommentsBean () {}

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    //Accessor and mutator methods for the user ID of who posted comment
    public int getPostedBy () {return this.postedBy;}
    public void setPostedBy (int postedBy) {this.postedBy = postedBy;}

    public String getPostedByName() {
        return postedByName;
    }

    public void setPostedByName(String postedByName) {
        this.postedByName = postedByName;
    }

    //Accessor and mutator methods for the issue report ID where the comment has been posted
    public int getIssueID () {return this.issueID;}
    public void setIssueID (int issueID) {this.issueID = issueID;}

    //Accessor and mutator methods for the content of the comment posted
    public String getContent () {return this.content;}
    public void setContent (String content) {this.content = content;}

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }
}