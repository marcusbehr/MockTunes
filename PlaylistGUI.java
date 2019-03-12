package edu.century.finalproject.mocktunes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EventListener;
import java.awt.event.ActionEvent;


/*TODO:
 * Change buttons
 * add events to buttons
 * Make table row clickable
 * have the clickable table row do something
 * */
public class PlaylistGUI extends JFrame implements FileWork, ActionListener {
	
	private Library library;
	private JPanel contentPane;
	private static JTable playlistJTable;
	Thread t;
	SongThreadWork s;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PlaylistQueue playlistTest = new PlaylistQueue();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaylistGUI frame = new PlaylistGUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		
		});
	}

	/**
	 * Create the frame.
	 */
	public PlaylistGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fullbackgroundpanel = new JPanel();
		fullbackgroundpanel.setBounds(0, 0, 555, 378);
		contentPane.add(fullbackgroundpanel);
		fullbackgroundpanel.setLayout(null);
		
		JPanel tablecontainerpanel = new JPanel();
		tablecontainerpanel.setBounds(44, 59, 468, 262);
		fullbackgroundpanel.add(tablecontainerpanel);
		tablecontainerpanel.setLayout(new BorderLayout(0, 0));
		
	
		playlistJTable = new JTable();
		tablecontainerpanel.add(new JScrollPane(playlistJTable), BorderLayout.CENTER);
		
		JButton btnStore = new JButton("Store");
		btnStore.setBounds(469, 11, 76, 23);
		fullbackgroundpanel.add(btnStore);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(this);
		btnPlay.setBounds(230, 344, 89, 23);
		fullbackgroundpanel.add(btnPlay);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(this);
		btnPause.setBounds(329, 344, 89, 23);
		fullbackgroundpanel.add(btnPause);
		
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(this);
		btnStop.setBounds(131, 344, 89, 23);
		fullbackgroundpanel.add(btnStop);
		
		JButton playlistBtn = new JButton("Playlist");
		playlistBtn.addActionListener(this);
		playlistBtn.setBounds(10, 327, 51, 24);
		fullbackgroundpanel.add(playlistBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
