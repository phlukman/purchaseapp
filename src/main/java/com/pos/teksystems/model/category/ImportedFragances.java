package com.pos.teksystems.model.category;

import com.pos.teksystems.model.category.base.ImportAndSalesTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class ImportedFragances extends ImportAndSalesTaxableCategory {

    @Getter
    private NAME name = NAME.IMPORTED_FRAGANCES;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportedFragances that = (ImportedFragances) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
