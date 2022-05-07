package com.jc.project.dal;

import com.jc.project.be.Word;
import com.jc.project.dal.exception.DALException;

import java.util.List;

public interface IDALFacade {
    List<Word> getRandomWords(int noOfWordsToPull) throws DALException;
}
