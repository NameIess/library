package model;

import java.io.Serializable;

public class User implements Identified, Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) {
            return false;
        }

        if (employeeNumber != null ? !employeeNumber.equals(user.employeeNumber) : user.employeeNumber != null) {
            return false;
        }

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }

        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) {
            return false;
        }

        if (surname != null ? !surname.equals(user.surname) : user.surname != null) {
            return false;
        }

        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }

        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) {
            return false;
        }

        if (passportSeries != null ? !passportSeries.equals(user.passportSeries) : user.passportSeries != null) {
            return false;
        }

        if (passportNumber != null ? !passportNumber.equals(user.passportNumber) : user.passportNumber != null) {
            return false;
        }

        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }

        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employeeNumber != null ? employeeNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
