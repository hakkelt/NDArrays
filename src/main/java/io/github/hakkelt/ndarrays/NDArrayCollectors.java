package io.github.hakkelt.ndarrays;

import org.apache.commons.math3.complex.Complex;

/**
 * Collectors that produce NDArrays
 */
public class NDArrayCollectors {

    private NDArrayCollectors() {}

    static class ComplexF32 extends Complex {
        public ComplexF32(float real) {
            super(real);
        }
        public ComplexF32(float real, float imag) {
            super(real, imag);
        }
    }
    static class ComplexF64 extends Complex {
        public ComplexF64(double real) {
            super(real);
        }
        public ComplexF64(double real, double imag) {
            super(real, imag);
        }
    }

    /**
     * Collect streaming values to a ComplexF32NDArray
     * 
     * @param dims dimensionality of the resulting array
     * @return a ComplexF32NDArray that is filled with the streamed values
     */
    public static ComplexNDArrayCollector toComplexF32NDArray(int... dims) {
        return new ComplexNDArrayCollector(ComplexF32.class, dims);
    }

    /**
     * Collect streaming values to a ComplexF64NDArray
     * 
     * @param dims dimensionality of the resulting array
     * @return a ComplexF64NDArray that is filled with the streamed values
     */
    public static ComplexNDArrayCollector toComplexF64NDArray(int... dims) {
        return new ComplexNDArrayCollector(ComplexF64.class, dims);
    }

    /**
     * Collect streaming values to a RealF32NDArray
     * 
     * @param dims dimensionality of the resulting array
     * @return a RealF32NDArray that is filled with the streamed values
     */
    public static RealF32NDArrayCollector toRealF32NDArray(int... dims) {
        return new RealF32NDArrayCollector(Float.class, dims);
    }

    /**
     * Collect streaming values to a toRealF64NDArray
     * 
     * @param dims dimensionality of the resulting array
     * @return a toRealF64NDArray that is filled with the streamed values
     */
    public static RealF64NDArrayCollector toRealF64NDArray(int... dims) {
        return new RealF64NDArrayCollector(Double.class, dims);
    }
    
}
