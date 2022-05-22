package org.a3.beans; /**
 * @author (Dane Cowburn)
 * @version (1.0)
 * @date (2022-05-20)
 * @course (SENG2050)
 * @assignment (3.2)
 * @group (Dimitar Velovski, Stephen Watson, Dane Cowburn, Lindsey Neilson)
 * @studentNumber (c3232962)
 * @description: model class for statistics on issue reports including
 * Total Unresolved Incidents (TUI), Total Resolved Incidents (TRI) and stress rate.
 */

import java.util.*;
import java.io.Serializable;

public class StatisticsBean implements Serializable
{
    private int networkTUI,
                networkTRI,
                hardwareTUI,
                hardwareTRI,
                softwareTUI,
                softwareTRI,
                emailTUI,
                emailTRI,
                accountTUI,
                accountTRI;
    private double stressRate;

    public StatisticsBean () {}

    /**************************NETWORK**************************/
    //Accessor and mutator methods for Total Unresolved Incidents in 'Network' category
    public int getNetworkTUI() {return this.networkTUI;}
    public void setNetworkTUI(int networkTUI) {this.networkTUI = networkTUI;}

    //Accessor and mutator methods for Total Resolved Incidents in 'Network' category
    public int getNetworkTRI() {return this.networkTRI;}
    public void setNetworkTRI(int networkTRI) {this.networkTRI = networkTRI;}

    /**************************HARDWARE**************************/

    //Accessor and mutator methods for Total Unresolved Incidents in 'Hardware' category
    public int getHardwareTUI() {return this.hardwareTUI;}
    public void setHardwareTUI(int hardwareTUI) {this.hardwareTUI = hardwareTUI;}

    //Accessor and mutator methods for Total Resolved Incidents in 'Hardware' category
    public int getHardwareTRI() {return this.hardwareTRI;}
    public void setHardwareTRI(int hardwareTRI) {this.hardwareTRI = hardwareTRI;}

    /**************************SOFTWARE**************************/

    //Accessor and mutator methods for Total Unresolved Incidents in 'Software' category
    public int getSoftwareTUI() {return this.softwareTUI;}
    public void setSoftwareTUI(int softwareTUI) {this.softwareTUI = softwareTUI;}

    //Accessor and mutator methods for Total Resolved Incidents in 'Software' category
    public int getSoftwareTRI() {return this.softwareTRI;}
    public void setSoftwareTRI(int softwareTRI) {this.softwareTRI = softwareTRI;}

    /**************************EMAIL**************************/

    //Accessor and mutator methods for Total Unresolved Incidents in 'Email' category
    public int getEmailTUI() {return this.emailTUI;}
    public void setEmailTUI(int emailTUI) {this.emailTUI = emailTUI;}

    //Accessor and mutator methods for Total Resolved Incidents in 'Email' category
    public int getEmailTRI() {return this.emailTRI;}
    public void setEmailTRI(int emailTRI) {this.emailTRI = emailTRI;}

    /**************************ACCOUNT**************************/

    //Accessor and mutator methods for Total Unresolved Incidents in 'Account' category
    public int getAccountTUI() {return this.accountTUI;}
    public void setAccountTUI(int accountTUI) {this.accountTUI = accountTUI;}

    //Accessor and mutator methods for Total Resolved Incidents in 'Account; category
    public int getAccountTRI() {return this.accountTRI;}
    public void setAccountTRI(int accountTRI) {this.accountTRI = accountTRI;}

    /**************************STRESS RATE**************************/

    //Accessor and mutator methods for stress rate
    public double getStressRate() {return stressRate;}
    public void setStressRate(double stressRate) {this.stressRate = stressRate;}
}