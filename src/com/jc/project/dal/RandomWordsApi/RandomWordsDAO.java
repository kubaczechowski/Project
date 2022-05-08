package com.jc.project.dal.RandomWordsApi;

import com.jc.project.be.Word;
import com.jc.project.dal.Interfaces.IRandomWordsDAO;
import com.jc.project.dal.RandomWordsApi.converter.ConvertStringToObject;
import com.jc.project.dal.exception.DALException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RandomWordsDAO implements IRandomWordsDAO {
    private final static int CONNECTION_TIMEOUT =2_000;
    private final static int READ_TIMEOUT =3_000;
    private static final String URL = "https://random-words-api.vercel.app/word";
    private final URL url;
    private final ConvertStringToObject convertStringToObject;
    public RandomWordsDAO() throws DALException {
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            throw new DALException();
        }
        convertStringToObject = new ConvertStringToObject();
    }

    /**
     * We will get Random words and convert Json to Objects
     * @param numberOfWords - number of words we want to pull
     * @return List<Word> we return a list of words pulled from the API
     * @throws DALException - custom DAL exception that latter is caught and wrapped into another exception
     */
    @Override
    public List<Word> getRandomWordsAsList(int numberOfWords) throws DALException {
        List<Word> wordsList = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            var wordAsString = getWordAsString();
            var word = convertStringToObject.convertStringToWord(wordAsString);
            if(wordsList.contains(word)){
                var wordAsString2 =  getWordAsString();
                var word2 = convertStringToObject.convertStringToWord(wordAsString2);
                while (wordsList.contains(word2)){
                    wordAsString2 =  getWordAsString();
                    word2 = convertStringToObject.convertStringToWord(wordAsString2);
                }
                wordsList.add(word2);
            }else
                wordsList.add(word);
        }
        return wordsList;
    }



    private String getWordAsString() throws DALException {
        StringBuilder stringBuilder = new StringBuilder();
        int responseCode = 0;
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connection is made
            responseCode = conn.getResponseCode();

            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            //Close the scanner
            scanner.close();

        } catch (IOException e) {
            if (responseCode!= 200)
                throw new DALException("Problem is probably on the server side." + " Response code: " + responseCode);
            else
                throw new DALException("Couldn't retrieve data from a server");
        }
        return stringBuilder.toString();
    }
}
