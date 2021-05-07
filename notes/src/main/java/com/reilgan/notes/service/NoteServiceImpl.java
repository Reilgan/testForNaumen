package com.reilgan.notes.service;

import com.reilgan.notes.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NoteServiceImpl implements NoteService{
    // Хранилище Заметок
    private static final Map<Integer, Note> NOTE_REPOSITORY_MAP = new HashMap<>();
    // Переменная для генерации ID клиента
    private static final AtomicInteger NOTE_ID_HOLDER = new AtomicInteger();

    @Override
    public Note create(Note note) {
        final int noteID = NOTE_ID_HOLDER.incrementAndGet();
        note.setId(noteID);
        NOTE_REPOSITORY_MAP.put(noteID, note);
        return note;
    }

    @Override
    public List<Note> readAll() {
        return new ArrayList<>(NOTE_REPOSITORY_MAP.values());
    }

    @Override
    public List<Note> searchBySubstring(String substring) {
        List<Note> foundNotes = new ArrayList<>();
        for(Note note: NOTE_REPOSITORY_MAP.values()){
            if (note.getHeading().contains(substring)
                    | note.getNote().contains(substring))
                {
                    foundNotes.add(note);
                }
        }
        return foundNotes;
    }

    @Override
    public boolean delete(int id) {
        return NOTE_REPOSITORY_MAP.remove(id) != null;
    }
}
