package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;

/**A list of notes to be used with the requirement class
 * 
 * @author Gabriel McCormick
 *
 */
public class NoteCollection {
	
	private List<Note> notes;
	
	public NoteCollection (){
		setNotes(new LinkedList<Note>());
	}

	/**
	 * @return the notes
	 */
	public List<Note> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	/** Method to add a note to the list of notes
	 * 
	 * @param note The note to add to the list
	 */
	public void addNote(Note note){
		notes.add(note);
	}
	
	/** Method to remove a note from a list of notes
	 * 
	 * @param id The id of the note to be deleted
	 */
	public void removeNote(int id){
		// iterate through the list looking for the note to remove
		for (int i=0; i < this.notes.size(); i++){
			if (notes.get(i).getId() == id){
				// remove the id
				notes.remove(i);
				break;
			}
		}
	}
	
}
