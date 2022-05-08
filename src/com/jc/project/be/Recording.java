package com.jc.project.be;

public class Recording {
    private final String artistName;
    private final String albumName;
    private final String recordingTitle;

    public Recording(String artistName, String albumName, String recordingTitle) {
        this.artistName = artistName;
        this.albumName = albumName;
        this.recordingTitle = recordingTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getRecordingTitle() {
        return recordingTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recording recording = (Recording) o;

        if (!artistName.equals(recording.artistName)) return false;
        if (!albumName.equals(recording.albumName)) return false;
        return recordingTitle.equals(recording.recordingTitle);
    }

    @Override
    public int hashCode() {
        int result = artistName.hashCode();
        result = 31 * result + albumName.hashCode();
        result = 31 * result + recordingTitle.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Recording{" +
                "artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", recordingTitle='" + recordingTitle + '\'' +
                '}';
    }
}
