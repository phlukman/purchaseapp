package com.pos.teksystems.model.category.base;

public abstract class OnlySalesTaxableCategory implements Category {


    @Override
    public boolean isImportTaxable() {
        return false;
    }

    @Override
    public boolean isSalesTaxable() {
        return true;
    }


}
