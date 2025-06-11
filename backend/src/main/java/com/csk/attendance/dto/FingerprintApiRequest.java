package com.csk.attendance.dto;

import lombok.Data;
import java.util.*;

@Data
public class FingerprintApiRequest {
    private Query Query;

    @Data
    public static class Query {
        private int limit;
        private List<Condition> conditions;
        private List<Order> orders;
    }

    @Data
    public static class Condition {
        private String column;
        private int operator;
        private List<String> values;
    }

    @Data
    public static class Order {
        private String column;
        private boolean descending;
    }
}