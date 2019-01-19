package com.example.yeseul.movieapp.mapper;

import java.util.HashMap;
import java.util.Map;

public class MovieMapper {

    public static final String QUERY_STRING_SEARCH_KEY = "query";
    public static final String QUERY_STRING_PAGE_UNIT = "display";
    public static final String QUERY_STRING_START = "start";

    public static final String QUERY_STRING_GENRE = "genre";
    public static final String QUERY_STRING_COUNTRY = "country";

    public static Map<String, String> toRequest(String searchKey, int pageUnit, int start, String genre, String country){

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_STRING_SEARCH_KEY, searchKey);
        queryMap.put(QUERY_STRING_PAGE_UNIT, ""+pageUnit);
        queryMap.put(QUERY_STRING_START, ""+start);

        if( genre != null && !genre.equals("0") && !genre.equals("")){
            queryMap.put(QUERY_STRING_GENRE, genre);
        }

        if( country != null && !country.equals("ALL") && !country.equals("")){
            queryMap.put(QUERY_STRING_COUNTRY, country);
        }

        return queryMap;
    }

}
