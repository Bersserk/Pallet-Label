package com.developer.bers.domain.repositories;

import com.developer.bers.data.RowComponent;
import com.developer.bers.presentation.surfaces.ListRowsBuilder;

public interface SaveNewDocumentRepository {
    void saveDoc(ListRowsBuilder<RowComponent> listRows);
}
