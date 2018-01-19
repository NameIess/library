package model;

public class User implements Identified {
    public static final String ID_ALIAS = "id";
    public static final String FOREIGN_KEY_ID_ALIAS = "user_id";
    public static final String NAME_ALIAS = "name";
    public static final String EMPLOYEE_NUMBER_ALIAS = "employee_number";
    public static final String SECOND_NAME_ALIAS = "second_name";
    public static final String SURNAME_ALIAS = "surname";
    public static final String EMAIL_ALIAS = "email";
    public static final String PHONE_NUMBER_ALIAS = "phone_number";
    public static final String PASSPORT_SERIES_ALIAS = "passport_series";
    public static final String PASSPORT_NUMBER_ALIAS = "passport_number";
    public static final String PASSWORD_ALIAS = "password";
    public static final String TABLE_NAME = "user";

    private Long id;
    private String employeeNumber;
    private String name;
    private String secondName;
    private String surname;
    private String email;
    private String phoneNumber;
    private String passportSeries;
    private String passportNumber;
    private String password;
    private Role role;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", employeeNumber='").append(employeeNumber).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", passportSeries='").append(passportSeries).append('\'');
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
