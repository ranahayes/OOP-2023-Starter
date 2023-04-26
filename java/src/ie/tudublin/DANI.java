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

	public void setup() {
		colorMode(HSB);
        loadFile("shakespere.txt");
		printModel();
    }


	public void keyPressed() {
		if (key == ' ') {
			sonnet = writeSonnet();
			for (int i = 0; i < sonnet.length; i++) {
				System.out.println(sonnet[i]);
			}
			redraw();
		}
	}


	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
		textAlign(CENTER, CENTER);
		if (sonnet != null) {
			float x = width / 2;
			float y = height / 2;
			float lineHeight = textAscent() + textDescent();
			for (int i = 0; i < sonnet.length; i++) {
				text(sonnet[i], x, y);
				y += lineHeight;
			}
		}
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

	public void printModel() {
        for (Word word : model) {
            System.out.print(word.getWord() + ": ");
            for (Follow follow : word.getFollows()) {
                System.out.print(follow.getWord() + "(" + follow.getCount() + ") ");
            }
            System.out.println();
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
	public String[] writeSonnet() {
        String[] sonnet = new String[14];
        for (int i = 0; i < sonnet.length; i++) {
            String line = "";
            Word currentWord = model.get((int) random(model.size()));
            line += currentWord.getWord();
            for (int j = 0; j < 7; j++) {
                ArrayList<Follow> follows = currentWord.getFollows();
                if (follows.size() == 0) {
                    break;
                }
                Follow follow = follows.get((int) random(follows.size()));
                line += " " + follow.getWord();
                currentWord = findWord(follow.getWord());
            }
            sonnet[i] = line;
        }
        return sonnet;
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