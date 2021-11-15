/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestRange.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.Range;
import io.github.hakkelt.ndarrays.internal.Errors;

import org.junit.jupiter.api.Test;

class TestRange {

    @Test
    void testRangeToString() {
        assertEquals("2", new Range(2).toString());
        assertEquals("0:3", new Range(0, 3).toString());
        assertEquals(":3", new Range(0, 1, 3, true, true, false).toString());
        assertEquals("0:1:3", new Range(0, 1, 3).toString());
        assertEquals("0:2:3", new Range(0, 2, 3).toString());
        assertEquals("-1:-1:1", new Range(-1, -1, 1).toString());
    }

    @Test
    void testParsedRangeToString() {
        assertEquals("2", Range.parseExpressions(new int[]{5}, 2)[0].toString());
        assertEquals("0:3", Range.parseExpressions(new int[]{5}, "0:3")[0].toString());
        assertEquals(":3", Range.parseExpressions(new int[]{5}, ":3")[0].toString());
        assertEquals("0:1:3", Range.parseExpressions(new int[]{5}, "0:1:3")[0].toString());
        assertEquals("0:2:3", Range.parseExpressions(new int[]{5}, "0:2:3")[0].toString());
        assertEquals("-1:-1:1", Range.parseExpressions(new int[]{5}, "-1:-1:1")[0].toString());
    }

    @Test
    void testWrongRange() {
        NDArray<Byte> array = new BasicByteNDArray(2, 3, 4);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, "asdf", "1:2"));
        assertEquals(String.format(Errors.INVALID_RANGE_EXPRESSION, "asdf"), exception.getMessage());
        
        exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, false, "1:2"));
        assertEquals(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, "false"), exception.getMessage());
        
        exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, 1.5, "1:2"));
        assertEquals(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, "1.5"), exception.getMessage());
        
        exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, 1.5f, "1:2"));
        assertEquals(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, "1.5"), exception.getMessage());
    }

}
