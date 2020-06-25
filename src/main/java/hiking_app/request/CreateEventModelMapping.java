package hiking_app.request;

public class CreateEventModelMapping {

	private String name;
	private String dateTime;

	public CreateEventModelMapping() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
