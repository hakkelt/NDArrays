/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\FileOperationsTemplate.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Utility class for operations where the NDArray is filled with values read from a file.
 */
public class FileOperations<T, T2 extends Number> {

    public static final String IDENTIFIER_STRING = "NDArray";
    protected static final byte[] IDENTIFIER_BYTES = IDENTIFIER_STRING.getBytes(StandardCharsets.US_ASCII);

    public void writeToFile(File file, AbstractNDArray<T,T2> array) throws IOException {
        try(OutputStream stream = new FileOutputStream(file)) {
            switch (array.dataTypeAsString()) {
                case "Byte": writeByteContentToFile(stream, array); break;
                case "Short": writeShortContentToFile(stream, array); break;
                case "Integer": writeIntegerContentToFile(stream, array); break;
                case "Long": writeLongContentToFile(stream, array); break;
                case "Float": writeFloatContentToFile(stream, array); break;
                case "Double": writeDoubleContentToFile(stream, array); break;
                case "Complex Float": writeComplexFloatContentToFile(stream, array); break;
                case "Complex Double": writeComplexDoubleContentToFile(stream, array); break;
                default: throw new IllegalStateException();
            }
        }
    }

    protected void writeFileHeader(ByteBuffer buffer, AbstractNDArray<T,T2> array) {
        buffer.put(IDENTIFIER_BYTES);
        buffer.put(DataTypeEnum.getIndex(array.dataTypeAsString()));
        buffer.put((byte) array.ndim());
        for (int i = 0; i < array.ndim(); i++)
            buffer.putInt(array.shape(i));
    }

    protected int calculateBufferSize(AbstractNDArray<T,T2> array, int itemSizeInBytes) {
        return IDENTIFIER_BYTES.length
            + 1 /* byte encoding the dataType */
            + 1 /* byte storing the number of dimensions */
            + array.ndim() * Integer.BYTES
            + array.length() * itemSizeInBytes
            + 1 /* EOF character */;
    }

    protected void writeByteContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Byte.BYTES));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++)
            buffer.put((Byte) array.getUnchecked(i));
        stream.write(buffer.array());
    }

    protected void writeShortContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Short.BYTES));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++)
            buffer.putShort((Short) array.getUnchecked(i));
        stream.write(buffer.array());
    }

    protected void writeIntegerContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Integer.BYTES));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++)
            buffer.putInt((Integer) array.getUnchecked(i));
        stream.write(buffer.array());
    }

    protected void writeLongContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Long.BYTES));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++)
            buffer.putLong((Long) array.getUnchecked(i));
        stream.write(buffer.array());
    }

    protected void writeFloatContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Float.BYTES));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++)
            buffer.putFloat((Float) array.getUnchecked(i));
        stream.write(buffer.array());
    }

    protected void writeDoubleContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Double.BYTES));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++)
            buffer.putDouble((Double) array.getUnchecked(i));
        stream.write(buffer.array());
    }

    protected void writeComplexFloatContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Float.BYTES * 2));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++) {
            buffer.putFloat((Float) array.getRealUnchecked(i));
            buffer.putFloat((Float) array.getImagUnchecked(i));
        }
        stream.write(buffer.array());
    }

    protected void writeComplexDoubleContentToFile(OutputStream stream, AbstractNDArray<T,T2> array) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(calculateBufferSize(array, Double.BYTES * 2));
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        writeFileHeader(buffer, array);
        for (int i = 0; i < array.length(); i++) {
            buffer.putDouble((Double) array.getRealUnchecked(i));
            buffer.putDouble((Double) array.getImagUnchecked(i));
        }
        stream.write(buffer.array());
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(File file, Function<int[],A> constructor) throws IOException {
        try (InputStream stream = new FileInputStream(file)) {
            ByteBuffer buffer = ByteBuffer.wrap(stream.readAllBytes());
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            byte[] identifier = new byte[IDENTIFIER_BYTES.length];
            buffer.get(identifier);
            if (!Arrays.equals(IDENTIFIER_BYTES, identifier))
                throw new IllegalArgumentException(Errors.READ_FROM_FILE_WRONG_FILE_IDENTIFIER);
            String dtype = DataTypeEnum.getStringRepresentation(buffer.get());
            byte ndim = buffer.get();
            int[] shape = IntStream.range(0, ndim).map(i -> buffer.getInt()).toArray();
            A array = constructor.apply(shape);
            switch (dtype) {
                case "Byte": return readFromFile(buffer.limit(buffer.limit() - 1), array);
                case "Short": return readFromFile(buffer.asShortBuffer(), array);
                case "Integer": return readFromFile(buffer.asIntBuffer(), array);
                case "Long": return readFromFile(buffer.asLongBuffer(), array);
                case "Float": return readFromFile(buffer.asFloatBuffer(), array);
                case "Double": return readFromFile(buffer.asDoubleBuffer(), array);
                case "Complex Float": return readComplexFromFile(buffer.asFloatBuffer(), array);
                case "Complex Double": return readComplexFromFile(buffer.asDoubleBuffer(), array);
                default: throw new IllegalStateException();
            }
        }
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(ByteBuffer buffer, A array) {
        if (buffer.remaining() != array.length())
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++)
            array.setUnchecked(array.wrapValue(buffer.get()), i);
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(ShortBuffer buffer, A array) {
        if (buffer.remaining() != array.length())
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++)
            array.setUnchecked(array.wrapValue(buffer.get()), i);
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(IntBuffer buffer, A array) {
        if (buffer.remaining() != array.length())
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++)
            array.setUnchecked(array.wrapValue(buffer.get()), i);
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(LongBuffer buffer, A array) {
        if (buffer.remaining() != array.length())
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++)
            array.setUnchecked(array.wrapValue(buffer.get()), i);
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(FloatBuffer buffer, A array) {
        if (buffer.remaining() != array.length())
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++)
            array.setUnchecked(array.wrapValue(buffer.get()), i);
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readFromFile(DoubleBuffer buffer, A array) {
        if (buffer.remaining() != array.length())
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++)
            array.setUnchecked(array.wrapValue(buffer.get()), i);
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readComplexFromFile(FloatBuffer buffer, A array) {
        if (buffer.remaining() != array.length() * 2)
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++) {
            array.setRealUnchecked(array.wrapRealValue(buffer.get()), i);
            array.setImagUnchecked(array.wrapRealValue(buffer.get()), i);
        }
        return array;
    }

    public <A extends AbstractNDArray<T,T2>> A readComplexFromFile(DoubleBuffer buffer, A array) {
        if (buffer.remaining() != array.length() * 2)
            throw new IllegalStateException();
        for (int i = 0; i < array.length(); i++) {
            array.setRealUnchecked(array.wrapRealValue(buffer.get()), i);
            array.setImagUnchecked(array.wrapRealValue(buffer.get()), i);
        }
        return array;
    }

}
