package edu.century.finalproject.mocktunes;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Library implements FileWork{
	public final static String DATA_FILE = "songdata.txt";
	public SongCollection songs;
	public Song song;
	public PlaylistQueue playlist;
	public static SongCollection s; // new
	
	/**
	 * constructor for library class
	 * postcondition: songcollection initialized, playlist initialized, filework reads songdata.txt
	 * and adds these songs to the filework song collection which is added to this library
	 */
	public Library() {	
		songs = new SongCollection();
		playlist = new PlaylistQueue();
		FileWork.readFile(DATA_FILE);
		this.songs.addAll(FileWork.songs);
		
	}
	
	/**
	 * precondition: needs song object
	 * postcondition: song is added to library's playlist as queue.
	 * @param song
	 */
	public void addToPlaylist(Song song) {
		playlist.enqueue(song);
		System.out.println(this.playlist);
	}
	
	/**
	 * plays the first song in the library's playlist
	 * @return a string with the .wav extension for file retrieval
	 */
	public String playNextSongInPlaylist() {
		return playlist.dequeue().trim() + ".wav";
	}
	
	/**
	 * precondition: needs a song object
	 * postcondition: song data from the song object is added to the songdata.txt file which will be added to the 
	 * library when the library constructor is called again from LibraryGUI
	 * @param song
	 */
	public static void buyFromStore(Song song) {
		if(FileWork.songs.searchBySongname(song.getSongName().trim()))
			System.out.println("Song in library!");
		else
			FileWork.updateFile(song, "songdata.txt");
	}
	
	/**
	 * precondition: needs a song object
	 * postcondition: filework removes the song from songdata.txt which will update the library upon
	 * refresh of the libraryGUI
	 * @param song
	 */
	public static void removeFromLibrary(Song song) {
		
		if (FileWork.songs.searchBySongname(song.getSongName().trim())) {
			FileWork.songs.removeSong(song);
			FileWork.overWriteFile("songdata.txt");
		}
		else
			System.out.println("Song not in library!");
	}
	
	/**
	 * return the toString of songs in this song collection
	 * @return
	 */
	public SongCollection getSongs() {
		return this.songs;
	}
	
}
