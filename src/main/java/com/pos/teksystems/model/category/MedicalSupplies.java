package com.pos.teksystems.model.category;

import com.pos.teksystems.model.category.base.ExemptTaxableCategory;
import lombok.Getter;

import java.util.Objects;

public class MedicalSupplies extends ExemptTaxableCategory {

    @Getter
    private NAME name = NAME.MEDICAL_SUPPLIES;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalSupplies that = (MedicalSupplies) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
