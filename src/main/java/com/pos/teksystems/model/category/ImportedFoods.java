package com.pos.teksystems.model.category;

import com.pos.teksystems.model.category.base.OnlyImportTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class ImportedFoods extends OnlyImportTaxableCategory {

    @Getter
    private NAME name = NAME.IMPORTED_FOODS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportedFoods that = (ImportedFoods) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
