package com.popov.introductiontoandroidnotes.domain;

import android.content.Context;

import com.popov.introductiontoandroidnotes.R;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesRepository implements NotesRepository {

    private static NotesRepository INSTANCE;
    private final Context context;

    private InMemoryNotesRepository(Context context) {
        this.context = context;
    }

    public static NotesRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new InMemoryNotesRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public List<Note> getAll() {
        ArrayList<Note> result = new ArrayList<>();

        result.add(new Note(context.getString(R.string.note_1), R.string.note_date_1, R.string.note_description_1));
        result.add(new Note(context.getString(R.string.note_2), R.string.note_date_2, R.string.note_description_2));
        result.add(new Note(context.getString(R.string.note_3), R.string.note_date_3, R.string.note_description_3));
        result.add(new Note(context.getString(R.string.note_4), R.string.note_date_4, R.string.note_description_4));
        result.add(new Note(context.getString(R.string.note_5), R.string.note_date_5, R.string.note_description_5));

        return result;
    }

    @Override
    public void add(Note note) {
    }
}
