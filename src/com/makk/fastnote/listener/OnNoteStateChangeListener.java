package com.makk.fastnote.listener;

import com.makk.fastnote.data.Note;

/**
*
*/
public interface OnNoteStateChangeListener {

    void onAddButtonClicked();
    void onNoteDetailsOpen(Note note);
    void onNoteAdded(String title, String content);
    void onNoteChanged(Note newNote, Note oldNote);
}
