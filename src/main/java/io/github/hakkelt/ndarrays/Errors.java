package io.github.hakkelt.ndarrays;

/**
 * Enum of error messages. Outside of the package it should only be used within unit tests to check
 * if proper exceptions are thrown in case of faulty behaviour. 
 */
public class Errors {

    private Errors() {}
    
/**
 *    "Parameter '%s' must be between %d and %d, but %d is given!
 */
    public static final String PARAMETER_MUST_BE_BETWEEN =
        "Parameter '%s' must be between %d and %d, but %d is given!";
    /**
     * Number of indices (%d) doesn't match the number of dimensions (%d)!
     */
    public static final String DIMENSION_MISMATCH =
        "Number of indices (%d) doesn't match the number of dimensions (%d)!";
    /**
     * Bounds error: Attempt to access element of %s array at index %s!
     */
    public static final String CARTESIAN_BOUNDS_ERROR =
        "Bounds error: Attempt to access element of %s array at index %s!";
    /**
     * Bounds error: Attempt to access element of array with %d elements at linear index %d!
     */
    public static final String LINEAR_BOUNDS_ERROR =
        "Bounds error: Attempt to access element of array with %d elements at linear index %d!";
    /**
     * Bounds error: Iterator already reached the end of the array!
     */
    public static final String ITERATOR_OUT_OF_BOUNDS =
        "Bounds error: Iterator already reached the end of the array!";
    /**
     * Size of array (%s) given as parameter doesn't match the size of the array (%s) on the left side!
     */
    public static final String COMBINE_SIZE_MISMATCH =
        "Size of array (%s) given as parameter doesn't match the size of the array (%s) on the left side!";
    /**
     * All parameters of %s must be either NDArray, Integer, Float, Double or Complex, but %s is given!
     */
    public static final String COMBINE_TYPE_MISMATCH_WITH_COMPLEX =
        "All parameters of %s must be either NDArray, Integer, Float, Double or Complex, but %s is given!";
    /**
     * All parameters of %s must be either NDArray, Integer, Float or Double, but %s is given!
     */
    public static final String COMBINE_TYPE_MISMATCH =
        "All parameters of %s must be either NDArray, Integer, Float or Double, but %s is given!";
    /**
     * Invalid range expression: %s!
     */
    public static final String INVALID_RANGE_EXPRESSION =
        "Invalid range expression: %s!";
    /**
     * Cannot concatenate %s array to the current array of size %s along dimension %d!
     */
    public static final String CONCATENATION_SIZE_MISMATCH =
        "Cannot concatenate %s array to the current array of size %s along dimension %d!";
    /**
     * The input object must be a (multidimensional) primitive array!
     */
    public static final String NOT_FLOAT_ARRAY =
        "The input object must be a (multidimensional) primitive array!";
    /**
     * Both of the input objects must be (multidimensional) primitive array!
     */
    public static final String NOT_FLOAT_ARRAYS =
        "Both of the input objects must be (multidimensional) primitive array!";
    /**
     * Input arrays differ in size!
     */
    public static final String ARRAYS_DIFFER_IN_SIZE =
        "Input arrays differ in size!";
    /**
     * Cannot initialize new ComplexNDArray from NDArray of type %s!
     */
    public static final String COPY_FROM_UNSUPPORTED_TYPE =
        "Cannot initialize new NDArray from array of type %s!";
    /**
     * The size of the input (%d) is incompatible with the given dimensions: %s!
     */
    public static final String INCOMPATIBLE_SIZE =
        "The size of the input (%d) is incompatible with the dimensions of the NDArray (%s)!";
    /**
     * Cannot drop dimension %d because it is not singleton!
     */
    public static final String DROPDIMS_NOT_SINGLETON =
        "Cannot drop dimension %d because it is not singleton!";
    /**
     * p must be a positive real number!
     */
    public static final String NEGATIVE_NORM =
        "p must be a positive real number!";
    /**
     * Axis %d cannot be selected
     */
    public static final String CANNOT_SELECT_DIM_NEGATIVE =
        "Axis %d cannot be selected";
    /**
     * Axis %d cannot be selected because the array has only %d dimensions
     */
    public static final String CANNOT_SELECT_DIM_OVERFLOW =
        "Axis %d cannot be selected because the array has only %d dimensions";
    /**
     * Axis %d cannot be dropped
     */
    public static final String CANNOT_DROP_DIM_NEGATIVE =
        "Axis %d cannot be dropped";
    /**
     * Axis %d cannot be dropped because the array has only %d dimensions
     */
    public static final String CANNOT_DROP_DIM_OVERFLOW =
        "Axis %d cannot be dropped because the array has only %d dimensions";
    /**
     * Cannot collect type %s!
     */
    public static final String TYPE_MISMATCH =
        "Cannot collect type %s!";
    /**
     * A (multidimensional) primitive array is expected as input!
     */
    public static final String INPUT_MUST_BE_ARRAY =
        "A (multidimensional) primitive array is expected as input!";
    /**
     * The permutation vector (%s) doesn't fit the size of the array to be permutated (%s)!
     */
    public static final String PERMUTATOR_SIZE_MISMATCH =
        "The permutation vector (%s) doesn't fit the size of the array to be permutated (%s)!";
    /**
     * The permutation vector (%s) is not a valid permutation vector for the array to be permutated (%s)!
     */
    public static final String INVALID_PERMUTATOR =
        "The permutation vector (%s) is not a valid permutation vector for the array to be permutated (%s)!";
    /**
     * Illegal slicing expression: %s
     */
    public static final String ILLEGAL_SLICING_EXPRESSION =
        "Illegal slicing expression: %s";
    /**
     * Dimension mismatch: cannot slice %s array with the following slicing expression: %s
     */
    public static final String SLICE_DIMENSION_MISMATCH =
        "Dimension mismatch: cannot slice %s array with the following slicing expression: %s";
    /**
     * Bounds error: cannot slice %s array with the following slicing expression: %s
     */
    public static final String SLICE_OUT_OF_BOUNDS =
        "Bounds error: cannot slice %s array with the following slicing expression: %s";
    /**
     * Invalid range: [%d, %d)
     */
    public static final String INVALID_RANGE =
        "Invalid range: [%d, %d)";
    /**
     * Mask must have same shape as the array to be masked, but the array has shape of %s and the mask is of the shape %s!
     */
    public static final String MASK_DIMENSION_MISMATCH =
        "Mask must have same shape as the array to be masked, but the array has shape of %s and the mask is of the shape %s!";
    /**
     * Cannot reshape %s array to new shape %s: Number of elements doesn't match!
     */
    public static final String RESHAPE_LENGTH_MISMATCH = 
        "Cannot reshape %s array to new shape %s: Number of elements doesn't match!";
    /**
     * Cannot drop all dimensions!
     */
    public static final String ALL_DIMS_DROPPED = 
        "Cannot drop all dimensions!";
    
}
