package com.pos.teksystems.model.category;

import com.pos.teksystems.model.category.base.OnlySalesTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class Fragance extends OnlySalesTaxableCategory {

    @Getter
    private NAME name = NAME.FRAGANCES;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fragance fragance = (Fragance) o;
        return name == fragance.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
