package ua.hillel.hw.task9.calculator;

import ua.hillel.hw.task9.exceptions.ArrayDataException;
import ua.hillel.hw.task9.exceptions.ArraySizeException;

import java.util.Objects;

public class ArrayValueCalculator implements Calculator {

    @Override
    public int calculate(String[][] matrix) {
        Objects.requireNonNull(matrix, "Parameter [matrix] must not be null!");
        validateMatrixSize(matrix);
        try {
            int result = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    result += Integer.parseInt(matrix[i][j]);
                }
            }
            return result;
        } catch (NumberFormatException e) {
            throw new ArrayDataException("Incorrect type of value, only 'int' values are supported.", e);
        }
    }

    private void validateMatrixSize(final String[][] matrix) {
        if (matrix.length != 4 || hasIncorrectNumberOfValuesInRow(matrix)) {
            throw new ArraySizeException("Matrix should be of size 4x4. Please correct your input and try again.");
        }
    }

    private boolean hasIncorrectNumberOfValuesInRow(final String[][] matrix) {
        for (String[] row : matrix) {
            if (row.length != 4) {
                return true;
            }
        }
        return false;
    }
}
