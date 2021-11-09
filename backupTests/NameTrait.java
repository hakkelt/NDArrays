package io.github.hakkelt.ndarrays.backup;

interface NameTrait {

    public default String getNamePrefix() {
        return "basic";
    }

}
