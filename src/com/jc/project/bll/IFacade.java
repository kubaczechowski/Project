package com.jc.project.bll;

import com.jc.project.be.Word;
import com.jc.project.bll.exception.BLLException;

import java.util.List;

public interface IFacade {
    boolean inputNumberIsInTheRange(int value);

    List<Word> getRandomWords(int noOfWordsToPull) throws BLLException;
}
