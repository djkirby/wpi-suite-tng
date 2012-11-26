package edu.wpi.cs.wpisuitetng.modules.core.models;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.database.TNG;
import edu.wpi.cs.wpisuitetng.modules.Model;

/**
 * The Data Model representation of a Project. Offers
 * 	serialization and database interaction.
 * @author mpdelladonna (sp?), twack
 */

public class Project implements Model, TNG
 {
	private String name;
	private String idNum;
	
	/**
	 * Primary constructor for a Project
	 * @param name	the project name
	 * @param idNum	the ID number to associate with this Project.
	 */
	public Project(String name, String idNum)
	{
		this.name = name;
		this.idNum = idNum;
	}
	
	/* Accessors */
	public String getName()
	{
		return name;
	}
	
	public String getIdNum()
	{
		return idNum;
	}
	
	/* database interaction */
	
	/**
	 * Implements Model-specific save logic
	 */
	public void save()
	{
		return; // TODO: implement saving during API - DB Layer Link up
	}
	
	/**
	 * Implements Model-specific delete logic
	 */
	public void delete()
	{
		return; // TODO: implement deleting during API - DB Layer Link up
	}
	
	
	/* Serializing */
	
	/**
	 * Serializes this Project's member variables into
	 * 	a JSON string.
	 * @return	the JSON string representation of this Project
	 */
	public String toJSON()
	{
		String json;
		
		Gson gson = new Gson();
		
		json = gson.toJson(this, Project.class);
		return json;
	}
	
	/**
	 * Static Project method that serializes a list of Projects
	 * 	into JSON strings.
	 * @param u	The list of Projects to serialize
	 * @return	a comma delimited list of Project JSON strings.
	 */
	public static String toJSON(Project[] u)
	{
		String json ="";
		
		Gson gson = new Gson();
		
		json = gson.toJson(u, Project[].class);
		
		
		return json;
		
	}
	
	
	/* Built-in overrides/overloads */
	
	/**
	 * Returns the JSON representation of this object as 
	 * 	the toString.
	 * 
	 * @return	the String returned by toJSON()
	 * @see		Project.toJSON()
	 */
	public String toString()
	{
		return this.toJSON();
	}
	
	@Override
	public Boolean identify(Object o)
	{
		Boolean b  = false;
		
		if(o instanceof Project)
		{
			if(((Project) o).getIdNum().equalsIgnoreCase(this.idNum))
				{
					b = true;
				}
		}
		
		if(o instanceof String)
			if(((String) o).equalsIgnoreCase((this.idNum)))
				b = true;
		
		
		return b;
	}
}
