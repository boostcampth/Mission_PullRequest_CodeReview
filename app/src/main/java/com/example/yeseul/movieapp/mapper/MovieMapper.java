package com.example.yeseul.movieapp.mapper;

import java.util.HashMap;
import java.util.Map;

public class MovieMapper {

    public static final String QUERY_STRING_SEARCH_KEY = "query";
    public static final String QUERY_STRING_PAGE_UNIT = "display";
    public static final String QUERY_STRING_START = "start";
    public static final String QUERY_STRING_GENRE = "genre";

    public static Map<String, String> toRequest(String searchKey, int pageUnit, int start, int genre){

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_STRING_SEARCH_KEY, searchKey);
        queryMap.put(QUERY_STRING_PAGE_UNIT, ""+pageUnit);
        queryMap.put(QUERY_STRING_START, ""+start);
        queryMap.put(QUERY_STRING_GENRE, ""+genre);

        return queryMap;
    }

}
