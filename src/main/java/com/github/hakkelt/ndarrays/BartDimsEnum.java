package com.github.hakkelt.ndarrays;

/**
 * Meaning of dimensions in BART.
 */
public enum BartDimsEnum {
    /** Readout dimension */
    _00_READ,   // NOSONAR
    /** First phase dimension */
    _01_PHS1,   // NOSONAR
    /** Second phase dimension */
    _02_PHS2,   // NOSONAR
    /** Coil images */
    _03_COIL,   // NOSONAR
    /** Sensitivity maps */
    _04_MAPS,   // NOSONAR
    /** Dimension for measurements with different TE value */
    _05_TE,     // NOSONAR
    /** Dimension for coefficients */
    _06_COEFF,  // NOSONAR
    /** Dimension for coefficients */
    _07_COEFF2, // NOSONAR
    /** Dimension for iterations */
    _08_ITER,   // NOSONAR
    /** Chemical shift dimension */
    _09_CSHIFT, // NOSONAR
    /** First time dimension */
    _10_TIME,   // NOSONAR
    /** Second time dimension (eg cardiac cycle) */
    _11_TIME2,  // NOSONAR
    /** Dimension for multilevel low-rank reconstructions */
    _12_LEVEL,  // NOSONAR
    /** Slice dimension in multiplanar mode */
    _13_SLICE,  // NOSONAR
    /** Averaging dimension */
    _14_AVG,    // NOSONAR
    /** Unused dimension */
    _15_UNUSED, // NOSONAR
}
