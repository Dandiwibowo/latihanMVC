package dw.tyhmeleaf.paractice1.dto;

import javax.validation.constraints.NotEmpty;
public class IndexDTO {
    
    @NotEmpty(message = "Pleas fill this form")
    private String firstname;
    @NotEmpty(message = "Pleas fill this form")
    private String lastname;
    @NotEmpty(message = "Pleas fill this form")
    private String email;
    @NotEmpty(message = "Pleas fill this form")
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
