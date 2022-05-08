package com.jc.project.dal.MusicApi;

import com.jc.project.be.Recording;
import com.jc.project.be.Word;
import com.jc.project.dal.Interfaces.IMusicApiDto;
import com.jc.project.dal.MusicApi.converter.ConvertStringToObject;
import com.jc.project.dal.exception.DALException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
/**
 * I decided to take this naming convention in order
 * to prevent other participant from easily finding this repository by API name
 */
public class MusicApiDAO implements IMusicApiDto {
    private final static int CONNECTION_TIMEOUT =1_000;
    private final static int READ_TIMEOUT =2_000;
    private final ConvertStringToObject converter;
    private static final String URL_BEGIN =
            "https://musicbrainz.org/ws/2/recording/?query=recording:";
    private static final String URL_END = "&fmt=json&limit=1";
    private static final String HEADER = "ProjectKuba/1.0.0 (jcobczechowski@gmail.com)";


    public MusicApiDAO() {
        converter = new ConvertStringToObject();
    }

    /**
     * @param wordsList list of words for each we will search recordings
     * @return Map<Word, Recording> - we program to the interface not
     * concrete implementation. Later we may want to switch from
     * TreeMap to LinkedMap, and it won't be a problem
     */
    @Override
    public Map<Word, Recording> getRecordings(List<Word> wordsList) throws DALException {
        Map<Word, Recording> map = new TreeMap<>(); //put pairs in the sorted order.
        // Word entity implements comparable

        try {
            for (var word : wordsList
                 ) {
                Thread.sleep(1_800);
                var recordingString = getRecordingAsString(word);
                var recordingIsFound = converter.isRecordingFound(recordingString);
                if(recordingIsFound){
                    var record = converter.convertStringToRecording(recordingString);
                    map.put(word, record);
                }else {
                    map.put(word, null);
                }
            }
            return map;

        }  catch (InterruptedException e) {
            return null; //then we just want to return if the app was interrupted or forced to close
        }
    }


    /**
     *
     * The API limit is 1 request / sec. If we don't follow that
     * requirement we may receive 503 status code and later  out IP may be blocked
     * @param word - we will search for a recording based on Word property
     * @return we return found recording as string
     * @throws DALException we use custom exceptions. each layer has its own exception
     */
    public String getRecordingAsString(Word word) throws DALException {
        int responseCode =0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String URL = URL_BEGIN + word.getWord().replace(" ", "") + URL_END;
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestProperty("CustomHeader", HEADER);
            conn.connect();

            //Check if connection is established
            responseCode = conn.getResponseCode();

            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            //Close the scanner
            scanner.close();

            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            throw new DALException("There is a problem with URL");
        } catch (ProtocolException e) {
            throw new DALException("ProtocolException...");
        } catch (IOException e) {
            if (responseCode!= 200)
                throw new DALException("Problem is probably on the server side. " + "Response code: " + responseCode);
            else
                throw new DALException("Couldn't retrieve data from a server");
        }
    }


}
