package com.pos.teksystems.model.category;


import com.pos.teksystems.model.category.base.ExemptTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class Books extends ExemptTaxableCategory {

    @Getter
    private NAME name = NAME.BOOKS;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return name == books.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
