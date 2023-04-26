package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	

	public void settings() {
		//size(1000, 1000);
		fullScreen(SPAN);
	}

    String[] sonnet;

    public String[] writeSonnet()
    {
        return null;
    }

	public void setup() {
		colorMode(HSB);

       
	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
        
	}
	class Follow {
		private String word;
		private int count;
	
		public Follow(String word) {
			this.word = word;
			this.count = 1;
		}
	
		public String getWord() {
			return this.word;
		}
	
		public int getCount() {
			return this.count;
		}
	
		public void incrementCount() {
			this.count++;
		}
	
		public String toString() {
			return word + "(" + count + ")";
		}
	}
	class Word {
		private String word;
		private ArrayList<Follow> follows;
	
		public Word(String word) {
			this.word = word;
			this.follows = new ArrayList<Follow>();
		}
	
		public String getWord() {
			return this.word;
		}
	
		public ArrayList<Follow> getFollows() {
			return this.follows;
		}
	
		public void addFollow(String word) {
			for (Follow follow : follows) {
				if (follow.getWord().equals(word)) {
					follow.incrementCount();
					return;
				}
			}
			follows.add(new Follow(word));
		}
	
		public String toString() {
			String str = word + ": ";
			for (Follow follow : follows) {
				str += follow.toString() + " ";
			}
			return str;
		}
	}
}