package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "TestBase")
public class TestBaseTemplate {

    @Patterns({"Byte", "assertEquals(lhs, rhs)"})
    @Replacements({"Short", "assertEquals(lhs, rhs)"})
    @Replacements({"Integer", "assertEquals(lhs, rhs)"})
    @Replacements({"Long", "assertEquals(lhs, rhs)"})
    @Replacements({"Float", "assertEquals(lhs, rhs)"})
    @Replacements({"Double", "assertEquals(lhs, rhs)"})
    @Replacements({"BigInteger", "assertEquals(lhs, rhs)"})
    @Replacements({"BigDecimal", "assertEquals(0, lhs.compareTo(rhs))"})
    @Replacements({"Complex", "assertEquals(lhs, rhs)"})
    public void assertSpecializedEquals(Byte lhs, Byte rhs) {
        assertEquals(lhs, rhs);
    }

    @Patterns({"Byte", "assertEquals(lhs, rhs, delta)"})
    @Replacements({"Short", "assertEquals(lhs, rhs, delta)"})
    @Replacements({"Integer", "assertEquals(lhs, rhs, delta)"})
    @Replacements({"Long", "assertEquals(lhs, rhs, delta)"})
    @Replacements({"Float", "assertEquals(lhs, rhs, delta)"})
    @Replacements({"Double", "assertEquals(lhs, rhs, delta)"})
    @Replacements({"BigInteger", "assertEquals(lhs, rhs)"})
    @Replacements({"BigDecimal", "assertEquals(0, lhs.compareTo(rhs), delta)"})
    @Replacements({"Complex", "assertEquals(1, lhs.equals(Complex.ZERO) && rhs.equals(Complex.ZERO) ? 1 : lhs.abs() / rhs.abs(), delta)"})
    public void assertSpecializedEquals(Byte lhs, Byte rhs, double delta) {
        assertEquals(lhs, rhs, delta);
    }

    @Patterns({"/Byte/", "double", "(byte) value"})
    @Replacements({"Short", "double", "(short) value"})
    @Replacements({"Integer", "double", "(int) value"})
    @Replacements({"Long", "double", "(long) value"})
    @Replacements({"Float", "double", "(float) value"})
    @Replacements({"Double", "double", "value"})
    @Replacements({"BigInteger", "double", "BigInteger.valueOf((long) value)"})
    @Replacements({"BigDecimal", "double", "new BigDecimal(value)"})
    @Replacements({"Complex", "double", "new Complex(value)"})
    public static Byte wrapToByte(double value) {
        return (byte) value;
    }

    public static Double wrapToDouble(BigInteger value) {
        return value.doubleValue();
    }

    public static Double wrapToDouble(BigDecimal value) {
        return value.doubleValue();
    }

    @Patterns({"add", "Byte", "(byte) (lhs + rhs)"})
    @Replacements({"subtract", "Byte", "(byte) (lhs - rhs)"})
    @Replacements({"multiply", "Byte", "(byte) (lhs * rhs)"})
    @Replacements({"divide", "Byte", "(byte) (lhs / rhs)"})
    @Replacements({"add", "Short", "(short) (lhs + rhs)"})
    @Replacements({"subtract", "Short", "(short) (lhs - rhs)"})
    @Replacements({"multiply", "Short", "(short) (lhs * rhs)"})
    @Replacements({"divide", "Short", "(short) (lhs / rhs)"})
    @Replacements({"add", "Integer", "(int) (lhs + rhs)"})
    @Replacements({"subtract", "Integer", "(int) (lhs - rhs)"})
    @Replacements({"multiply", "Integer", "(int) (lhs * rhs)"})
    @Replacements({"divide", "Integer", "(int) (lhs / rhs)"})
    @Replacements({"add", "Long", "(long) (lhs + rhs)"})
    @Replacements({"subtract", "Long", "(long) (lhs - rhs)"})
    @Replacements({"multiply", "Long", "(long) (lhs * rhs)"})
    @Replacements({"divide", "Long", "(long) (lhs / rhs)"})
    @Replacements({"add", "Float", "(float) (lhs + rhs)"})
    @Replacements({"subtract", "Float", "(float) (lhs - rhs)"})
    @Replacements({"multiply", "Float", "(float) (lhs * rhs)"})
    @Replacements({"divide", "Float", "(float) (lhs / rhs)"})
    @Replacements({"add", "Double", "(double) (lhs + rhs)"})
    @Replacements({"subtract", "Double", "(double) (lhs - rhs)"})
    @Replacements({"multiply", "Double", "(double) (lhs * rhs)"})
    @Replacements({"divide", "Double", "(double) (lhs / rhs)"})
    public Byte add(Byte lhs, Byte rhs) {
        return (byte) (lhs + rhs);
    }

