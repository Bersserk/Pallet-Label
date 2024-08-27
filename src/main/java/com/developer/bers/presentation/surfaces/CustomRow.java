package com.developer.bers.presentation.surfaces;

import com.developer.bers.domain.models.CustomField;
import com.developer.bers.domain.models.Tab;

public record CustomRow(Tab getTextLabel, CustomField getCustomField) {

    public CustomField getCustomField() {
        return getCustomField;
    }

    public Tab getTextLabel() {
        return getTextLabel;
    }
}
