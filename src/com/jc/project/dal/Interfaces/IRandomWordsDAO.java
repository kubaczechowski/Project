package com.jc.project.dal.Interfaces;

import com.jc.project.be.Word;
import com.jc.project.dal.exception.DALException;

import java.util.List;

public interface IRandomWordsDAO {
    List<Word> getRandomWordsAsList(int numberOfWords) throws DALException;
}
