package hiking_app.request;

import javax.validation.constraints.NotBlank;

public class RegisterHikerModelMapping {

	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotBlank(message = "Password is mandatory")
	private String password;

	@NotBlank(message = "Name is mandatory")
	private String name;

	public RegisterHikerModelMapping() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
