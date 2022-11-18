# General Multi-dimensional arrays

[![Documentation](https://img.shields.io/badge/Documentation-latest-blue)](https://hakkelt.github.io/NDArrays/)
[![Tutorial](https://img.shields.io/badge/Tutorial-Description%20%2B%20examples-brightgreen)](https://hakkelt.github.io/NDArrays/tutorial/NDArrays.html)

General framework for multi-dimensional arrays holding either complex, floating point and integer values.

## Goals
 1. Provide an easy way to create minimal wrappers for n-dimensinal array implementations,
 2. Give a rich interface to query and modify data, and
 3. Allow effortless conversion between wrapped data structures.

## Features
 - Can be parametrized with Float, Double, Byte, Short, Integer, Long, BigInteger, BigDecimal, and Complex from org.apache.commons.math3 package
 - Easy initialization: copying data from (multi-dimensional) arrays of primitive values or NDArrays of any kind, and also filling with a scalar value
 - Lightweight views: slice (select a specific slice or range of slices along arbitrary dimension), permuteDims (change order of indexing), reshape (change shape of data)
 - Basic linear algebra operations: add, subtract, multiply, and divide an NDArray with scalar or another NDArray (element-wise)
 - Implements streaming and iterations of values or indices
 - Implemented fully in native Java

## Examples

```java
ComplexNDArray<Double> array = new ComplexDoubleNDArray(4, 5, 3)
    .fillUsingLinearIndices(i -> new Complex(Math.random(), Math.random()));

array.slice("2:3", ":", 1).fill(2); // updates the selected range in the original array
ComplexNDArray<Double> sum = array.sum(2); // Calculates sum along axis 2, producing a 4 x 5 NDarray

// Euclidean norm of element-wise product of two slices:
array.slice("0:2", ":", 1).multiply(array.slice("2:4", ":", 1)).norm();

// Assign zero to elements where the first coordinate is an even number:
array.streamCartesianIndices() 
    .filter(idx -> idx[0] % 2 == 0)
    .forEach(idx -> array.set(0, idx));
// Alternative solution:
array.applyWithCartesianIndices((value, idx) -> idx[0] % 2 == 0 ? 0 : value);

array.addInplace(new Complex(1, 3.5)); // Adds 1 + 3.5i to all elements
```

## Documentation

### Tutorial:

It presents NDArray features through examples, and illustrates how the task would be solved in Matlab, Python and Julia. For better readability, multiple versions is rendered of this tutorial.
 - [All-in-one](https://hakkelt.github.io/NDArrays/tutorial/NDArrays.html): Java vs Matlab vs Python vs Julia
 - [Java-only](https://hakkelt.github.io/NDArrays/tutorial/NDArrays-Java-only.html): Only Java examples
 - [Java+Matlab tutorial](https://hakkelt.github.io/NDArrays/tutorial/NDArrays-Matlab.html): Java and Matlab examples
 - [Java+Python tutorial](https://hakkelt.github.io/NDArrays/tutorial/NDArrays-Python.html): Java and Python examples
 - [Java+Julia tutorial](https://hakkelt.github.io/NDArrays/tutorial/NDArrays-Julia.html): Java and Julia examples

For more examples, see tests in [src/test/java/com/github/hakkelt/ndarrays/basic](https://github.com/hakkelt/NDArrays/tree/main/src/test/java/io/github/hakkelt/ndarrays/basic).

### Javadoc

For a complete list of features, please refer to the [javadoc documentation](https://hakkelt.github.io/NDArrays/).

## Installation

### Maven
```
<dependencies>
    <dependency>
        <groupId>io.github.hakkelt</groupId>
        <artifactId>ndarrays</artifactId>
        <version>2.1.2</version>
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
