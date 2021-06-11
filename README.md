# Java Interface for BART

N-dimensional arrays holding either complex or real values.

This interface can be parametrized with the following types: Float, Double, and Complex from org.apache.commons.math3.complex.

Goals:
 1. Provide an easy way to handle multidimensional data,
 2. Make it easy to pass data to JNI functions through a FloatBuffer object.
 3. Add convenience functions to handle dimensions in a way they are used in [BART](https://github.com/mrirecon/bart).

## Documentation

Run `mvn javadoc:javadoc` and open `docs/index.html`

Examples: See tests in src/test/java/com/github/hakkelt/ndarrays.
