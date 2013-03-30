package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models;

import com.google.gson.Gson;
import edu.wpi.cs.wpisuitetng.modules.AbstractModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.AcceptanceTestList;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.AttachmentList;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.DevelopmentTaskList;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.Iteration;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.NoteCollection;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.SubRequirements;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TeamMemberList;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics.TransactionHistory;

/**
 * Basic Requirement class
 * 
 * @author david
 *
 */
public class Requirement extends AbstractModel {
	/**  the ID of the requirement */
	private int id = 1;
	
	/**  the name of the requirement */
	private String name;
	
	/**  the release number of the requirement */
	private String release;
	
	/**  the project status of the requirement */
	private RequirementStatus status;

	/**  the priority of the requirement */
	private RequirementPriority priority;
	
	/**  a short description of the requirement */
	private String description;
	
	/**  the estimated amount of time to complete the requirement */
	private int estimate;
	
	/**  the actual effort of completing the requirement */
	private int actualEffort;
	
	/** a flag indicating if the requirement is active or deleted */
	private boolean activeStatus;
	
	/** history of transactions of the requirement */
	private TransactionHistory history;
	
	/** the type of the requirement */
	private RequirementType type;
	
	/** subrequirements that must be completed before the current requirement is considered complete */
	private SubRequirements subRequirements;
	
	/** notes associated with the requirement */
	private NoteCollection notes;
	
	/** iteration the requirement is assigned to */
	private Iteration iteration;
	
	/** team members the requirement is assigned to */
	private TeamMemberList assignedTo; 
	
	/** development tasks associated with the requirement */
	private DevelopmentTaskList tasks;
	
	/** acceptance tests associated with the requirement */
	private AcceptanceTestList tests;
	
	/** attachments associated with the requirement */
	private AttachmentList attachments;
	
	/** history log for the requirement */
	private TransactionHistory history;
	
	/**
	 * Constructs a Requirement with default characteristics
	 */
	public Requirement() {
		super();
		id = id + 1;
		name = description = "";
		release = "";
		status = RequirementStatus.NEW;
		priority = RequirementPriority.BLANK;
		estimate = actualEffort = 0;
		activeStatus = true;
		setIteration(new Iteration("Backlog"));
		type = RequirementType.BLANK;
		setNotes(new NoteCollection());
		setTasks(new DevelopmentTaskList());
		setTests(new AcceptanceTestList());
		setAttachments(new AttachmentList());
		setAssignedTo(new TeamMemberList());
	}

	/**
	 * Construct a Requirement with required properties provided and others set to default
	 * 
	 * @param id The ID number of the requirement
	 * @param name The name of the requirement
	 * @param description A short description of the requirement
	 */
	// need to phase out supplying the ID
	public Requirement(int id, String name, String description) {
		this();
		this.name = name;
		this.description = description;
	}

	/**
	 * Constructs a requirement from the provided characteristics
	 * 
	 * @param id The ID number of the requirement
	 * @param name The name of the requirement
	 * @param release The release number of the requirement
	 * @param status The status of the requirement
	 * @param description A short description of the requirement
	 * @param estimate The estimated time required by the task. This is in a point system decided by the user
	 * @param effort The estimated amount of work required by the requirement.
	 * @deprecated
	 */
	@Deprecated
	public Requirement(int id, String name, String release, RequirementStatus status, RequirementPriority priority, String description, int estimate, int effort){
		this.id = id;
		this.name = name;
		this.release = release;
		this.status = status;
		this.priority = priority;
		this.description = description;
		this.estimate = estimate;
		this.actualEffort = effort;
	}

