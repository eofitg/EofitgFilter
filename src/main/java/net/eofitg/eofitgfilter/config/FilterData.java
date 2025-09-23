package net.eofitg.eofitgfilter.config;

public class FilterData {
    public String message;
    public boolean regex;

    public FilterData(String message, boolean regex) {
        this.message = message;
        this.regex = regex;
    }
}
