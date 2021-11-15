/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBase.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;

public class TestBase {

    public void assertSpecializedEquals(Byte lhs, Byte rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(Short lhs, Short rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(Integer lhs, Integer rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(Long lhs, Long rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(Float lhs, Float rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(Double lhs, Double rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(BigInteger lhs, BigInteger rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(BigDecimal lhs, BigDecimal rhs) {
        assertEquals(0, lhs.compareTo(rhs));
    }

    public void assertSpecializedEquals(Complex lhs, Complex rhs) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(Byte lhs, Byte rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    public void assertSpecializedEquals(Short lhs, Short rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    public void assertSpecializedEquals(Integer lhs, Integer rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    public void assertSpecializedEquals(Long lhs, Long rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    public void assertSpecializedEquals(Float lhs, Float rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    public void assertSpecializedEquals(Double lhs, Double rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    public void assertSpecializedEquals(BigInteger lhs, BigInteger rhs, double delta) {
        assertEquals(lhs, rhs);
    }

    public void assertSpecializedEquals(BigDecimal lhs, BigDecimal rhs, double delta) {
        assertEquals(0, lhs.compareTo(rhs), delta);
    }

    public void assertSpecializedEquals(Complex lhs, Complex rhs, double delta) {
        assertEquals(1, lhs.equals(Complex.ZERO) && rhs.equals(Complex.ZERO) ? 1 : lhs.abs() / rhs.abs(), delta);
    }

    public static Byte wrapToByte(double value) {
        return (byte) value;
    }

    public static Short wrapToShort(double value) {
        return (short) value;
    }

    public static Integer wrapToInteger(double value) {
        return (int) value;
    }

    public static Long wrapToLong(double value) {
        return (long) value;
    }

    public static Float wrapToFloat(double value) {
        return (float) value;
    }

    public static Double wrapToDouble(double value) {
        return value;
    }

    public static BigInteger wrapToBigInteger(double value) {
        return BigInteger.valueOf((long) value);
    }

    public static BigDecimal wrapToBigDecimal(double value) {
        return new BigDecimal(value);
    }

    public static Complex wrapToComplex(double value) {
        return new Complex(value);
    }

    public static Double wrapToDouble(BigInteger value) {
        return value.doubleValue();
    }

    public static Double wrapToDouble(BigDecimal value) {
        return value.doubleValue();
    }

    public Byte add(Byte lhs, Byte rhs) {
        return (byte) (lhs + rhs);
    }

    public Byte subtract(Byte lhs, Byte rhs) {
        return (byte) (lhs - rhs);
    }

    public Byte multiply(Byte lhs, Byte rhs) {
        return (byte) (lhs * rhs);
    }

    public Byte divide(Byte lhs, Byte rhs) {
        return (byte) (lhs / rhs);
    }

    public Short add(Short lhs, Short rhs) {
        return (short) (lhs + rhs);
    }

    public Short subtract(Short lhs, Short rhs) {
        return (short) (lhs - rhs);
    }

    public Short multiply(Short lhs, Short rhs) {
        return (short) (lhs * rhs);
    }

    public Short divide(Short lhs, Short rhs) {
        return (short) (lhs / rhs);
    }

    public Integer add(Integer lhs, Integer rhs) {
        return (int) (lhs + rhs);
    }

    public Integer subtract(Integer lhs, Integer rhs) {
        return (int) (lhs - rhs);
    }

    public Integer multiply(Integer lhs, Integer rhs) {
        return (int) (lhs * rhs);
    }

    public Integer divide(Integer lhs, Integer rhs) {
        return (int) (lhs / rhs);
    }

    public Long add(Long lhs, Long rhs) {
        return (long) (lhs + rhs);
    }

    public Long subtract(Long lhs, Long rhs) {
        return (long) (lhs - rhs);
    }

    public Long multiply(Long lhs, Long rhs) {
        return (long) (lhs * rhs);
    }

    public Long divide(Long lhs, Long rhs) {
        return (long) (lhs / rhs);
    }

    public Float add(Float lhs, Float rhs) {
        return (float) (lhs + rhs);
    }

    public Float subtract(Float lhs, Float rhs) {
        return (float) (lhs - rhs);
    }

    public Float multiply(Float lhs, Float rhs) {
        return (float) (lhs * rhs);
    }

    public Float divide(Float lhs, Float rhs) {
        return (float) (lhs / rhs);
    }

    public Double add(Double lhs, Double rhs) {
        return (double) (lhs + rhs);
    }

    public Double subtract(Double lhs, Double rhs) {
        return (double) (lhs - rhs);
    }

    public Double multiply(Double lhs, Double rhs) {
        return (double) (lhs * rhs);
    }

    public Double divide(Double lhs, Double rhs) {
        return (double) (lhs / rhs);
    }

    public Complex add(Complex lhs, Complex rhs) {
        return lhs.add(rhs);
    }

    public Complex subtract(Complex lhs, Complex rhs) {
        return lhs.subtract(rhs);
    }

    public Complex multiply(Complex lhs, Complex rhs) {
        return lhs.multiply(rhs);
    }

    public Complex divide(Complex lhs, Complex rhs) {
        return lhs.divide(rhs);
    }

    public BigInteger add(BigInteger lhs, BigInteger rhs) {
        return lhs.add(rhs);
    }

    public BigInteger subtract(BigInteger lhs, BigInteger rhs) {
        return lhs.subtract(rhs);
    }

    public BigInteger multiply(BigInteger lhs, BigInteger rhs) {
        return lhs.multiply(rhs);
    }

    public BigInteger divide(BigInteger lhs, BigInteger rhs) {
        return lhs.divide(rhs);
    }

    public BigDecimal add(BigDecimal lhs, BigDecimal rhs) {
        return lhs.add(rhs);
    }

    public BigDecimal subtract(BigDecimal lhs, BigDecimal rhs) {
        return lhs.subtract(rhs);
    }

    public BigDecimal multiply(BigDecimal lhs, BigDecimal rhs) {
        return lhs.multiply(rhs);
    }

    public BigDecimal divide(BigDecimal lhs, BigDecimal rhs) {
        return lhs.divide(rhs);
    }

    public Byte add(Byte lhs, double rhs) {
        return (byte) (lhs + rhs);
    }

    public Byte subtract(Byte lhs, double rhs) {
        return (byte) (lhs - rhs);
    }

    public Byte multiply(Byte lhs, double rhs) {
        return (byte) (lhs * rhs);
    }

    public Byte divide(Byte lhs, double rhs) {
        return (byte) (lhs / rhs);
    }

    public Short add(Short lhs, double rhs) {
        return (short) (lhs + rhs);
    }

    public Short subtract(Short lhs, double rhs) {
        return (short) (lhs - rhs);
    }

    public Short multiply(Short lhs, double rhs) {
        return (short) (lhs * rhs);
    }

    public Short divide(Short lhs, double rhs) {
        return (short) (lhs / rhs);
    }

    public Integer add(Integer lhs, double rhs) {
        return (int) (lhs + rhs);
    }

    public Integer subtract(Integer lhs, double rhs) {
        return (int) (lhs - rhs);
    }

    public Integer multiply(Integer lhs, double rhs) {
        return (int) (lhs * rhs);
    }

    public Integer divide(Integer lhs, double rhs) {
        return (int) (lhs / rhs);
    }

    public Long add(Long lhs, double rhs) {
        return (long) (lhs + rhs);
    }

    public Long subtract(Long lhs, double rhs) {
        return (long) (lhs - rhs);
    }

    public Long multiply(Long lhs, double rhs) {
        return (long) (lhs * rhs);
    }

    public Long divide(Long lhs, double rhs) {
        return (long) (lhs / rhs);
    }

    public Float add(Float lhs, double rhs) {
        return (float) (lhs + rhs);
    }

    public Float subtract(Float lhs, double rhs) {
        return (float) (lhs - rhs);
    }

    public Float multiply(Float lhs, double rhs) {
        return (float) (lhs * rhs);
    }

    public Float divide(Float lhs, double rhs) {
        return (float) (lhs / rhs);
    }

    public Double add(Double lhs, double rhs) {
        return (double) (lhs + rhs);
    }

    public Double subtract(Double lhs, double rhs) {
        return (double) (lhs - rhs);
    }

    public Double multiply(Double lhs, double rhs) {
        return (double) (lhs * rhs);
    }

    public Double divide(Double lhs, double rhs) {
        return (double) (lhs / rhs);
    }

    public Complex add(Complex lhs, double rhs) {
        return lhs.add(rhs);
    }

    public Complex subtract(Complex lhs, double rhs) {
        return lhs.subtract(rhs);
    }

    public Complex multiply(Complex lhs, double rhs) {
        return lhs.multiply(rhs);
    }

    public Complex divide(Complex lhs, double rhs) {
        return lhs.divide(rhs);
    }

    public BigInteger add(BigInteger lhs, double rhs) {
        return lhs.add(BigDecimal.valueOf(rhs).toBigInteger());
    }

    public BigInteger subtract(BigInteger lhs, double rhs) {
        return lhs.subtract(BigDecimal.valueOf(rhs).toBigInteger());
    }

    public BigInteger multiply(BigInteger lhs, double rhs) {
        return lhs.multiply(BigDecimal.valueOf(rhs).toBigInteger());
    }

    public BigInteger divide(BigInteger lhs, double rhs) {
        return lhs.divide(BigDecimal.valueOf(rhs).toBigInteger());
    }

    public BigDecimal add(BigDecimal lhs, double rhs) {
        return lhs.add(BigDecimal.valueOf(rhs));
    }

    public BigDecimal subtract(BigDecimal lhs, double rhs) {
        return lhs.subtract(BigDecimal.valueOf(rhs));
    }

    public BigDecimal multiply(BigDecimal lhs, double rhs) {
        return lhs.multiply(BigDecimal.valueOf(rhs));
    }

    public BigDecimal divide(BigDecimal lhs, double rhs) {
        return lhs.divide(BigDecimal.valueOf(rhs));
    }

}
