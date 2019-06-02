package testParseXML.model;

import java.util.LinkedList;
import java.util.List;

public class Group {
    private List<Student> studentList = new LinkedList<>();

    public Group() {
        super();
    }
    public void addStudentToGroup(Student student) {
        studentList.add(student);
    }


    public void changeAllAverageMark() {
        System.out.println("Checking student marks:");
        for (Student student: studentList) {
            System.out.println("Student name: " + student.getFirstName() + " " + student.getLastName() + ", from group " + student.getGroupNumber());
            boolean check = student.checkAverageMark();
            if(check) {
                System.out.println("Haven't mistakes in average mark");
            }
            else {
                System.out.println("Had mistakes in average mark, it was changed to " + student.getAverageMark());
            }
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
