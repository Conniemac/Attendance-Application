package app;

public class Person {

    private String firstName, lastName;
    private String major;
    private String email;
    private boolean returningMember;
    private int matchingNames;
    private int id;

    public Person(String firstName, String lastName, boolean returning){

        // Checks to see if the first letter in each name is upper case if it is not
    	// it makes the first letter a capital
        if(firstName.charAt(0) > 90){

            StringBuilder newFirst = new StringBuilder(firstName);

            newFirst.setCharAt(0, Character.toUpperCase(firstName.charAt(0)));
            firstName = newFirst.toString();
        }
        if(lastName.charAt(0) > 90){

            StringBuilder newLast = new StringBuilder(lastName);

            newLast.setCharAt(0, Character.toUpperCase(lastName.charAt(0)));
            lastName = newLast.toString();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.returningMember = returning;
    }

    public String getFirstName(){

        return firstName;
    }

    public String getLastName(){

        return lastName;
    }

    public String getMajor(){

        return major;
    }

    public String getEmail(){

        return email;
    }

    public boolean getReturning(){

        return returningMember;
    }

    // Returns the numbers of names that match the users that are already in the database
    public int getMatchingNames(){

        return matchingNames;
    }

    public int getId(){

        return id;
    }

    public void setMajor(String major){

        this.major = major;
    }

    public void setEmail(String email){

        this.email = email;
    }

    public void setMatchingNames(int matchingNames){

        this.matchingNames = matchingNames;
    }

    public void setId(int id){

        this.id = id;
    }
}