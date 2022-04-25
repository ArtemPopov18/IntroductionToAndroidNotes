package com.popov.introductiontoandroidnotes.domain;

import java.util.List;

public interface NotesRepository {

    List<Note> getAll();

    void add(Note note);
}
