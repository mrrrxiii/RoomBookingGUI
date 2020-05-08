package cst8284.asgmt4.roomScheduler;

import java.io.Serializable;

/**
 * 
 * activity information including category and description
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */

public class Activity implements Serializable {
	public static final long serialVersionUID = 1L;
	private String description;
	private String category;

	/**
	 * two-arg constructor set the category and description
	 * 
	 * @param category    event category
	 * @param description event description
	 * @throws Exception
	 */
	public Activity(String category, String description) {
		setCategory(category);
		setDescription(description);

	}

	/**
	 * get the event description
	 * 
	 * @return event description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set the event description
	 * 
	 * @param description event decription
	 * @throws Exception
	 */
	public void setDescription(String description) {
		if (InputValidation.checkEmpty(description) & InputValidation.checkNull(description)) {
			this.description = description;
		}
	}

	/**
	 * get the event category
	 * 
	 * @return event category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * set the event category
	 * 
	 * @param category event category
	 * @throws Exception
	 */
	public void setCategory(String category) {
		if (InputValidation.checkEmpty(category) & InputValidation.checkNull(category)) {
			this.category = category;
		}
	}

	/**
	 * information of the event category and description
	 * 
	 * @return event category and description
	 * 
	 */
	@Override
	public String toString() {
		return "Event: " + getCategory() + "\n" + "Description: " + getDescription();
	}

}
