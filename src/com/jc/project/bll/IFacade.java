package com.jc.project.bll;

import com.jc.project.be.Recording;
import com.jc.project.be.Word;
import com.jc.project.bll.exception.BLLException;

import java.util.List;
import java.util.Map;

public interface IFacade {
    boolean inputNumberIsInTheRange(int value);

    List<Word> getRandomWords(int noOfWordsToPull) throws BLLException;

    Map<Word, Recording> getRecordings(List<Word> words) throws BLLException;
}
