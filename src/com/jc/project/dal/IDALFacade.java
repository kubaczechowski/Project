package com.jc.project.dal;

import com.jc.project.be.Recording;
import com.jc.project.be.Word;
import com.jc.project.dal.exception.DALException;

import java.util.List;
import java.util.Map;

public interface IDALFacade {
    List<Word> getRandomWords(int noOfWordsToPull) throws DALException;

    Map<Word, Recording> getRecordings(List<Word> words) throws DALException;
}
