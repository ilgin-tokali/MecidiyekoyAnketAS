package com.example.MecidiyekoyAnketAS;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "happiness_survey")
public class HappinessSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String gender; // 'male', 'female', 'other'
    private Date birthday;

    @Column(name = "hapiness")
    private Integer hapiness;

    @Column(name = "reasonforhapiness")
    private String reasonForHapiness;

    @Column(name = "reasonforunhapiness")
    private String reasonForUnhapiness;

    private String description;

    @Column(name = "interviewer_name")
    private String interviewerName;

    @Column(name = "interviewer_surname")
    private String interviewerSurname;

    @Column(name = "submitted_at")
    private Timestamp submittedAt;

    public HappinessSurvey() {}

    public HappinessSurvey(Integer id, String name, String surname, String gender, Date birthday, 
        Integer hapiness, String reasonForHapiness, String reasonForUnhapiness, String description, 
        String interviewerName, String interviewerSurname, Timestamp submittedAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.hapiness = hapiness;
        this.reasonForHapiness = reasonForHapiness;
        this.reasonForUnhapiness = reasonForUnhapiness;
        this.description = description;
        this.interviewerName = interviewerName;
        this.interviewerSurname = interviewerSurname;
        this.submittedAt = submittedAt;
    }

    /* Getter and Setter Methods */

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public Integer getHapiness() { return hapiness; }
    public void setHapiness(Integer hapiness) { this.hapiness = hapiness; }
    public String getReasonForHapiness() { return reasonForHapiness; }
    public void setReasonForHapiness(String reasonForHapiness) { this.reasonForHapiness = reasonForHapiness; }
    public String getReasonForUnhapiness() { return reasonForUnhapiness; }
    public void setReasonForUnhapiness(String reasonForUnhapiness) { this.reasonForUnhapiness = reasonForUnhapiness; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getInterviewerName() { return interviewerName; }
    public void setInterviewerName(String interviewerName) { this.interviewerName = interviewerName; }
    public String getInterviewerSurname() { return interviewerSurname; }
    public void setInterviewerSurname(String interviewerSurname) { this.interviewerSurname = interviewerSurname; }
    public Timestamp getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Timestamp submittedAt) { this.submittedAt = submittedAt; }
}
