package com.example.ionutcristian.seriesadicted;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by IonutCristian on 6/1/2015.
 */
public class SeriesContract {

    public static final String CONTENT_AUTORITY = "com.example.ionutcristian.seriesadicted";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTORITY);

    public static final String PATH_POPULARS = "populars";
    public static final String PATH_DETAIL = "details";


    public static final class PopularsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POPULARS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTORITY + "/" + PATH_POPULARS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTORITY + "/" + PATH_POPULARS;

        public static final String TABLE_NAME = "populars";

        public static final String COLUMN_ID_DETAIL = "id_detail";

        public static final String COLUMN_SERIES_TITLE = "name";

        public static final String COLUMN_SHORT_DESC = "short_desc";

        public static final String COLUMN_STATUS = "status";

        public static final String COLUMN_CKECK = "check";

        public static Uri buildPopularUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class DetailEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_DETAIL).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTORITY + "/" + PATH_DETAIL;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTORITY + "/" + PATH_DETAIL;

        public static final String TABLE_NAME = "details";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_LONG_DESC = "long_desc";

        public static final String COLUMN_GENRE = "genre";

        public static final String COLUMN_COMPANY = "comp";

        public static final String COLUMN_GRADE = "grade";

        public static final String COLUMN_LAST_EPISODE = "last_ep";

        public static final String COLUMN_NEXT_EPISODE = "next_ep";

        public static final String COLUMN_IMDB = "imdb";

        public static Uri buildDetailUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
