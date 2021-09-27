package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public ResponseEntity<Object> getAll() {
        List<Note> response = noteService.findAll();
        ResponseEntity responseEntity;

        if (response.isEmpty()) {
            responseEntity = new ResponseEntity("Could not find any notes", HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(response, HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Note note) {
        ResponseEntity<String> responseEntity;
        String response = noteService.save(note);

        if (response.equals("Success")) {
            responseEntity = new ResponseEntity<>("Success, ID: " + note.getId(), HttpStatus.OK);
        } else if (response.equals("FAILED")){
            responseEntity = new ResponseEntity<>("Title or description cannot be empty\"", HttpStatus.NOT_ACCEPTABLE) ;
        } else {
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("/edit")
    public ResponseEntity<String> edit(@RequestParam UUID id, @RequestBody Note note) {
        ResponseEntity<String> responseEntity;
        String response = noteService.editNote(id, note);

        if (response.equals("Success")) {
            responseEntity = new ResponseEntity<>("Success", HttpStatus.OK);
        } else if (response.equals("FAILED")) {
            responseEntity = new ResponseEntity<>("Title or description cannot be empty", HttpStatus.NOT_ACCEPTABLE);
        } else {
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam UUID id) {
        ResponseEntity<String> responseEntity;
        String response = noteService.delete(id);

        if (response.equals("Success")) {
            responseEntity = new ResponseEntity<>("Note deleted", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
