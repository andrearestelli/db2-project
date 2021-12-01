package it.polimi.db2_project.web.utils;

import java.util.stream.Stream;

public final class ParametersChecker {
    public static boolean discoverInvalidParameters(Stream<String> parameters){
        return parameters.anyMatch(x -> x == null || x.isEmpty());
    }
}
