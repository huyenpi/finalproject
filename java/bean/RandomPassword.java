package bean;

public class RandomPassword {
	private int lengthOfPassword = 6;

	public int getLength() {
		return lengthOfPassword;
	}

	public void setLength(int lengthOfPassword) {
		this.lengthOfPassword = lengthOfPassword;
	}

	public char randomCharacter() {
		/*
		 * In ASCII '0' - '9' => 48 - 57 'A' - 'Z' => 65 - 90 'a' - 'z' => 97 - 122 62
		 * characters
		 */
		int rand = (int) (Math.random() * 62); // 0-61

		if (rand <= 9) { // 0 - 9 => 48-57
			rand += 48;
			return (char) (rand);

		} else if (rand <= 35) { // 10-35 => 65-90
			rand += 55;
			return (char) (rand);
		} else { // 36-61 => 67-122
			rand += 61;
			return (char) (rand);
		}
	}

	public String randomPassword() {
		String pass = "";
		for (int i = 0; i < lengthOfPassword; i++) {
			pass += randomCharacter();
		}
		return pass;
	}
}
