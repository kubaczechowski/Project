package com.jc.project.dal;

import com.jc.project.be.Recording;
import com.jc.project.be.Word;
import com.jc.project.dal.Interfaces.IMusicApiDto;
import com.jc.project.dal.Interfaces.IRandomWordsDAO;
import com.jc.project.dal.MusicApi.MusicApiDAO;
import com.jc.project.dal.RandomWordsApi.RandomWordsDAO;
import com.jc.project.dal.exception.DALException;

import java.util.List;
import java.util.Map;


public class DALFacade implements IDALFacade{
    private final IRandomWordsDAO randomWordsDAO;
    private final IMusicApiDto musicApiDto;

    public DALFacade() throws DALException {
        randomWordsDAO = new RandomWordsDAO();
        musicApiDto = new MusicApiDAO();
    }

    @Override
    public List<Word> getRandomWords(int noOfWordsToPull) throws DALException {
        return randomWordsDAO.getRandomWordsAsList(noOfWordsToPull);
    }

    @Override
    public Map<Word, Recording> getRecordings(List<Word> words) throws DALException {
        return musicApiDto.getRecordings(words);
    }
}
