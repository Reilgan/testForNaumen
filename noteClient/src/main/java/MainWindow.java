import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private JList notesList;
    private JButton addButton;
    private JButton delButton;
    private JPanel panel;
    private JTextField textFieldFilter;

    private final NoteViewer nodeViewer;
    private final NoteListModel noteListModel;
    private final ListSelectionModel noteSelectModel;

    public MainWindow() {
        this.getContentPane().add(panel);

        // Подготовка окна просмотра и редактирования заметок
        nodeViewer = new NoteViewer(this);
        nodeViewer.pack();
        nodeViewer.setSize(new Dimension(400, 400));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { onAdd(); }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onDelete();
            }
        });

        textFieldFilter.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent caretEvent) {
                onFilterNoteList(textFieldFilter.getText());
            }
        });

        noteListModel = new NoteListModel();
        notesList.setModel(noteListModel);
        noteListModel.setNotes(Request.requestNotes());
        noteSelectModel = notesList.getSelectionModel();
        notesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2){
                    Note note = noteListModel.getNote(noteSelectModel.getLeadSelectionIndex());
                    nodeViewer.setNoteData(note);
                    nodeViewer.show(NoteViewerOperatingMode.show);
                }
            }
        });
    }

    private void onFilterNoteList(String substring){
        noteListModel.setFilterSubstring(substring);
    }

    private void onDelete(){
        int i = notesList.getSelectedIndex();
        if (i != -1){
            Request.requestDeleteNote(noteListModel.getNote(i));
            noteListModel.removeNote(i);
        }
    }

    private void onAdd() {
        nodeViewer.show(NoteViewerOperatingMode.create);
    }

    public void onCreateNote(Note note){
        noteListModel.addNote(Request.requestCreateNote(note));
    }
}
