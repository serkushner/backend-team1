package com.exadel.project.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperUtil {
    public static <T> List<String> getStrings(List<T> sourceList, Function<T, String> function) {
        return Optional.ofNullable(sourceList)
                .stream()
                .flatMap(Collection::stream)
                .map(function)
                .collect(Collectors.toList());
    }
}
