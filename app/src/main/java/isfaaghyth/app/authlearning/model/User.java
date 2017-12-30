package isfaaghyth.app.authlearning.model;

public class User{
	private int id;
	private int role;
	private String name;
	private String email;
	private String picture;
	private String createdAt;
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public int getRole(){
		return role;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getPicture(){
		return picture;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}
