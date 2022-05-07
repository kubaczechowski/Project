package com.jc.project.be;

public class Word {
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
}
