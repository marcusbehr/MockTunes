package edu.century.finalproject.mocktunes;

public class Song {
	private String artist;
	private String album;
	private String songName;
	private String genre;
	private int year;
	public ReviewsNode reviews;
	
	//some constant values for genre, want to keep the genres as broad as possible 
	final String ROCK = "Rock";
	final String RAP = "Hip Hop";
	final String COUNTRY = "Country";
	final String ELECTRONIC = "Electronic";
	final String GOSPEL = "Gospel"; //lolgospel
	
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setgenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Song() {
		artist = null;
		album = null;
		songName = null;
		genre = null;
		year = 1900;
		reviews = new ReviewsNode();
	}
	
	public Song(String artist, String album, String songName, String genre, int year) {
		this.artist = artist;
		this.album = album;
		this.songName = songName;
		this.genre = genre;
		this.year = year;
		reviews = new ReviewsNode();
	}
	
	public void addReview(String name, int rating, String review) {
		this.reviews.addReview(name, rating, review);
	}
	
	public String toString() {
		
		return artist + " - " + songName +  "\n\n" + reviews;
	}
	
	public static void main(String[] args) {
		Song song1 = new Song("Some artist","some album", "some songname", "some genre",5);
		song1.addReview("Marcus", 0, "Not good dude");
		song1.addReview("Jeff", 5, "Not good at all");
		
		System.out.println(song1);
	}
}
