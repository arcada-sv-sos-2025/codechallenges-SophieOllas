package fi.arcada.p01;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Statistics {

    public static double calcMean(List<DataModel> data) {
        //Returnerar 0.0 om det inte finns någon data, för att undvika error
        if (data == null || data.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        int count = 0;
        for (DataModel item : data) {
            try {
                //konverterar string till double
                double value = Double.parseDouble(item.getValue2());
                //räknar ihop alla värden i datalistan
                sum += value;
                //samt antalet värden
                count++;
            } catch (NumberFormatException e) {
                System.err.println("Invalid number: " + item.getValue2());
            }
        }

        //om värdet är större än 0 divideras sum med count
        return count > 0 ? sum / count : 0.0;
    }

    public static double calcMin(List<DataModel> data) {
        if (data == null || data.isEmpty()) {
            return 0.0;
        }

        //Initializera min som max value för att säkerställa att alla valid
        //värden är mindre än min
        double min = Double.MAX_VALUE;
        boolean foundValidNumber = false;

        //Loopa igenom värden tills den minsta hittas
        for (DataModel item : data) {
            try {
                double value = Double.parseDouble(item.getValue2());
                if (value < min) {
                    //min blir alltid ersätt med det mindre värdet tills den
                    //minsta hittas
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
            return 0.0;
        }

        //Samma princip som med min
        double max = Double.MIN_VALUE;
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
        int count = 0;
        for (DataModel item : data) {
            try {
                double value = Double.parseDouble(item.getValue2());
                //Värden summeras och antalet räknas ut
                sum += value;
                count++;
            } catch (NumberFormatException e) {
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
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        if (values.isEmpty()) {
            return 0.0;
        }

        //Sorterar listan av värden i ascending order
        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }

    public static List<Double> calcMode(List<DataModel> data) {
        List<Double> values = new ArrayList<>();
        if (data == null || data.isEmpty()) {
            return new ArrayList<>();
        }

        for (DataModel item : data) {
            try {
                values.add(Double.parseDouble(item.getValue2()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        if (values.isEmpty()) {
            return new ArrayList<>();
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

        return modes;
    }


    public static double calcStdev(List<DataModel> data) {
        List<Double> values = new ArrayList<>();
        if (data == null || data.size() < 2) {
            return 0.0;
        }

        for (DataModel item : data) {
            try {
                values.add(Double.parseDouble(item.getValue2()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + item.getValue2());
            }
        }

        if (values.size() < 2) {
            return 0.0;
        }

        double average = calcAverageFromValues(values);
        double sumOfSquares = 0;
        for (double value : values) {
            sumOfSquares += Math.pow(value - average, 2);
        }

        return Math.sqrt(sumOfSquares / (values.size() - 1));
    }

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
        List<Double> values = extractValues(data);
        if (values.size() < 2) return 0.0;

        Collections.sort(values);
        int lowerSize = values.size() / 2;
        List<Double> lowerHalf = values.subList(0, lowerSize);

        return calcMedianFromValues(lowerHalf);
    }

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

    private static double calcMedianFromValues(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        Collections.sort(values);
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

