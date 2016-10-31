package net.bobstudio.so.dto;

public enum Status {
    
    有效("有效"), 无效("无效");

    private String description;
    
    Status(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}