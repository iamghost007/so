package net.bobstudio.so.dto;

public enum Status {
    
    ENABLE("有效"), DISABLE("无效");

    private String description;
    
    Status(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String toString(){
    	return this.description;
    }
    
}