package com.popov.introductiontoandroidnotes.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.popov.introductiontoandroidnotes.R;
import com.popov.introductiontoandroidnotes.domain.InMemoryNotesRepository;
import com.popov.introductiontoandroidnotes.domain.Note;

import java.util.List;

public class NotesListFragment extends Fragment {

    public static final String NOTES_CLICKED_KEY = "NOTES_CLICKED_KEY";
    public static final String SELECTED_NOTE = "SELECTED_NOTE";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof ToolbarHolder) {
            ((MainActivity) requireActivity()).setToolbar(toolbar);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_info) {
                    Toast.makeText(requireContext(), "info", Toast.LENGTH_LONG).show();
                    return true;
                }
                if (menuItem.getItemId() == R.id.action_share) {
                    Toast.makeText(requireContext(), "share", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });

        List<Note> notes = InMemoryNotesRepository.getInstance(requireContext()).getAll();

        LinearLayout container = view.findViewById(R.id.container);

        for (Note note : notes) {

            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);

            itemView.findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getParentFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_container, NoteDetailsFragment.newInstance(note)).
                            addToBackStack("details").
                            commit();
                }
            });

            TextView title = itemView.findViewById(R.id.note_title);

            title.setText(note.getName());

            TextView date = itemView.findViewById(R.id.note_date);

            date.setText(note.getDate());

            container.addView(itemView);
        }
    }
}
