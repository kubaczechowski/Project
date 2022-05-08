package com.jc.project.dal.Interfaces;

import com.jc.project.be.Recording;
import com.jc.project.be.Word;
import com.jc.project.dal.exception.DALException;

import java.util.List;
import java.util.Map;

public interface IMusicApiDto {
    //By using map we ensure that data structure doesn't have any duplicates
     Map<Word, Recording> getRecordings(List<Word> wordsList) throws DALException;
}
