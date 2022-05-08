package com.jc.project.be;

public class Word implements Comparable<Word>{
    private final String word;
    private final String definition;
    private final String pronunciation;

    public Word(String word, String definition, String pronunciation) {
        this.word = word;
        this.definition = definition;
        this.pronunciation = pronunciation;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public int compareTo(Word o) {
        return this.getWord().compareTo(o.getWord());
    }
}
