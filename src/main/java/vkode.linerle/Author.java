package vkode.linerle;

public class Author {

    private String firstName;
    
    private String middleName;
    
    private String lastName;
    
    private Year birthDate;

    public Year getBirthDate() {
        return birthDate;
    }

    public Author setBirthDate(Year birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Author setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }
}
