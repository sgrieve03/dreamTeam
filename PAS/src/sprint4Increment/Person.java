/**
 * 
 */
package sprint4Increment;


/**
 * Creating the Person class to GitHub
 * @author Hugh
 *
 */
public class Person implements Comparable<Person> {

	/**
	 * Initialising the firstNames
	 */
	private String firstName;

	/**
	 * initialising the lastName
	 */
	private String lastName;

	/**
	 * Initialising the address
	 */
	private String address;

	/**
	 * Initialising the contactNo
	 */
	private String contactNo;

	/**
	 * Initialising the nHSNumber
	 */
	private int nHSNumber;

	/**
	 * Initialising the category
	 */
	private int category;

	/**
	 * Initialising the bloodType
	 */
	private String bloodType;

	/**
	 * Initialising the allergies
	 */
	private String[] allergies;

	/**
	 * Initialising the gender
	 */
	private String gender;

	/**
	 * Initialising the age
	 */
	private int age;

	/**
	 * Initialising the patientTime
	 */
	private int patientTime;

	/**
	 * Initialising the insureProvider
	 */
	private String insureProvider;

	/**
	 * Initialising the euMember
	 */
	private boolean euMember;

	/**
	 * Setting the default constructor
	 */
	public Person() {

	}

	/**
	 * Creating the Person constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param contactNo
	 * @param nHSNumber
	 * @param category
	 * @param bloodType
	 * @param allergies
	 * @param gender
	 * @param age
	 * @param patientTime
	 * @param insureProvider
	 * @param euMember
	 */
	public Person(String firstName, String lastName, String address,
			String contactNo, int nHSNumber, int category, String bloodType,
			String[] allergies, String gender, int age, int patientTime,
			String insureProvider, boolean euMember) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNo = contactNo;
		this.nHSNumber = nHSNumber;
		this.category = category;
		this.bloodType = bloodType;
		this.allergies = allergies;
		this.gender = gender;
		this.age = age;
		this.patientTime = patientTime;
		this.insureProvider = insureProvider;
		this.euMember = euMember;

	}

	public Person(int i, String string, String string2, int j) {
		this.category = i;
		this.firstName = string;
		this.lastName = string2;
		this.patientTime = j;
		
		// TODO Auto-generated constructor stub
	}

	public Person(int i, String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getting the firstName
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * setting the firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * getting the lastName
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * setting the lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * getting the address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * setting the address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * getting the contactNo
	 * @return contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * setting the contactNo
	 * @param contactNo
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * getting the nHSNumber
	 * @return nHSNumber
	 */
	public int getnHSNumber() {
		return nHSNumber;
	}

	/**
	 * setting the nHSNumber
	 * @param nHSNumber
	 */
	public void setnHSNumber(int nHSNumber) {
		this.nHSNumber = nHSNumber;
	}

	/**
	 * getting the category
	 * @return category
	 */
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) 
	{
		// triage must be 0, 1, 2 or 3
		if(category >= 0 && category <= 3)
		{
			this.category = category;
		}
		// the default triage value is 3
		else
		{
			System.out.println("Triage must be 0, 1, 2 or 3");
			System.out.println("Setting triage to default of 3");
			setCategory(3);
		}
	}

	/**
	 * getting the BloodType
	 * @return bloodType
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * setting the BloodType
	 * @param bloodType
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	/**
	 * getting the gender
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * setting the gender
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * getting the age
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * setting the age
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * getting the InsureProvider
	 * @return insureProvider
	 */
	public String getInsureProvider() {
		return insureProvider;
	}

	/**
	 * setting the InsureProvider
	 * @param insureProvider
	 */
	public void setInsureProvider(String insureProvider) {
		this.insureProvider = insureProvider;
	}

	/**
	 * getting the isEuMember
	 * @return euMember
	 */
	public boolean isEuMember() {
		return euMember;
	}

	/**
	 * setting the EUMember
	 * @param euMember
	 */
	public void setEuMember(boolean euMember) {
		this.euMember = euMember;
	}

	/**
	 * getting the PatientTime
	 * @return patientTime
	 */
	public int getPatientTime() {
		return patientTime;
	}

	public void setPatientTime(int time) 
	{
		if(time >= 0)
		{
			this.patientTime = time;
		}
		else
		{
			System.out.println("Time can't be negative.");
			System.out.println("Setting default time to 0");
			setPatientTime(0);
		}
	}
	/**
	 * getting the Allergies
	 * @return allergies
	 */
	public String[] getAllergies() {
		return allergies;
	}

	/**
	 * setting the Allergies
	 * @param allergies
	 */
	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}
	
	// toString() is printed for every object in a LinkedList
		// when we use System.out.println(list)
		public String toString()
		{
			return "Name: " +getFirstName()+ " " +getLastName()+ " - Triage level: " +getCategory()+ " - time waiting: " +getPatientTime()+ " mins\n";
		}
		
		// This is the overridden method implemented from the 
		// Comparable interface. It compares the triage values
		// of the patients, enabling sorting of the patients
		@Override
		public int compareTo(Person p) 
		{
			int comparedTriage = p.getCategory();
			
			if (this.category > comparedTriage) 
			{
				return 1;
			} 
			else if (this.category == comparedTriage) 
			{
				return 0;
			} 
			else
			{
				return -1;
			}
		}
		
		boolean isFixed(){
			return true;}

}
