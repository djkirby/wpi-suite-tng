package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;
 /**
  * 
  * @author Gabriel McCormick
  *
  */
public class TeamMemberList {
	
	private List<String> teamMembers;
	
	public TeamMemberList (){
		setTeamMembers(new LinkedList<String>());
	}

	/**
	 * @return the teamMembers
	 */
	public List<String> getTeamMembers() {
		return teamMembers;
	}

	/**
	 * @param teamMembers the teamMembers to set
	 */
	public void setTeamMembers(List<String> teamMembers) {
		this.teamMembers = teamMembers;
	}
	
	/**addTeamMebers adds a username of a team member represented by a string to the list of team members
	 * 
	 * @param teamMember String the the team member's username
	 */
	public void addTeamMembers(String teamMember){
		this.teamMembers.add(teamMember);
	}
	
	/**removeTeamMember removes the supplied username from the list of teamMembers
	 * 
	 * @param teamMember String of the username of the team member to be removed
	 * @return true if the string was removed and found, false if the string was not found
	 */
	public boolean removeTeamMember(String teamMember){
		// iterate through the list looking for a matching string
		for (int i=0; i < this.teamMembers.size(); i++){
			if (teamMembers.get(i).equals(teamMember)){
				// remove the id
				teamMembers.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
}
