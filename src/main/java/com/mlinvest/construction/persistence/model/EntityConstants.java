package com.mlinvest.construction.persistence.model;

public class EntityConstants {
    public static class Issuer {
        public static final int MIN_NAME_LENGTH = 5;
        public static final int MAX_NAME_LENGTH = 30;
    }

    public static class Bidder {
        public static final int MIN_NAME_LENGTH = 5;
        public static final int MAX_NAME_LENGTH = 30;
    }

    public static class Tender {
        // for testing purposes allow small desc length
        public static final int MIN_NAME_LENGTH = 5;
        public static final int MAX_NAME_LENGTH = 2000;
    }

    public static class Offer {
        // same as tender, for testing purposes allow small desc length
        public static final int MIN_NAME_LENGTH = 5;
        public static final int MAX_NAME_LENGTH = 2000;
    }
}
