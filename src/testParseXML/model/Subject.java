package testParseXML.model;

public class Subject {
    private String title;
    private int mark;

    public Subject() {
        super();
    }

    public Subject(String title, int mark) {
        this.title = title;
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
