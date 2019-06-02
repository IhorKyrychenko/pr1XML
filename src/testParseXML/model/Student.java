package testParseXML.model;

import java.util.LinkedList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String groupNumber;
    private List<Subject> subjectList = new LinkedList<>();
    private double averageMark;

    public Student() {
        super();
    }

    public Student(String firstName, String lastName, String groupNumber, List<Subject> subjectList, double averageMark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
        this.subjectList = subjectList;
        this.averageMark = Math.round(averageMark*10.0)/10.0;
    }

    public boolean checkAverageMark() {
        double sum = 0;
        for (Subject sub: subjectList) {
           sum += sub.getMark();
        }
        double realAverage = sum/subjectList.size();
        if(realAverage == averageMark){
            return true;
        }
        else
        {
            averageMark = Math.round(realAverage*10.0)/10.0;
            return false;
        }
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = Math.round(averageMark*10.0)/10.0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", subjectList=" + subjectList +
                ", averageMark=" + averageMark +
                '}';
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }
}
