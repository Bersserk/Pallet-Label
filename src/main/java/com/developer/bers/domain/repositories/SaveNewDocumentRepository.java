package com.developer.bers.domain.repositories;

import com.developer.bers.presentation.surfaces.CustomRow;
import com.developer.bers.presentation.surfaces.ListRows;

public interface SaveNewDocumentRepository {
    void saveDoc(ListRows<CustomRow> listRows);
}
