public class Movie {
	private String title;
	private String productionCompany;
	private int year;
	private int hours;
	private int minutes;
	
	public Movie(String title, String productionCompany, int year, int hours, int minutes) {
		this.title = title;
		this.productionCompany = productionCompany;
		this.year = year;
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public int getDuration() {
		return 60 * hours + minutes;
	}
	
	@Override
	public String toString() {
		return "Title: " + this.title + "\n" +
				"Production Company: " + this.productionCompany + "\n" +
				"Year: " + this.year + "\n" +
				"Duration: " + this.getDuration() + "\n";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductionCompany() {
		return productionCompany;
	}

	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
}