package com.jc.project.dal;

import com.jc.project.be.Word;
import com.jc.project.dal.Interfaces.IRandomWordsDAO;
import com.jc.project.dal.RandomWordsApi.RandomWordsDAO;
import com.jc.project.dal.exception.DALException;

import java.util.List;


public class DALFacade implements IDALFacade{
    private final IRandomWordsDAO randomWordsDAO;

    public DALFacade() throws DALException {
        randomWordsDAO = new RandomWordsDAO();
    }

    @Override
    public List<Word> getRandomWords(int noOfWordsToPull) throws DALException {
        return randomWordsDAO.getRandomWordsAsList(noOfWordsToPull);
    }
}
