package configurationProject.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User {
	
	public User() {	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "component_id")
	private Long id;
	
	@Column(unique = true)
	@NotBlank(message = "username is mandatory")
	private String username;
	
	@Column
	@NotBlank(message = "password is mandatory")
	private String password;
	
	@Column
	@NotBlank(message = "first name is mandatory")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "last name is mandatory")
	private String lastName;
	
	@Column
	@Enumerated(EnumType.STRING)
	@NotNull(message = "User role is mandatory")
	private UserRole userRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
	
	
}
