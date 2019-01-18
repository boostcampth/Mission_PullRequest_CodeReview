package com.example.yeseul.movieapp.mapper;

import java.util.HashMap;
import java.util.Map;

public class MovieMapper {

    public static final String QUERY_STRING_SEARCH_KEY = "query";
    public static final String QUERY_STRING_PAGE_UNIT = "display";
    public static final String QUERY_STRING_START = "start";
    public static final String QUERY_STRING_YEAR_FROM="yearFrom";
    public static final String QUERY_STRING_YEAR_TO="yearTo";

    public static Map<String, String> toRequest(String searchKey, int pageUnit, int start,int yearFrom, int yearTo){

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_STRING_SEARCH_KEY, searchKey);
        queryMap.put(QUERY_STRING_PAGE_UNIT, ""+pageUnit);
        queryMap.put(QUERY_STRING_START, ""+start);
        if(yearFrom!=0&&yearTo!=0) {
            queryMap.put(QUERY_STRING_YEAR_FROM,""+yearFrom);
            queryMap.put(QUERY_STRING_YEAR_TO,""+yearTo);
        }
        return queryMap;
    }

}
