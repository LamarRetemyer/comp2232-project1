package p2.enums;

public enum RSStatus {   
        OPEN("The station is open"),
        CLOSED("The station is closed");

        private String description;   
        
        RSStatus(String description) {
                this.description = description;
            }
            public String getDescription() {
                return description;
            }
}
