package edu.century.finalproject.mocktunes;

public class ReviewsNode {
	private String name;
	private int rating;
	private String review;
	private String stars = "";
	private ReviewsNode next;
	private ReviewsNode head;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public ReviewsNode getNext() {
		return next;
	}

	public void setNext(ReviewsNode next) {
		this.next = next;
	}

	public ReviewsNode getHead() {
		return head;
	}

	public void setHead(ReviewsNode head) {
		this.head = head;
	}

	public ReviewsNode() {
		this.name = null;
		this.rating = 0;
		this.review = null;
		this.next = null;
		head = null;
	}
	
	public ReviewsNode(String name, int rating, String review) {
		this.name = name;
		this.rating = rating;
		this.review = review;
		this.next = null;
		head = null;
	}
	
	/**
	 * 
	 * @param name
	 * @param rating
	 * @param review
	 */
	public void addReview(String name, int rating, String review) {
		ReviewsNode temp = new ReviewsNode(name,rating,review);
		temp.next = head;
		head = temp;
	}
	
	public String printStars(int n) {
		
		if (n==1)
			return "*";
		else
			return "*" + printStars(n-1);
	}
	
	public String toString() {
		String data = "";

		ReviewsNode cursor = new ReviewsNode();
		cursor = head;
		
		while (cursor != null) {
			
			int stars = cursor.getRating();
			
			data += "Name: " + cursor.getName() + "\n"
					+ "Rating: " + printStars(cursor.getRating()) + "\n"
					+ "Review: " + cursor.getReview() + "\n" + "\n";
			
			cursor = cursor.next;
		}
		
		return data;
	}
	
public static void main(String[] args) {
	
}
}
