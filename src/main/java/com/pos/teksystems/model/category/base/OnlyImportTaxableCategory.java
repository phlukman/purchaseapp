package com.pos.teksystems.model.category.base;

public abstract class OnlyImportTaxableCategory implements Category {


    @Override
    public boolean isImportTaxable() {
        return true;
    }

    @Override
    public boolean isSalesTaxable() {
        return false;
    }


}
