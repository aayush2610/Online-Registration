	public abstract class UserDTO {
		private String username = "";
		private String password = "";
		private String role = "";

	
		public String getUsername() {
			return username;
		}

		public void setUsername(String login) {
			this.username = login;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}


