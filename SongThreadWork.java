package edu.century.finalproject.mocktunes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SongThreadWork implements LineListener, Runnable {

	Clip clip;
	boolean songover;
	volatile boolean running = true;
	
	// initializes a clip with the song provided via filename 
	public SongThreadWork(String filename) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.addLineListener(this);
				clip.open(audioInput);
			}else {
				System.out.println("didn't work");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static void main(String[] args) {
		
	}
	// this works but sloppy stack overflow answer
	public void play(){
		System.out.println("before");
	    	clip.setFramePosition(0);
	    	clip.start();
	    	while(!songover) {
	    		try {
	    			Thread.sleep(1000);
	    		}catch(InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    
	}
	
	// using clip.stop in a diff method but can use this later for visibility
	public void stop() {
		clip.stop();
	}

	// don't think this does anything
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();
		if(type==LineEvent.Type.START) {
		}
	}


	// creates a thread specifically for the clip, and plays when play button, actionPerformed on HomeGUI
	@Override
	public void run() {
		while(running) {
	    	clip.setFramePosition(0);
	    	clip.start();
	    	while(!songover) {
	    		try {
	    			Thread.sleep(1000);
	    		}catch(InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	}
			
		}
	}
	// stops the song playing, running is supposed to kill the loop but clip.stop does
	public void stopThread() {
		running = false;
		songover = true;
		clip.stop();
	}
}
