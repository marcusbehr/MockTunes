package edu.century.finalproject.mocktunes;

import java.util.NoSuchElementException;

public class PlaylistQueue {
	private PlayListNode front;
	private PlayListNode rear;
	private int playlistLength;
	
/**
 * 
 * @return playlistLength (how many song objects are in the queue)
 */
	public int getLength() {
		return playlistLength;
	}
	
	/**
	 * checks whether the queue is empty
	 * @return boolean value true or false
	 */
	public boolean isEmpty() {
		return playlistLength == 0;
	}
	
	
	/**
	 * precondition: needs a song object
	 * postcondition: song object added to the queue in first in first out order
	 * @param song
	 */
	public void enqueue(Song song) {
		PlayListNode temp = new PlayListNode(song);
		if (isEmpty()) 
			front = temp;
		else
			rear.next = temp;
		
		rear = temp;
		playlistLength++;
		
	}
	
	/**
	 * postcondition: song at the top of the queue will be returned and removed from the queue
	 * @return the String of the song's songname variable
	 */
	public String dequeue() {
		if(isEmpty()) 
			throw new NoSuchElementException("There are no songs in the playlist");
			
			Song song = front.getSong();
			
			
			if(front == null)
				rear = null;
			else if (front.getNext() == null) {
				front = null;
				rear = null;
			}
			else {
				front = front.getNext();
			}
			
			playlistLength--;
			return song.getSongName();
			
			
		}
	
	/**
	 * returns the list of songs in the queue in format: songname: (songname)
	 */
	public String toString() {
		String data = "";
		PlayListNode cursor = new PlayListNode();
		cursor = front;
		if (cursor == null)
			data = "There are no songs in playlist";
		while (cursor != null) {
//			data += "Artist: " + cursor.getSong().getArtist() + "\n" 
//					+ "Album: " + cursor.getSong().getAlbum() + "\n"
//					+ "Song Name:" + cursor.getSong().getSongName() + "\n"
//					+ "Genre: " + cursor.getSong().getGenre() + "\n"
//					+ "Year: " + cursor.getSong().getYear() + "\n";
			
			data += "Song Name: " + cursor.getSong().getSongName() + "\n";
			
			cursor = cursor.getNext();
		}
		
		
		
		return data;
	}
		
	
	
	class PlayListNode {

		private Song song;
		PlayListNode next;
		
		/**
		 * postcondition: constructor for a playListNode object is created with song and next variables set to null
		 * this object will be used in the queue
		 */
		public PlayListNode() {
			this.song = null;
			this.next = null;
		}
		
	/**
	 * precondition: needs a Song object
	 * postcondition: constructor for a playlistNode object with a song object passed to it and the next as null
	 * this object will be used in the queue
	 * @param song
	 */
		public PlayListNode(Song song) {
			this.song = song;
			this.next = null;
		}
		
		/**
		 * 
		 * @return the Song of the calling object
		 */
		public Song getSong() {
			return this.song;
		}
		
		/**
		 * 
		 * @return the next variable in the playlistNode object
		 */
		public PlayListNode getNext() {
			return this.next;
		}
	
}
}
