package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

import java.util.LinkedList;
import java.util.List;

/**A list of Attachments to be used with the requirement class
 * 
 * @author Gabriel McCormick
 *
 */
public class AttachmentList {

	private List<Attachment> attachments;
	
	public AttachmentList (){
		setAttachments(new LinkedList<Attachment>());
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	/**Adds the supplied attachment to the end of the list of attachments
	 * 
	 * @param attachment
	 */
	public void addAttachment (Attachment attachment){
		this.attachments.add(attachment);
	}
	
	public boolean removeAttachment (int id){
		// iterate through the list looking for the requirement to remove
		for (int i=0; i < this.attachments.size(); i++){
			if (attachments.get(i).getId() == id){
				// remove the id
				attachments.remove(i);
				return true;
			}
		}
		return false;
	}
}
