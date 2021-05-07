import javax.swing.*;
import java.util.ArrayList;

public class NoteListModel extends AbstractListModel {

    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> filterBuffData = new ArrayList<>();
    private final ArrayList<String> filterData = new ArrayList<>();
    private final ArrayList<Note> noteData = new ArrayList<>();
    private String filterSubstring = "";

    public void setNotes(ArrayList<Note> notes){
        for (int i = 0; i < notes.size(); i++){
            addNote(notes.get(i));
        }
    }

    public void setFilterSubstring(String substring){
        filterSubstring = substring;
        if (filterSubstring.equals("")){
            fireContentsChanged(this, 0, data.size());
        }else {
            filterData.clear();
            for (String h: data){
                if (h.contains(substring)){
                    filterData.add(h);
                }
            }
            fireContentsChanged(this, 0, filterData.size()-1);
        }
    }

    public void addNote(Note note){
        noteData.add(note);
        data.add(note.getHeading());
        fireIntervalAdded(this, 0, data.size());
    }

    public Note removeNote(int i){
        data.remove(i);
        Note removeNote = noteData.remove(i);
        fireIntervalRemoved(this, 0, data.size());
        return removeNote;
    }

    public Note getNote(int i){
        return noteData.get(i);
    }

    @Override
    public int getSize() {
        if (filterSubstring.equals("")) {
            return data.size();
        }else {
            return filterData.size();
        }
    }

    @Override
    public String getElementAt(int i) {
        if (filterSubstring.equals("")){
            return data.get(i);
        }
        else {
            return filterData.get(i);
        }
    }
}
