package com.jc.project.UI.utils;

import com.jc.project.UI.options.Options;

import java.util.Arrays;

public class OptionsHelper {
    public static Options getOptionFromInt(int choice){
        return Arrays.stream(Options.values()).filter(o -> o.getValue() == choice).findFirst().orElse(null);
    }
}
