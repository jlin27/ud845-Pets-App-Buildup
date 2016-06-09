package com.jessica.ud845_pets_app_buildup;

import android.provider.BaseColumns;

/**
 * Created by jessicalin on 6/2/16.
 */
public class PetContract {

    public static final class PetEntry implements BaseColumns {

        // Add constants to define table and columns
        public final static String TABLE_NAME = "pets";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PET_TYPE = "type";
        public final static String COLUMN_PET_NAME ="name";
        public final static String COLUMN_PET_GENDER = "gender";
        public final static String COLUMN_PET_AGE = "age";
        public final static String COLUMN_PET_BREED = "breed";
        public final static String COLUMN_PET_HEIGHT = "height";
        public final static String COLUMN_PET_WEIGHT = "weight";
        public final static String COLUMN_IMAGE_PATH = "image_path";
        public final static String COLUMN_STERILIZATION_STATUS = "status_status";
        public final static String COLUMN_ADDITIONAL_NOTES = "additional_notes";
        public final static String COLUMN_ADOPTION_STATUS = "adoption_status";

        public final static String[] TABLE_COLUMNS = {
                _ID ,
                COLUMN_PET_NAME ,
                COLUMN_PET_AGE ,
                COLUMN_PET_BREED ,
                };
    }

}
