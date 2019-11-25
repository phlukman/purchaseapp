package com.pos.teksystems.model.category;

import com.pos.teksystems.model.category.base.ExemptTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class Foods extends ExemptTaxableCategory {

    @Getter
    private NAME name = NAME.FOODS;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foods foods = (Foods) o;
        return name == foods.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
