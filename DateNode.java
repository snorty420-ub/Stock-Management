package col106.assignment5;

public class DateNode implements DateInterface {

	int day;
	int month;
	int year;

	public DateNode(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return this.day;
	}

	public int getMonth() {
		return this.month;
	}

	public int getYear() {
		return this.year;
	}

	public int compare(DateNode b){
		// if a is older than b = -1
		// if a is newer than b = 1
		// else 0
		if(year > b.year){
			return 1;
		} else if(year < b.year){
			return -1;
		} else {
			if(month > b.month){
				return 1;
			} else if(month < b.month){
				return -1;
			} else {
				if(day > b.day){
					return 1;
				} else if(day < b.day){
					return -1;
				} else {
					return 0;
				}
			}
		}

	}

	public String toString() {
		return day + "/" + month + "/" + year;
	}

}
