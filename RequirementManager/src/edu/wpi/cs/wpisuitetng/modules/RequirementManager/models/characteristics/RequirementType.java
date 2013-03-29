package edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.characteristics;

/**
 * Various types that a Requirement can be
 * @author Gabriel McCormick
 */
public enum RequirementType {
	/** Indicates that the Requirement does not have an associated type */
	BLANK,
	/** Indicates that the Requirement is an Epic */
	EPIC,
	/** Indicates that the Requirement is a theme */
	THEME,
	/** Indicates that the Requirement is a user story */
	USERSTORY,
	/** Indicates that the Requirement is a non-functional requirement */
	NONFUNCTIONAL,
	/** Indicates that the Requirement is a scenario */
	SCENARIO
}