package com.mute.winter.winterkit.events;

/**
 * @author dkoller
 * @since 13.04.2017
 */

public class AnalyticsEvent {

    private String category;
    private String action;
    private String label;
    private long value;

    private AnalyticsEvent(Builder builder){
        this.category = builder.category;
        this.action = builder.action;
        this.label = builder.label;
        this.value = builder.value;
    }

    public String getCategory() {
        return category;
    }

    public String getAction() {
        return action;
    }

    public String getLabel() {
        return label;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CATEGORY: " +category
                +"\nACTION: " +action
                +"\nLABEL: " +label
                +"\nVALUE: " +String.valueOf(value);
    }

    public static class Builder{

        private String category;
        private String action;
        private String label;
        private long value;

        public Builder category(String category){
            this.category = category;
            return this;
        }

        public Builder action(String action){
            this.action = action;
            return this;
        }

        public Builder label(String label){
            this.label = label;
            return this;
        }

        public Builder value(long value){
            this.value = value;
            return this;
        }

        public AnalyticsEvent build(){
            return new AnalyticsEvent(this);
        }

    }
}
