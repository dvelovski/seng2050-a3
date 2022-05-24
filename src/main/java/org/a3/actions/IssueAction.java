package org.a3.actions;

public class IssueAction extends BaseSessionAwareAction{
    @Override
    public String doExecute() {
        //if 'action' is blank, we are viewing.
            //if user is staff, or creator, we can view
        //if 'action' is comment, we are adding a comment.
            //if user is creator, or staff, we can do this.
        //if 'action' is comment with param 'mark solution' - have to be staff
        //if 'action' is 

        return SUCCESS;
    }
}
