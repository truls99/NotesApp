package com.example.demo.repo;

import com.example.demo.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepo extends CrudRepository<Note, UUID> {
}
