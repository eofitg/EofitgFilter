package net.eofitg.eofitgfilter.config;

import java.util.ArrayList;
import java.util.List;

public class EofitgFilterConfig {

    private boolean enabled = true;

    private List<FilterData> filterList = new ArrayList<>();

    public List<FilterData> getFilterList() {
        return filterList;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}