package model;

/**
 * @author Anton Bardakhanov, Steven Bradley
 *
 */
public class Recommendation {
	// [5] strong accept [4] accept [3] neutral [2] reject [1] strong reject
	int state;
	String rationale;

	public Recommendation() {
		state = 0;
	}

	public void setState(int state) {
		if (state < 0 || state > 3)
			return;
		this.state = state;

	}
	
	public void setRationale(String comments) {
		rationale = comments;
	}
	
	public String toString() {
		return state + "," + rationale;
	}
}
