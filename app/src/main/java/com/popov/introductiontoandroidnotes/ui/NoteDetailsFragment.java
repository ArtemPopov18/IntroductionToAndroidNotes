package com.popov.introductiontoandroidnotes.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.popov.introductiontoandroidnotes.R;
import com.popov.introductiontoandroidnotes.domain.Note;

public class NoteDetailsFragment extends Fragment {

    private static final String AFG_NOTE = "AFG_NOTE";
    private TextView title;
    private TextView date;
    private TextView description;

    public NoteDetailsFragment() {
        super(R.layout.fragment_note_details);
    }

    public static NoteDetailsFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(AFG_NOTE, note);

        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.note_title);
        date = view.findViewById(R.id.note_date);
        description = view.findViewById(R.id.note_description);

        getParentFragmentManager()
                .setFragmentResultListener(NotesListFragment.NOTES_CLICKED_KEY, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = result.getParcelable(NotesListFragment.NOTES_CLICKED_KEY);

                        showNote(note);
                    }
                });

        if (getArguments() != null && getArguments().containsKey(AFG_NOTE)) {
            Note note = getArguments().getParcelable(AFG_NOTE);

            showNote(note);
        }
    }

    private void showNote(Note note) {
        title.setText(note.getName());
        date.setText(note.getDate());
        description.setText(note.getDescription());
    }
}
