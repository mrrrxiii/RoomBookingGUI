package cst8284.asgmt4.roomScheduler;

import java.io.Serializable;

/**
 * contact information
 * 
 * @author Lei Kong
 * @version 1.00
 * 
 *
 */

public class ContactInfo implements Serializable {
	public static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String organization;

	/**
	 * construct the contact information with the first name ,last name ,phone
	 * number and default organization "Algonquin college"
	 * 
	 * @param firstName   first name of the contact person
	 * @param lastName    last name of the contact person
	 * @param phoneNumber phone number of the contact person
	 * @throws Exception
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
	}

	/**
	 * construct the contact information with the first name ,last name ,phone
	 * number and organization
	 * 
	 * @param firstName    first name of the contact person
	 * @param lastName     last name of the contact person
	 * @param phoneNumber  phone number of the contact person
	 * @param organization organization of the contact person
	 * @throws Exception
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setOrganization(organization);
	}

	/**
	 * get the first name of the contact person
	 * 
	 * @return first name of the contact person
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set the first name of the contact person
	 * 
	 * @param firstName first name of the contact person
	 * @throws Exception
	 * 
	 */
	public void setFirstName(String firstName) {
		if (InputValidation.checkEmpty(firstName) & InputValidation.checkNull(firstName)
				& InputValidation.checkNameChar(firstName) & InputValidation.checkNameLength(firstName)) {
			this.firstName = firstName;
		}
	}

	/**
	 * get the last name of the contact person
	 * 
	 * @return last name of the contact person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set the last name of the contact person
	 * 
	 * @param lastName last name of the contact person
	 * @throws Exception
	 */
	public void setLastName(String lastName) {
		if (InputValidation.checkEmpty(lastName) & InputValidation.checkNull(lastName)
				& InputValidation.checkNameChar(lastName) & InputValidation.checkNameLength(lastName)) {
			this.lastName = lastName;
		}
	}

	/**
	 * get the phone number of the contact person
	 * 
	 * @return phone number of the contact person
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * set phone number of the contact person
	 * 
	 * @param phoneNumber phone number of the contact person
	 * @throws Exception
	 */
	public void setPhoneNumber(String phoneNumber) {
		if (InputValidation.checkEmpty(phoneNumber) & InputValidation.checkNull(phoneNumber)
				& InputValidation.checkPhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
	}

	/**
	 * get the organization of the contact person
	 * 
	 * @return organization of the contact person
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * set the organization of the contact person
	 * 
	 * @param organization organization of the contact person
	 */

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * contact information including the first ,last name , phone number and
	 * organization of the contact
	 * 
	 * @return contact information
	 */
	@Override
	public String toString() {
		return "ContactInfo Information: " + getFirstName() + " " + getLastName() + "\n" + "Phone: " + getPhoneNumber()
				+ (getOrganization().isEmpty() ? "\n" : "\n" + getOrganization() + "\n");
	}

}
