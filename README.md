# General Multi-dimensional arrays

[![Documentation](https://img.shields.io/badge/Documentation-latest-blue)](https://hakkelt.github.io/NDArrays/)

General framework for multi-dimensional arrays holding either complex, floating point and integer values.

## Goals
 1. Provide an easy way to create minimal wrappers for n-dimensinal array implementations,
 2. Give a rich interface to query and modify data, and
 3. Allow effortless conversion between wrapped data structures.

## Features
 - Can be parametrized with Float, Double, Byte, Short, Integer, Long, and Complex from org.apache.commons.math3 package
 - Easy initialization: copying data from (multi-dimensional) arrays of primitive values or NDArrays of any kind, and also filling with a scalar value
 - Lightweight views: slice (select a specific slice or range of slices along arbitrary dimension), permuteDims (change order of indexing), reshape (change shape of data)
 - Basic linear algebra operations: add, subtract, multiply, and divide an NDArray with scalar or another NDArray (element-wise)
 - Implements streaming and iterations of values or indices
 - Implemented fully in native Java

## Examples

```java
double[][][] real = new float[4][5][3];
double[][][] imag = new float[4][5][3];
for (int i = 0; i < real.length; i++)
    for (int j = 0; j < real[i].length; j++)
        for (int k = 0; k < real[i][j].length; k++) {
            real[i][j][k] = Math.random();
            imag[i][j][k] = Math.random();
        }
ComplexNDArray<Double> array = ComplexDoubleNDArray.of(real, imag);

array.slice("2:3", ":", 1).fill(2); // updates the selected range in the original array
ComplexNDArray<Double> sum = array.sum(2); // Calculates sum along axis 2, producing a 4 x 5 NDarray

array.streamCartesianIndices().forEach(idx -> { // Assign zero to elements where the first coordinate is an even number
    if (idx[0] % 2 == 0)
        array.set(0, idx);
});

// Assign zero to elements where the first coordinate is an even number
array.streamCartesianIndices() 
    .filter(idx -> idx[0] % 2 == 0)
    .forEach(idx -> array.set(0, idx));
array.applyWithCartesianIndices((value, idx) -> idx[0] % 2 == 0 ? 0 : value); // Alternative solution

array.addInplace(new Complex(1,3.5)); // Adds 1 + 3.5i to all elements
```

For more examples, see tests in [src/test/java/com/github/hakkelt/ndarrays/basic](https://github.com/hakkelt/NDArrays/tree/main/src/test/java/io/github/hakkelt/ndarrays/basic).  
For a complete list of features, please refer to the [documentation](https://hakkelt.github.io/NDArrays/).

## Installation

### Maven
```
<dependencies>
    <dependency>
        <groupId>io.github.hakkelt</groupId>
        <artifactId>ndarrays</artifactId>
        <version>2.0</version>
    </dependency>
    ...
</dependencies>
```

### Manual

Download jar files from [latest release](https://github.com/hakkelt/NDArrays/releases/latest), and add their path to java path. You should also take care of the dependencies (`org.apache.commons.commons-math3`, `org.apache.commons.commons-lang3`).

## Similar packages
 - [Vectorz](https://github.com/mikera/vectorz)
 - [DeepJavaLibrary](https://github.com/deepjavalibrary/djl) -> [documentation](https://javadoc.io/doc/ai.djl/api/latest/ai/djl/ndarray/NDArray.html)
 - [Jep - Java Embedded Python](https://github.com/ninia/jep) -> [Numpy Usage](https://github.com/ninia/jep/wiki/Numpy-Usage)

## Inspiration from other languages
 - Python's [numpy.ndarray](https://numpy.org/doc/stable/reference/generated/numpy.ndarray.html)
 - Julia's native [multidimensional array type](https://docs.julialang.org/en/v1/manual/arrays/)
