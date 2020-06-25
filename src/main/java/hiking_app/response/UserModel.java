package hiking_app.response;

import org.springframework.hateoas.RepresentationModel;

public class UserModel extends RepresentationModel<UserModel> {

	private String email;
	private String name;

	public UserModel() {
		super();
	}

	public UserModel(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
