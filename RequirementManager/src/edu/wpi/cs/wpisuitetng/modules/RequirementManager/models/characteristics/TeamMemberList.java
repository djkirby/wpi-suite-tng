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
	
	
}
