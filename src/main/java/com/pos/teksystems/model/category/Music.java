package com.pos.teksystems.model.category;

import com.pos.teksystems.model.category.base.OnlySalesTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class Music extends OnlySalesTaxableCategory {

    @Getter
    private NAME name = NAME.MUSIC;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return name == music.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
