package edu.auburn.eng.csse.comp3710.DZT0021.midterm18;

/**
 * Created by donaldtran on 3/15/18.
 */

import java.util.ArrayList;
import android.util.Pair;
import java.util.List;

public class HaikuModel extends MainHaikuActivity {
    private List<Pair<String, Integer>> phrase1pair;
    private List<Pair<String, Integer>> phrase2pair;
    private List<Pair<String, Integer>> phrase3pair;

    public HaikuModel() {
        phrase1pair = new ArrayList<>();
        phrase2pair = new ArrayList<>();
        phrase3pair = new ArrayList<>();
    }

    public void reset() {
        phrase1pair.clear();
        phrase2pair.clear();
        phrase3pair.clear();
    }

    protected List<Pair<String, Integer>> getP1Pair() {
        return phrase1pair;
    }

    protected void setPhrase1pair(List<Pair<String, Integer>> in) {
        phrase1pair = in;
    }

    protected List<Pair<String, Integer>> getP2Pair() {
        return phrase2pair;
    }

    protected void setPhrase2pair(List<Pair<String, Integer>> in) {
        phrase2pair = in;
    }

    protected List<Pair<String, Integer>> getP3Pair() {
        return phrase3pair;
    }

    protected void setPhrase3pair(List<Pair<String, Integer>> in) {
        phrase3pair = in;
    }

    public int getphraseOneSyllableCount() {
        int val = 0;
        for(Pair<String, Integer> p: phrase1pair) {
            val += p.second;
        }
        return val;
    }

    public int getPhraseOneSize() {
        int val = 0;
        for(Pair<String, Integer> p: phrase1pair) {
            val ++;
        }
        return val;
    }

    public int getphraseTwoSyllableCount() {
        int val = 0;
        for(Pair<String, Integer> p: phrase2pair) {
            val += p.second;
        }
        return val;
    }

    public int getPhraseTwoSize() {
        int val = 0;
        for(Pair<String, Integer> p: phrase2pair) {
            val ++;
        }
        return val;
    }

    public int getphraseThreeSyllableCount() {
        int val = 0;
        for(Pair<String, Integer> p: phrase3pair) {
            val += p.second;
        }
        return val;
    }

    public int getPhraseThreeSize() {
        int val = 0;
        for(Pair<String, Integer> p: phrase3pair) {
            val ++;
        }
        return val;
    }

    public String getPhraseOneAsString() {
        StringBuilder sb = new StringBuilder();
        for(Pair<String, Integer> p: phrase1pair) {
            sb.append(p.first);
            sb.append(" ");
        }
        return sb.toString();
    }

    public ArrayList<String> getPhraseOneAsList() {
        ArrayList<String> pal = new ArrayList<>();
        for (Pair<String, Integer> p: phrase1pair) {
            pal.add(p.first);
        }
        return pal;

    }

    public boolean removeFromPhraseOne() {
        if(phrase1pair.size() > 0) {
            phrase1pair.remove(phrase1pair.size() - 1);
            return true;
        }
        return false;
    }

    public boolean addToPhraseOne(String word) {
        int countS = Integer.parseInt(word.substring(0, 1));
        int cmp = getphraseOneSyllableCount();
        int len = word.length();
        if(countS + cmp <= 5) {
            phrase1pair.add(new Pair<>(word.substring(1, len), countS));
            return true;
        }
        return false;
    }

    public String getPhraseTwoAsString() {
        StringBuilder sb = new StringBuilder();
        for(Pair<String, Integer> p: phrase2pair) {
            sb.append(p.first);
            sb.append(" ");
        }
        return sb.toString();
    }

    public ArrayList<String> getPhraseTwoAsList() {
        ArrayList<String> pal = new ArrayList<>();
        for (Pair<String, Integer> p: phrase2pair) {
            pal.add(p.first);
        }
        return pal;
    }

    public boolean removeFromPhraseTwo() {
        if(phrase2pair.size() > 0) {
            phrase2pair.remove(phrase2pair.size() - 1);
            return true;
        }
        return false;
    }

    public boolean addToPhraseTwo(String word) {
        int countS = Integer.parseInt(word.substring(0, 1));
        int cmp = getphraseTwoSyllableCount();
        int len = word.length();
        if(countS + cmp <= 7) {
            phrase2pair.add(new Pair<>(word.substring(1, len), countS));
            return true;
        }
        return false;
    }

    public String getPhraseThreeAsString() {
        StringBuilder sb = new StringBuilder();
        for(Pair<String, Integer> p: phrase3pair) {
            sb.append(p.first);
            sb.append(" ");
        }
        return sb.toString();
    }

    public ArrayList<String> getPhraseThreeAsList() {
        ArrayList<String> pal = new ArrayList<>();
        for (Pair<String, Integer> p: phrase3pair) {
            pal.add(p.first);
        }
        return pal;
    }

    public boolean removeFromPhraseThree() {
        if(phrase3pair.size() > 0) {
            phrase3pair.remove(phrase3pair.size() - 1);
            return true;
        }
        return false;
    }

    public boolean addToPhraseThree(String word) {
        int countS = Integer.parseInt(word.substring(0, 1));
        int cmp = getphraseThreeSyllableCount();
        int len = word.length();
        if(countS + cmp <= 5) {
            phrase3pair.add(new Pair<>(word.substring(1, len), countS));
            return true;
        }
        return false;
    }
}
