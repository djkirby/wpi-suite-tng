/**
 * 
 */
package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;

/**A list of development tasks for use with the requirement
 * 
 * @author Gabriel McCormick
 *
 */
public class DevelopmentTaskList {
	
	private List<DevelopmentTask> tasks;
	
	public DevelopmentTaskList(){
		setTasks(new LinkedList<DevelopmentTask>());
	}

	/**
	 * @return the tasks
	 */
	public List<DevelopmentTask> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<DevelopmentTask> tasks) {
		this.tasks = tasks;
	}
	/** Method to add a development task
	 * 
	 * @param task the task to be added to the list of development tasks
	 */
	public void addTask(DevelopmentTask task){
		tasks.add(task);
	}
	
	/** Method to remove a development task
	 * 
	 * @param 
	 */
	public void removeTask(int id){
		// iterate through the list looking for the note to remove
		for (int i=0; i < this.tasks.size(); i++){
			if (tasks.get(i).getId() == id){
				// remove the id
				tasks.remove(i);
				break;
			}
		}
	}
	
	
}
