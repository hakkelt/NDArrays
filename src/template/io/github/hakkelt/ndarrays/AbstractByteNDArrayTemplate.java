package io.github.hakkelt.ndarrays;

import io.github.hakkelt.generator.*;

import io.github.hakkelt.ndarrays.internal.AbstractRealNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

/**
 * Abstract NDArray class for byte (8-bit integer) values.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays", newName = "Abstract$2NDArray")
@Patterns({ "/byte/", "/Byte/", "8 bit integer" })
@Replacements({ "short", "Short", "16 bit integer" })
@Replacements({ "int", "Integer", "32 bit integer" })
@Replacements({ "long", "Long", "64 bit integer" })
@Replacements({ "float", "Float", "single precision, 32 bit floating-point" })
@Replacements({ "double", "Double", "double precision, 64 bit floating-point" })
@Replacements({ "float", "Float", "single precision, 32 bit floating-point" })
@Replacements({ "double", "Double", "double precision, 64 bit floating-point" })
public abstract class AbstractByteNDArrayTemplate extends AbstractRealNDArray<Byte> {

    protected AbstractByteNDArrayTemplate() {
    }

    @Override
    protected Byte zeroT() {
        return Byte.valueOf((byte) 0);
    }

    @Override
    protected Byte oneT() {
        return Byte.valueOf((byte) 1);
    }

    @Override
    protected Byte wrapValue(Number value) {
        return Byte.valueOf(value.byteValue());
    }

    @Override
    protected Byte wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Byte.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value.byteValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.byteValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Byte::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    @Override
    protected Byte accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Byte acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}