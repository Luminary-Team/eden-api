package com.luminary.apieden.model.client;

public class CreateRelationshipResponse {
    private Result result;
    private String status;

    private static class Result {
        private Purchaser purchaser;
    }

    private static class Purchaser {
        private long id;
        private String name;
    }
}