	/**
	 * Returns an instance of Requirement constructed using the given
	 * Requirement encoded as a JSON string.
	 * 
	 * @param the JSON-encoded Requirement to deserialize
	 * @return the Requirement contained in the given JSON
	 */
	public static Requirement fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Requirement.class);
	}

	/**
	/**Getter for id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**Setter for the id
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**getter for the id
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**setter for the name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		if(name.length() > 100) this.name = name.substring(0, 100);
	}

	/**getter for the name
	 * 
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}

	/**Setter for the release number
	 * 
	 * @param release the release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**Getter for the release number
	 * 
	 * @return the status
	 */
	public RequirementStatus getStatus() {
		return status;
	}

	/**Setter for the status
	 * 
	 * @param status the status to set
	 */
	public void setStatus(RequirementStatus status) {
		if (status != this.status){
			String originalStatus = this.status.name();
			String newStatus = status.name();
			String message = ("Changed status from " + originalStatus + " to " + newStatus);
			this.history.add(message);
			UpdateRequirementController.getInstance().updateRequirement(this);
		}

		this.status = status;
		
	}

	/**Getter for the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**Setter for the description
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**Getter for the estimate
	 * 
	 * @return the estimate
	 */
	public int getEstimate() {
		return estimate;
	}

	/**Setter for the estimate
	 * 
	 * @param estimate the estimate to set
	 */
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	/**Getter for the estimate
	 * 
	 * @return the effort
	 */
	public int getEffort() {
		return actualEffort;
	}

	/**Setter for the effort
	 * 
	 * @param effort the effort to set
	 */
	public void setEffort(int effort) {
		this.actualEffort = effort;
	}

	/**Getter for the priority
	 * 
	 * @return the priority
	 */
	public RequirementPriority getPriority() {
		return priority;
	}

	/**Setter for the priority
	 * 
	 * @param priority the priority to set
	 */
	public void setPriority(RequirementPriority priority) {
		if (priority != this.priority){
			String originalPriority = this.priority.name();
			String newPriority = priority.name();
			String message = ("Changed priority from " + originalPriority + " to " + newPriority);
			this.history.add(message);
			UpdateRequirementController.getInstance().updateRequirement(this);
		}
		
		this.priority = priority;
	}
	
	/**Getter for the type
	 * 
	 * @return the type
	 */
	public RequirementType getType() {
		return type;
	}

	/**Setter for the type
	 * 
	 * @param type the type to set the requirement to
	 */
	public void setType(RequirementType type) {
		this.type = type;
	}
	
	/**Getter for subRequirements
	 * @return the subRequirements
	 */
	public SubRequirements getSubRequirements() {
		return subRequirements;
	}

	/**Setter for subRequirements
	 * @param subRequirements the subRequirements to set
	 */
	public void setSubRequirements(SubRequirements subRequirements) {
		this.subRequirements = subRequirements;
	}
	
	/** Getter for Iteration. Currently deals in Strings, but will deal with Iterations in the future
	 * 
	 * @return a string representing the iteration it has been assigned to
	 */
	public Iteration getIteration() {
		return iteration;
	}

	/** Setter for iteration. Currently deals with strings, but will deal with Iterations in the future.
	 * 
	 * @param iteration the iteration to assign the requirement to
	 */
	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}
	
	/** The getter for Transaction History
	 * 
	 * @return a TransdactionHistory for this requirement
	 */
	public TransactionHistory getHistory() {
		return history;
	}
	
	/** The Setter for TransactionHistory
	 * 
	 * @param history The history to assign to the requirement
	 */
	public void setHistory(TransactionHistory history) {
		this.history = history;
	}

	/**
	 * @return the notes
	 */
	public NoteCollection getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(NoteCollection notes) {
		this.notes = notes;
	}

	/**
	 * @return the tasks
	 */
	public DevelopmentTaskList getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(DevelopmentTaskList tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the assignedTo
	 */
	public TeamMemberList getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(TeamMemberList assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * @return the tests
	 */
	public AcceptanceTestList getTests() {
		return tests;
	}

	/**
	 * @param tests the tests to set
	 */
	public void setTests(AcceptanceTestList tests) {
		this.tests = tests;
	}

	/**
	 * @return the attachments
	 */
	public AttachmentList getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(AttachmentList attachments) {
		this.attachments = attachments;
	}
	/** Getter for Iteration. Currently deals in Strings, but will deal with Iterations in the future
	 * 
	 * @return a string representing the iteration it has been assigned to
	 */
	public Iteration getIteration() {
		return iteration;
	}

	/** Setter for iteration. Currently deals with strings, but will deal with Iterations in the future.
	 * 
	 * @param iteration the iteration to assign the requirement to
	 */
	public void setIteration(Iteration newIteration) {
		if(this.iteration == null) this.iteration = newIteration;
		if (!this.iteration.equals(newIteration)){
			String originalIteration = this.iteration.toString();
			String newIterationString = newIteration.toString();
			String message = ("Moved from " + originalIteration + " to " + newIterationString);
			this.history.add(message);
		}
		
		this.iteration = newIteration;
	}
	
	/** Getter for AssignedTo
	 * 
	 * @return the list of strings representing the users for whom the requirement has been assigned to.
	 */ 
	public List<String> getAssignedTo() {
		return assignedTo;
	}

	/**Setter for assignedTo
	 * 
	 * @param assignedTo the list of strings representing the people who the requirement is assigned to.
	 */
	public void setAssignedTo(List<String> assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**Sets a flag in the requirement to indicate it's deleted */
	public void remove() {
		this.activeStatus = false;
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	/**This returns a Json encoded String representation of this requirement object.
	 * 
	 * @return a Json encoded String representation of this requirement
	 * 
	 */
	public String toJSON() {
		return new Gson().toJson(this, Requirement.class);
	}
	
	/**
	 * Returns an array of Requirements parsed from the given JSON-encoded
	 * string.
	 * 
	 * @param a string containing a JSON-encoded array of Requirement
	 * @return an array of Requirement deserialzied from the given JSON string
	 */
	public static Requirement[] fromJsonArray(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Requirement[].class);
	}

	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	/**
	 * Returns whether the requirement has been deleted.
	 * @return delete status of the requirement.
	 */
	public boolean isDeleted() {
		return !activeStatus;
	}
}
