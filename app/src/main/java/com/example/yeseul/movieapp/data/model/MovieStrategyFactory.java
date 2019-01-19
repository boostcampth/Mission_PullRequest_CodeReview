package com.example.yeseul.movieapp.data.model;

import static com.example.yeseul.movieapp.data.model.MovieSortStrategy.*;

public class MovieStrategyFactory {
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
