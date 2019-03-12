package edu.century.finalproject.mocktunes;



public class SongCollection {
	private Song[] songList;
	private int index;
	
	public int getIndex() {
		return index;
	}

	public SongCollection() {
		final int INITIAL_CAPACITY = 20;
		songList = new Song[INITIAL_CAPACITY];
		index = 0;
	}

	public void addSong(Song song) {

		if (index == songList.length) {
			ensureCapacity(index * 2 + 1);
		} else {
			songList[index] = song;
			index++;
		}
	}

	private void ensureCapacity(int i) {
		System.out.print("in ensurecapacity");
		Song[] tmp = new Song[i];
		
		for (int a = 0; a < songList.length; a++) {
			tmp[a] = songList[a];
		}

		songList = tmp;
	}

	public void removeSong(Song song) {
		for (int b = 0; b < index; b++) {
			if (song == songList[b]) {
				songList[b] = songList[index - 1];
				index--;

			}
		}
	}

	public boolean searchBySongname(String songname) {
		for (int i = 0; i < index; i++) {
			if(songname.equals(songList[i].getSongName().trim()))
				return true;
		}
		
		return false;
			
	}

	public Song findSong(Song song) {
		if (song == null)
			throw new IllegalArgumentException();

		for (int a = 0; a < index; a++) {
			if (songList[a].getSongName().equals(song.getSongName()))
				return song;

		}
		return null;
	}

	public String toString() {
		String data = "";

		for (int a = 0; a < index; a++) {
			data += songList[a].getArtist() + " , " + songList[a].getSongName() + 
					" , " + songList[a].getAlbum() + " , " + songList[a].getGenre() +
					" , " + songList[a].getYear() + "\r\n";
		}

		return data;
	}

	public void addAll(SongCollection addend) {
		if(index==songList.length)
		ensureCapacity((index + addend.index) * 2 + 1);
		System.arraycopy(addend.songList, 0, songList, index, addend.index);
		index = addend.index;
				
	}

}
