package io.github.hakkelt.ndarrays;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

/**
 * Methods annotated with this annotation are excluded from Jacoco code coverage
 * reports.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@interface Generated {
}

/**
 * Abstract NDArray class for Complex values.
 */
public abstract class AbstractComplexNDArray<T extends Number> extends AbstractNDArray<Complex, T>
        implements ComplexNDArray<T> {

    protected AbstractComplexNDArray() {
    }

    public Class<?> dtype() {
        return Complex.class;
    }

    public Class<?> dtype2() {
        Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        return types[types.length - 1];
    }

    public String dataTypeAsString() {
        String[] str = dtype2().toString().split("\\.");
        return "Complex " + str[str.length - 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        return (ComplexNDArray<T>) super.copy();
    }

    @Override
    public NDArray<T> real() {
        return streamLinearIndices().mapToObj(this::getReal).collect(getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> imaginary() {
        return streamLinearIndices().mapToObj(this::getImag).collect(getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> abs() {
        return streamLinearIndices().mapToObj(i -> get(i).abs()).collect(getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> angle() {
        return streamLinearIndices().mapToObj(i -> get(i).getArgument()).collect(getRealCollectorInternal(shape));
    }

    @Override
    public ComplexNDArray<T> similar() {
        return createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(wrapRealValue(value), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(wrapRealValue(value), indices);
    }

    @Override
    public void setReal(Number value, int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        setRealUnchecked(wrapRealValue(value), IndexingOperations.unwrap(linearIndex, length()));
    }

    @Override
    public void setReal(Number value, int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        setRealUnchecked(wrapRealValue(value), IndexingOperations.unwrap(indices, shape()));
    }

    @Override
    public void setImag(Number value, int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        setImagUnchecked(wrapRealValue(value), IndexingOperations.unwrap(linearIndex, length()));
    }

    @Override
    public void setImag(Number value, int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        setImagUnchecked(wrapRealValue(value), IndexingOperations.unwrap(indices, shape()));
    }

    protected abstract ComplexNDArray<T> createNewNDArrayOfSameTypeAsMe(int... shape);

    protected Complex accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Complex acc = get(linearIndex);
        for (Object item : objects)
            if (item instanceof NDArray<?>)
                acc = accumulate(acc, ((NDArray<?>) item), linearIndex, operator);
            else if (item instanceof Complex)
                acc = accumulate(acc, (Complex) item, operator);
            else
                acc = accumulate(acc, (Number) item, operator);
        return acc;
    }

    @SuppressWarnings("unchecked")
    protected T wrapRealValue(Number value) {
        if (dtype2() == Float.class)
            return (T) Float.valueOf(value.floatValue());
        return (T) Double.valueOf(value.doubleValue());
    }

    protected long countNonZero() {
        return stream().filter(item -> item != Complex.ZERO).count();
    }

    protected Collector<Object, List<Object>, NDArray<Complex>> getCollectorInternal(int[] shape) {
        return new ComplexNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(shape));
    }

    protected String printItem(int index, String format) {
        Complex item = get(index);
        String complexFormat = format + (item.getImaginary() < 0 ? "-" : "+") + format + "i";
        return String.format(complexFormat, item.getReal(), Math.abs(item.getImaginary()));
    }

    @Generated
    protected Complex accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex,
            AbstractNDArray.AccumulateOperators operation) {
        if (array.dtype() == Complex.class)
            return accumulate(acc, (Complex) array.get(linearIndex), operation);
        else if (array.dtype() == Double.class)
            return accumulate(acc, (Double) array.get(linearIndex), operation);
        else
            return accumulate(acc, (Float) array.get(linearIndex), operation);
    }

    @Generated
    protected Complex accumulate(Float acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Double acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Byte acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Short acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Integer acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Long acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Complex accumulate(Complex acc, Complex value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD:
                return acc.add(value);
            case SUBTRACT:
                return acc.subtract(value);
            case MULTIPLY:
                return acc.multiply(value);
            case DIVIDE:
            default:
                return acc.divide(value);
        }
    }

    protected Complex accumulate(Complex acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD:
                return acc.add(value.doubleValue());
            case SUBTRACT:
                return acc.subtract(value.doubleValue());
            case MULTIPLY:
                return acc.multiply(value.doubleValue());
            case DIVIDE:
            default:
                return acc.divide(value.doubleValue());
        }
    }

    protected Complex wrapValue(Object value) {
        if (value instanceof Float)
            return new Complex((Float) value);
        if (value instanceof Double)
            return new Complex((Double) value);
        if (value instanceof Byte)
            return new Complex((Byte) value);
        if (value instanceof Short)
            return new Complex((Short) value);
        if (value instanceof Long)
            return new Complex((Long) value);
        if (value instanceof Integer)
            return new Complex((Integer) value);
        if (value instanceof BigInteger)
            return new Complex((BigInteger) value);
        if (value instanceof BigDecimal)
            return new Complex((BigDecimal) value);
        return (Complex) value;
    }

    @SuppressWarnings("unchecked")
    protected T zeroT2() {
        if (dtype2() == Float.class)
            return (T) Float.valueOf(0.f);
        return (T) Double.valueOf(0.);
    }

    protected Collector<Object, List<Object>, NDArray<T>> getRealCollectorInternal(int[] shape) {
        return new RealNDArrayCollector<>(createNewRealNDArrayOfSameTypeAsMe(shape));
    }

    protected Complex wrapValue(Number value) {
        return new Complex(value.doubleValue());
    }

    protected Complex zeroT() {
        return Complex.ZERO;
    }
}
