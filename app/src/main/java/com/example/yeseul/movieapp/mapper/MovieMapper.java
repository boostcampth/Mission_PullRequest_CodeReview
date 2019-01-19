package com.example.yeseul.movieapp.mapper;

import java.util.HashMap;
import java.util.Map;

/*
 *  */
public class MovieMapper {

    /* private 로 접근 제한자 수정 */
    private static final String QUERY_STRING_SEARCH_KEY = "query";
    private static final String QUERY_STRING_PAGE_UNIT = "display";
    private static final String QUERY_STRING_START = "start";

    /*
     * Retrofit 을 이용해 보낼 Query 생성 */
    public static Map<String, String> toRequest(String searchKey, int pageUnit, int start){

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_STRING_SEARCH_KEY, searchKey);
        queryMap.put(QUERY_STRING_PAGE_UNIT, ""+pageUnit);
        queryMap.put(QUERY_STRING_START, ""+start);

        return queryMap;
    }

}
