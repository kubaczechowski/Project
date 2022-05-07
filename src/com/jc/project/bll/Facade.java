package com.jc.project.bll;

import com.jc.project.be.Word;
import com.jc.project.bll.exception.BLLException;
import com.jc.project.bll.validations.Validator;
import com.jc.project.dal.DALFacade;
import com.jc.project.dal.IDALFacade;
import com.jc.project.dal.exception.DALException;

import java.util.List;

public class Facade implements IFacade{
    private final IDALFacade dalFacade;
    private final Validator validator;

    public Facade() throws BLLException {
        this.validator = new Validator();
        try {
            dalFacade = new DALFacade();
        } catch (DALException e) {
            throw new BLLException("Couldn't connect to words API ..");
        }
    }

    @Override
    public boolean inputNumberIsInTheRange(int value) {
        return validator.inputNumberIsInTheRange(value);
    }

    @Override
    public List<Word> getRandomWords(int noOfWordsToPull) throws BLLException {
        try {
            return dalFacade.getRandomWords(noOfWordsToPull);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }
}
