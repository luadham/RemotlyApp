package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String name;
    private String job;
    private String desc;
    private String email;
    private String pwd;
    private ArrayList<String> skills = new ArrayList<>();
    private ArrayList<Job> favJobs = new ArrayList<>();
    private String uid;

    public User() {

    }

    public User(String name, String job, String desc, String email, String pwd, ArrayList<Job> favouriteJobs) {
        this.name = name;
        this.job = job;
        this.desc = desc;
        this.email = email;
        this.pwd = pwd;
        this.favJobs = favouriteJobs;
    }

    public User(String name, String email, String pwd) {
        setName(name);
        setEmail(email);
        setPwd(pwd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<Job> getFavJobs() {
        return favJobs;
    }

    public void setFavJobs(ArrayList<Job> favJobs) {
        this.favJobs = favJobs;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
