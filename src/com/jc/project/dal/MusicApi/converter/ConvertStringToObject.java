package com.jc.project.dal.MusicApi.converter;

import com.jc.project.be.Recording;
import com.jc.project.dal.exception.DALException;
import com.jc.project.dal.utlis.Converter;


public class ConvertStringToObject extends Converter {
    private static final String TITLE  = "\"title\":\"";
    private static final String ARTIST = "\"artist-credit\":[{\"name\":\"";
    private static final String RELEASE = "\"releases\":[";
    private static final String RELEASE_END = "]";



    public Recording convertStringToRecording(String line){
        var recordingTitle = getStringInBetweenCustom(TITLE, END, line);

        var artistName = getStringInBetweenCustom(ARTIST, END, line);

        var stringReleases = getStringInBetweenCustom(RELEASE, RELEASE_END,
                line);
        var albumTitle = getStringInBetweenCustom(TITLE, END, stringReleases);

        return new Recording(artistName, albumTitle, recordingTitle);

    }
    
    public boolean isRecordingFound(String recordingString) throws DALException {
        var recordingStr = getStringInBetweenCustom("\"count\":", ",", recordingString);
        try {
            int no = Integer.parseInt(recordingStr);
            return no !=0;
        }catch (NumberFormatException ex){
            throw new DALException("Couldn't parse int");
        }
    }
}