    @Replace(pattern = "add", replacements = {"subtract", "multiply", "divide"})
    public Complex add(Complex lhs, Complex rhs) {
        return lhs.add(rhs);
    }

    @Replace(pattern = "add", replacements = {"subtract", "multiply", "divide"})
    public BigInteger add(BigInteger lhs, BigInteger rhs) {
        return lhs.add(rhs);
    }

    @Replace(pattern = "add", replacements = {"subtract", "multiply", "divide"})
    public BigDecimal add(BigDecimal lhs, BigDecimal rhs) {
        return lhs.add(rhs);
    }

    @Patterns({"add", "Byte", "(byte) (lhs + rhs)"})
    @Replacements({"subtract", "Byte", "(byte) (lhs - rhs)"})
    @Replacements({"multiply", "Byte", "(byte) (lhs * rhs)"})
    @Replacements({"divide", "Byte", "(byte) (lhs / rhs)"})
    @Replacements({"add", "Short", "(short) (lhs + rhs)"})
    @Replacements({"subtract", "Short", "(short) (lhs - rhs)"})
    @Replacements({"multiply", "Short", "(short) (lhs * rhs)"})
    @Replacements({"divide", "Short", "(short) (lhs / rhs)"})
    @Replacements({"add", "Integer", "(int) (lhs + rhs)"})
    @Replacements({"subtract", "Integer", "(int) (lhs - rhs)"})
    @Replacements({"multiply", "Integer", "(int) (lhs * rhs)"})
    @Replacements({"divide", "Integer", "(int) (lhs / rhs)"})
    @Replacements({"add", "Long", "(long) (lhs + rhs)"})
    @Replacements({"subtract", "Long", "(long) (lhs - rhs)"})
    @Replacements({"multiply", "Long", "(long) (lhs * rhs)"})
    @Replacements({"divide", "Long", "(long) (lhs / rhs)"})
    @Replacements({"add", "Float", "(float) (lhs + rhs)"})
    @Replacements({"subtract", "Float", "(float) (lhs - rhs)"})
    @Replacements({"multiply", "Float", "(float) (lhs * rhs)"})
    @Replacements({"divide", "Float", "(float) (lhs / rhs)"})
    @Replacements({"add", "Double", "(double) (lhs + rhs)"})
    @Replacements({"subtract", "Double", "(double) (lhs - rhs)"})
    @Replacements({"multiply", "Double", "(double) (lhs * rhs)"})
    @Replacements({"divide", "Double", "(double) (lhs / rhs)"})
    public Byte add(Byte lhs, double rhs) {
        return (byte) (lhs + rhs);
    }

    @Replace(pattern = "add", replacements = {"subtract", "multiply", "divide"})
    public Complex add(Complex lhs, double rhs) {
        return lhs.add(rhs);
    }

    @Replace(pattern = "add", replacements = {"subtract", "multiply", "divide"})
    public BigInteger add(BigInteger lhs, double rhs) {
        return lhs.add(BigDecimal.valueOf(rhs).toBigInteger());
    }

    @Replace(pattern = "add", replacements = {"subtract", "multiply", "divide"})
    public BigDecimal add(BigDecimal lhs, double rhs) {
        return lhs.add(BigDecimal.valueOf(rhs));
    }

}
