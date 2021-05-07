import java.io.Serializable;

public class Note {
    private Integer id;
    private String heading;
    private String note;

    public Note(String header, String note){
        this.heading = header;
        this.note = note;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setHeading(String header) {
        this.heading = header;
    }

    public String getNote() {
        return note;
    }

    public String getHeading() {
        return heading;
    }
}
