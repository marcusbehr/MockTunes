package edu.century.finalproject.mocktunes;

public class Store implements FileWork{
	public SongCollection songsInStore;
	
	public Store() {
		songsInStore = new SongCollection();
		FileWork.readFile("storedata.txt");
		this.songsInStore.addAll(FileWork.songs);
		
		
		
		
		
		
//		Song song1 = new Song("album", "artist", "songname", "genre", 1980);
//		Song song2 = new Song("album2", "artist2", "songname2", "genre2", 1980);
//		Song song3 = new Song("album3", "artist3", "songname", "genre", 1980);
//		Song song4 = new Song("album4", "artist4", "songname2", "genre2", 1980);
//		
//		songsInStore.addSong(song1);
//		songsInStore.addSong(song2);
//		songsInStore.addSong(song3);
//		songsInStore.addSong(song4);
		
		//FileWork.readFile(STORE_FILE);
	}	
}
