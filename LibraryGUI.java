package edu.century.finalproject.mocktunes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/*TODO:
 * Change buttons
 * add events to buttons
 * Make table row clickable
 * have the clickable table row do something
 * */
@SuppressWarnings("serial")
public class LibraryGUI extends JFrame implements FileWork, ActionListener {

	private JPanel contentPane;
	private static JTable libraryTable;
	public Library library;
	Thread t;
	SongThreadWork nowPlaying;
	static LibraryGUI frame;
	private JTextArea playlistTextArea;

	public LibraryGUI() {
		// create new library
		library = new Library();

		setTitle("Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel fullbackgroundpanel = new JPanel();
		fullbackgroundpanel.setBounds(12, 0, 1129, 448);
		contentPane.add(fullbackgroundpanel);
		fullbackgroundpanel.setLayout(null);

		JPanel tablecontainerpanel = new JPanel();
		tablecontainerpanel.setBounds(44, 59, 468, 262);
		fullbackgroundpanel.add(tablecontainerpanel);
		tablecontainerpanel.setLayout(new BorderLayout(0, 0));

		libraryTable = new JTable();
		try {
			BufferedReader br = new BufferedReader(new FileReader("songdata.txt"));

			String[] columnsName = { "Artist", "Album", "Song", "Genre", "Year" };

			DefaultTableModel model = (DefaultTableModel) libraryTable.getModel();
			model.setColumnIdentifiers(columnsName);

			Object[] tlines = br.lines().toArray();

			for (int i = 0; i < tlines.length; i++) {
				String line = tlines[i].toString().trim();
				String[] dRow = line.split(",");
				model.addRow(dRow);
			}

			br.close();

		} catch (IOException e) {
			System.out.println("songdata.txt not found.");
		}
		libraryTable.setDefaultEditor(Object.class, null);
		tablecontainerpanel.add(new JScrollPane(libraryTable), BorderLayout.EAST);

		JButton btnStore = new JButton("Store");
		btnStore.setBounds(539, 37, 76, 23);
		fullbackgroundpanel.add(btnStore);
		btnStore.addActionListener(this);

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(this);
		btnPlay.setBounds(155, 334, 89, 23);
		fullbackgroundpanel.add(btnPlay);

		JButton btnPause = new JButton("Remove");
		btnPause.addActionListener(this);
		btnPause.setBounds(256, 334, 89, 23);
		fullbackgroundpanel.add(btnPause);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(this);
		btnStop.setBounds(54, 334, 89, 23);
		fullbackgroundpanel.add(btnStop);

		JPanel playlistPanel = new JPanel();
		playlistPanel.setBorder(null);
		playlistPanel.setBounds(607, 59, 468, 267);
		fullbackgroundpanel.add(playlistPanel);
		playlistPanel.setLayout(new BorderLayout(0, 0));

		playlistTextArea = new JTextArea();
		playlistPanel.add(playlistTextArea, BorderLayout.CENTER);

		JButton btnPlayNextFrom = new JButton("Play next from playlist");
		btnPlayNextFrom.setBounds(768, 334, 169, 25);
		fullbackgroundpanel.add(btnPlayNextFrom);
		btnPlayNextFrom.addActionListener(this);

		JButton btnAddToPlaylist = new JButton("Add to playlist");
		btnAddToPlaylist.setBounds(357, 333, 129, 25);
		btnAddToPlaylist.addActionListener(this);
		fullbackgroundpanel.add(btnAddToPlaylist);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String nameBtn = e.getActionCommand();

		if (nameBtn.equalsIgnoreCase("Play")) {

			try {
				if (nowPlaying != null) {
					if (nowPlaying.running) {
						nowPlaying.stopThread();// kill current thread
					}
				}
				int column = 2;
				int row = libraryTable.getSelectedRow();
				String value = libraryTable.getModel().getValueAt(row, column).toString();

				nowPlaying = new SongThreadWork(value.trim() + ".wav"); // start new thread
				t = new Thread(nowPlaying);
				t.start(); // plays song via thread
			} catch (Exception e1) {
				System.out.println("Must select song to play");
			}

		} else if (nameBtn.equalsIgnoreCase("Stop")) {
			try {
				nowPlaying.stopThread();
			} catch (Exception e2) {
				System.out.println("Must select song to play");
			}
		} else if (nameBtn.equalsIgnoreCase("Remove")) {

			int row = libraryTable.getSelectedRow();

			String artist = libraryTable.getModel().getValueAt(row, 0).toString();
			String album = libraryTable.getModel().getValueAt(row, 1).toString();
			String songname = libraryTable.getModel().getValueAt(row, 2).toString();
			String genre = libraryTable.getModel().getValueAt(row, 3).toString();
			String year = libraryTable.getModel().getValueAt(row, 4).toString();

			int yearInteger = Integer.parseInt(year.trim());

			Song song = new Song(artist, album, songname, genre, yearInteger);
			Library.removeFromLibrary(song);

		} else if (nameBtn.equalsIgnoreCase("Add to playlist")) {

			try {
				int row = libraryTable.getSelectedRow();

				String artist = libraryTable.getModel().getValueAt(row, 0).toString();
				String album = libraryTable.getModel().getValueAt(row, 1).toString();
				String song = libraryTable.getModel().getValueAt(row, 2).toString();
				String genre = libraryTable.getModel().getValueAt(row, 3).toString();
				String year = libraryTable.getModel().getValueAt(row, 4).toString();

				int yearInteger = Integer.parseInt(year.trim());

				Song newsong = new Song(artist, album, song, genre, yearInteger);
				library.addToPlaylist(newsong);
				library.playlist.toString();

				playlistTextArea.setText("");
				playlistTextArea.append("UP NEXT IN PLAYLIST: \n");
				playlistTextArea.append(library.playlist.toString());
			} catch (Exception e3) {
				System.out.println("Must select song first!");

			}

		} else if (nameBtn.equalsIgnoreCase("Store")) {
			dispose();

			StoreGUI secondFrame = new StoreGUI();

			secondFrame.setLocationRelativeTo(null);
			secondFrame.setVisible(true);
		} else if (nameBtn == "Play next from playlist") {
			if (nowPlaying != null) 
				if (nowPlaying.running) 
					nowPlaying.stopThread();// kill current thread
			
			
			String songname = library.playNextSongInPlaylist(); // getting next song from the playlist
			nowPlaying = new SongThreadWork(songname); // starting new thread
			t = new Thread(nowPlaying);
			t.start();

			//update the playlist text area
			playlistTextArea.setText("");
			playlistTextArea.append("UP NEXT IN PLAYLIST: \n");
			playlistTextArea.append(library.playlist.toString());
		}
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LibraryGUI();
					frame.setVisible(true);

					// TODO: MAKE THIS INTO METHOD
					String filepath = "songdata.txt";
					File file = new File(filepath);
				} catch (Exception e) {
					System.out.println("File not found.");
				}
			}
		});

	}

}
