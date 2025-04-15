package fi.arcada.p01;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Statistics {

    public static double calcMean(List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        int count = 0; // Keep track of valid numbers
        for (DataModel item : data) {
            try {
                double value = Double.parseDouble(item.getValue2()); // Parse value2 as double
                sum += value;
                count++;
            } catch (NumberFormatException e) {
                // Handle the case where the value is not a valid number
                // You might want to log an error or skip the value
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        return count > 0 ? sum / count : 0.0; // Calculate mean if there are valid numbers
    }

    public static double calcMin(List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            return 0.0; // Or Double.MAX_VALUE if you want to indicate "no minimum" differently
        }

        double min = Double.MAX_VALUE; // Initialize with the largest possible double value
        boolean foundValidNumber = false;

        for (DataModel item : data) {
            try {
                double value = Double.parseDouble(item.getValue2());
                if (value < min) {
                    min = value;
                    foundValidNumber = true;
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format (e.g., log, skip, or return a special value)
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        return foundValidNumber ? min : 0.0; // Return the minimum or 0 if no valid numbers were found
    }

    public static double calcMax(List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            return 0.0; // Or Double.MIN_VALUE if you want to indicate "no maximum" differently
        }

        double max = Double.MIN_VALUE; // Initialize with the smallest possible double value
        boolean foundValidNumber = false;

        for (DataModel item : data) {
            try {
                double value = Double.parseDouble(item.getValue2());
                if (value > max) {
                    max = value;
                    foundValidNumber = true;
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format (e.g., log, skip, or return a special value)
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        return foundValidNumber ? max : 0.0; // Return the maximum or 0 if no valid numbers were found
    }

    public static double calcAverage(List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        int count = 0; // Keep track of valid numbers
        for (DataModel item : data) {
            try {
                double value = Double.parseDouble(item.getValue2());
                sum += value;
                count++;
            } catch (NumberFormatException e) {
                // Handle the case where the value is not a valid number
                // You might want to log an error or skip the value
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        return count > 0 ? sum / count : 0.0; // Calculate average if there are valid numbers
    }

    public static double calcMedian(List<DataModel> data) {
        List<Double> values = new ArrayList<>();
        if (data == null || data.isEmpty()) {
            return 0.0;
        }

        for (DataModel item : data) {
            try {
                values.add(Double.parseDouble(item.getValue2()));
            } catch (NumberFormatException e) {
                // Handle invalid number format (e.g., log, skip, or return a special value)
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        if (values.isEmpty()) {
            return 0.0; // Return 0 if no valid numbers were found
        }

        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            // Even number of elements: median is the average of the two middle elements
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            // Odd number of elements: median is the middle element
            return values.get(size / 2);
        }
    }

    public static List<Double> calcMode(List<DataModel> data) {
        List<Double> values = new ArrayList<>();
        if (data == null || data.isEmpty()) {
            return new ArrayList<>(); // Return an empty list for null or empty input
        }

        for (DataModel item : data) {
            try {
                values.add(Double.parseDouble(item.getValue2()));
            } catch (NumberFormatException e) {
                // Handle invalid number format (e.g., log, skip, or return a special value)
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        if (values.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if no valid numbers were found
        }

        Map<Double, Integer> counts = new HashMap<>();
        for (double value : values) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }

        int maxCount = 0;
        for (int count : counts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        List<Double> modes = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }

        return modes; // Return a list of modes (can be more than one)
    }


    public static double calcStdev(List<DataModel> data) {
        List<Double> values = new ArrayList<>();
        if (data == null || data.size() < 2) {
            return 0.0; // Standard deviation requires at least 2 data points
        }

        for (DataModel item : data) {
            try {
                values.add(Double.parseDouble(item.getValue2()));
            } catch (NumberFormatException e) {
                // Handle invalid number format
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        if (values.size() < 2) {
            return 0.0; // Return 0 if less than 2 valid numbers are found
        }

        double average = calcAverageFromValues(values); // Helper method (see below)
        double sumOfSquares = 0;
        for (double value : values) {
            sumOfSquares += Math.pow(value - average, 2);
        }

        return Math.sqrt(sumOfSquares / (values.size() - 1)); // Sample standard deviation
    }

    // Helper method to calculate the average from a list of doubles
    private static double calcAverageFromValues(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    public static double calcLQ(List<DataModel> data) {
        List<Double> values = extractValues(data); // Use helper to extract and handle invalid numbers
        if (values.size() < 2) return 0.0; // Need at least 2 values to calculate quartiles

        Collections.sort(values);
        int lowerSize = values.size() / 2;
        List<Double> lowerHalf = values.subList(0, lowerSize);

        return calcMedianFromValues(lowerHalf); // Use helper for median of doubles
    }

    // Helper method to extract valid double values
    private static List<Double> extractValues(List<DataModel> data) {
        List<Double> values = new ArrayList<>();
        if (data != null) {
            for (DataModel item : data) {
                try {
                    values.add(Double.parseDouble(item.getValue2()));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format: " + item.getValue2());
                }
            }
        }
        return values;
    }

    // Helper method to calculate median from a list of doubles
    private static double calcMedianFromValues(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        Collections.sort(values); // Sort again, as subList is not guaranteed to be sorted
        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }

    public static double calcUQ(List<DataModel> data) {
        List<Double> values = extractValues(data);
        if (values.size() < 2) return 0.0;
        Collections.sort(values);
        int upperSize = values.size() / 2;
        List<Double> upperHalf = values.subList(values.size() % 2 == 0 ? upperSize : upperSize + 1, values.size());
        return calcMedianFromValues(upperHalf);
    }

    public static double calcIQR(List<DataModel> data) {
        return calcUQ(data) - calcLQ(data);
    }
}

