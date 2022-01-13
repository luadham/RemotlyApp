package Model;

public class Job {
    private String jobTitle;
    private String jobRequirements;
    private String jobLocation;
    private String jobDesc;
    private String companyEmail;
    private double salary;

    public Job() {

    }

    public Job(String jobTitle, String jobRequirements, String jobLocation, String jobDesc, String companyEmail, double salary) {
        this.jobTitle = jobTitle;
        this.jobRequirements = jobRequirements;
        this.jobLocation = jobLocation;
        this.jobDesc = jobDesc;
        this.companyEmail = companyEmail;
        this.salary = salary;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Job(String jobTitle, String jobRequirements, String jobLocation, String jobDesc, double salary) {
        this.jobTitle = jobTitle;
        this.jobRequirements = jobRequirements;
        this.jobLocation = jobLocation;
        this.jobDesc = jobDesc;
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
