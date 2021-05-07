package com.reilgan.notes.service;

import com.reilgan.notes.model.Note;

import java.util.List;

public interface NoteService {
    /**
     * Создает новую заметку
     * @param note - заметка для создания
     */
    Note create(Note note);

    /**
     * Возвращает список всех имеющихся заметок
     * @return список клиентов
     */
    List<Note> readAll();

    /**
     * Возвращает список заметок по заданной подстроке
     * @param substring - подстрока для поиска
     * @return - список найденых по подстроке заметок
     */
    List<Note> searchBySubstring(String substring);

    /**
     * Удаляет заметку с заданным ID
     * @param id - id заметки, которую нужно удалить
     * @return - true если заметка была удалена, иначе false
     */
    boolean delete(int id);
}
