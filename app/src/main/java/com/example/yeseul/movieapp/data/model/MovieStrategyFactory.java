package com.example.yeseul.movieapp.data.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.yeseul.movieapp.data.model.MovieSortStrategy.*;

public class MovieStrategyFactory {

    // Interface 에 있던 상수들을 Factory Class 로 이동
    // 넘어오는 매개변수가 정확한 타입인지 인증하기 위해 IntDef 사용
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DICTIONARY_ORDER, LATEST_ORDER, OLDEST_ORDER, HIGHEST_RATING_ORDER, LOWEST_RATING_ORDER})

    public @interface SortTypeDef {}

    public static final int DICTIONARY_ORDER = 0;
    public static final int LATEST_ORDER = 1;
    public static final int OLDEST_ORDER = 2;
    public static final int HIGHEST_RATING_ORDER = 3;
    public static final int LOWEST_RATING_ORDER = 4;

    public static MovieSortStrategy create(int idx) {

        switch (idx) {
            case DICTIONARY_ORDER:
                return new MovieSortByDictionary();
            case LATEST_ORDER:
                return new MovieSortByLatestPubDate();
            case OLDEST_ORDER:
                return new MovieSortByOldPubDate();
            case HIGHEST_RATING_ORDER:
                return new MovieSortByHighestRating();
            case LOWEST_RATING_ORDER:
                return new MovieSortByLowestRating();
        }
        return null;
    }
}
