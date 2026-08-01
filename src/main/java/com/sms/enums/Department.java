package com.sms.enums;

public class Department {
    public enum dept{
        COMPUTER_SCIENCE("Computer Science"),
        MATHEMATICS("Mathematics"),
        PHYSICS("Physics"),
        ELECTRONICS("Electronics"),
        MECHANICAL("Mechanical"),
        ELECTRICAL("Electrical");

        private String displayName;

        dept(String displayName){
            this.displayName = displayName;
        }

        public String getDisplayName(){
            return this.displayName;
        }

        public static dept fromDisplayName(String displayName) {
            for (dept d : values()) {
                if (d.getDisplayName().equalsIgnoreCase(displayName)) {
                    return d;
                }
            }
            throw new IllegalArgumentException("Invalid department: " + displayName);
        }
    }
}