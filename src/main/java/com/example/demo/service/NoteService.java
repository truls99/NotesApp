package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;

    public List<Note> findAll() {
        var it = noteRepo.findAll();
        var noteList = new ArrayList<Note>();

        it.forEach(noteList::add);
        return noteList;
    }

    public String save(Note note) {
        String response = "";

        try {
            if (note.getTitle() != null && note.getDescription() != null) {
                note.setTitle(note.getTitle());
                note.setDescription(note.getDescription());
                noteRepo.save(note);
                response = "Success";
            } else {
                response = "FAILED";
            }
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }

    public String editNote(UUID id, Note newNote) {
        String response = "";
        try {
            Note note = noteRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Could not find note"));
            if (newNote.getTitle() != null && newNote.getDescription() != null) {
                note.setTitle(newNote.getTitle());
                note.setDescription(newNote.getDescription());
                noteRepo.save(note);
                response = "Success";
            } else {
                response = "FAILED";
            }
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }

    public String delete(UUID id) {
        String response = "";
        try {
            Note note = noteRepo.findById(id).orElseThrow(() -> new IllegalAccessException("Could not find note"));
            noteRepo.delete(note);
            response = "Success";
        } catch (Exception e) {
            response = e.getMessage();
        }
        return response;
    }


}
