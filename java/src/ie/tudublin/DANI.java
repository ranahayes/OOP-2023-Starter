package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
	ArrayList<Word> model = new ArrayList<Word>();
    String[] sonnet;

	

	public void settings() {
		//size(1000, 1000);
		fullScreen(SPAN);
	}

    public String[] writeSonnet()
    {
        return null;
    }

	public void setup() {
		colorMode(HSB);
        loadFile("shakespere.txt");
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
	public void loadFile(String filename) {
        String[] lines = loadStrings(filename);
        for (String line : lines) {
            String[] words = split(line, ' ');
            for (int i = 0; i < words.length; i++) {
                String w = words[i].replaceAll("[^\\w\\s]", "").toLowerCase();
                if (w.length() == 0) {
                    continue;
                }
				Word word = findWord(w);
                if (word == null) {
                    word = new Word(w);
                    model.add(word);
                }
                if (i < words.length - 1) {
                    String nextW = words[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();
                    if (nextW.length() == 0) {
                        continue;
                    }
                    word.addFollow(nextW);
                }
            }
        }
    }
	public Word findWord(String str) {
        for (Word word : model) {
            if (word.getWord().equals(str)) {
                return word;
            }
        }
        return null;
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