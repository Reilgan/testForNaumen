import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NoteViewer extends JDialog{
    private JPanel panel;
    private JLabel lHeader;
    private JLabel lNote;
    private JTextPane textHeader;
    private JTextPane textNote;
    private JButton applyButton;
    private JButton cancelButton;

    public NoteViewer(MainWindow parent) {
        super(parent);
        this.getContentPane().add(panel);
        this.setModal(true);

        // Создание слушателя для новых заметок
        applyButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Note note = new Note(textHeader.getText(),
                                     textNote.getText());

                parent.onCreateNote(note);
               onCancel();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onCancel();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    public void setNoteData(Note note){
        textHeader.setText(note.getHeading());
        textNote.setText(note.getNote());
    }

    public void show(NoteViewerOperatingMode mode){
        switch (mode){
            case create:
                applyButton.setVisible(true);
                cancelButton.setVisible(true);
                break;
            case show:
                applyButton.setVisible(false);
                cancelButton.setVisible(false);
                break;
        }
        setVisible(true);
    }

    private void onCancel(){
        textHeader.setText("");
        textNote.setText("");
        applyButton.setVisible(false);
        cancelButton.setVisible(false);
        setVisible(false);
    }
}
