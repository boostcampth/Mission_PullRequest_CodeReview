package com.example.yeseul.movieapp.view.custom;

import java.util.HashMap;

public class FilterView {

    static HashMap<String,String> genreMap = null;
    static HashMap<String,String> countryMap = null;

    public synchronized static HashMap<String,String> getGenreMap() {

        if( genreMap == null) {

            genreMap = new HashMap<>();

            genreMap.put("전체", "0");
            genreMap.put("드라마", "1");
            genreMap.put("판타지", "2");
            genreMap.put("서부", "3");
            genreMap.put("공포", "4");
            genreMap.put("로맨스", "5");
            genreMap.put("모험", "6");
            genreMap.put("스릴러", "7");
            genreMap.put("느와르", "8");
            genreMap.put("컬트", "9");
            genreMap.put("다큐멘터리", "10");
            genreMap.put("코미디", "11");
            genreMap.put("가족", "12");
            genreMap.put("미스터리", "13");
            genreMap.put("전쟁", "14");
            genreMap.put("애니메이션", "15");
            genreMap.put("범죄", "16");
            genreMap.put("뮤지컬", "17");
            genreMap.put("SF", "18");
            genreMap.put("액션", "19");
            genreMap.put("무협", "20");
            genreMap.put("에로", "21");
            genreMap.put("서스펜스", "22");
            genreMap.put("서사", "23");
            genreMap.put("블랙코미디", "24");
            genreMap.put("실험", "25");
            genreMap.put("영화카툰", "26");
            genreMap.put("영화음악", "27");
            genreMap.put("영화패러디포스터", "28");

        }

        return genreMap;
    }


    public synchronized static HashMap<String,String> getCountryMap() {

        if( countryMap == null) {

            countryMap = new HashMap<>();

            countryMap.put("전체", "");
            countryMap.put("한국", "KR");
            countryMap.put("일본", "JP");
            countryMap.put("미국", "US");
            countryMap.put("홍콩", "HK");
            countryMap.put("영국", "GB");
            countryMap.put("프랑스", "FR");
            countryMap.put("기타", "ETC");

        }

        return countryMap;
    }

}
