package model;

public class Recommendation {
	// 0-undecided, 1-accepted, 2-declined
	int state;

	public Recommendation() {
		state = 0;
	}

	public void setState(int state) {
		if (state < 0 || state > 3)
			return;
		this.state = state;

	}
}
