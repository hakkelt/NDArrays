# General Multi-dimensional arrays

[![Documentation](https://img.shields.io/badge/Documentation-latest-blue)](https://hakkelt.github.io/NDArrays/)

Multi-dimensional arrays holding either complex or real values.

## Goals
 1. Provide an easy way to handle multidimensional data,
 2. Make it easy to pass data to [BART](https://github.com/mrirecon/bart) functions through a FloatBuffer object (it might be used in other projects that requires FloatBuffers to pass arrays to JNI function calls)

## Distinctive features
 - Can be parametrized with Float, Double and Complex from org.apache.commons.math3 package
 - Native Java code
 - The underlying data container (FloatBuffer) can be passed to JNI function calls without copying data

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
NDArray<Complex> array = new ComplexF64NDArray(real, imag);

array.slice("2:3", ":", 1).fill(2); // updates the selected range in the original array
NDArray<Complex> sum = array.sum(2); // Calculates sum along axis 2, producing a 4 x 5 NDarray
array.streamCartesianIndices().forEach(idx -> { // Assign zero to elements where the first coordinate is an even number
    if (idx[0] % 2 == 0)
        array.set(0, idx);
});
array.addInplace(new Complex(1,3.5)); // Adds 1 + 3.5i to all elements
```

For more examples, see tests in [src/test/java/com/github/hakkelt/ndarrays](https://github.com/hakkelt/NDArrays/tree/main/src/test/java/io/github/hakkelt/ndarrays).  
For a complete list of features, please refer to the [documentation](https://hakkelt.github.io/NDArrays/).

## Installation

### Maven
```
<dependencies>
    <dependency>
        <groupId>io.github.hakkelt</groupId>
        <artifactId>ndarrays</artifactId>
        <version>1.0</version>
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
