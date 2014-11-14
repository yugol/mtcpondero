package pondero.data.evaluation.scoring;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicStatistics {

    private Integer                   validSize;
    private Integer                   missingSize;
    private Double                    min;
    private Double                    max;
    private Double                    amplitude;
    private Double                    mode;
    private Double                    median;
    private Double                    mean;
    private Double                    stdErrorMean;
    private Double                    variance;
    private Double                    stdDeviation;
    private Double                    skewness;
    private Double                    stdErrorSkewness;
    private Double                    kurtosis;
    private Double                    stdErrorKurtosis;
    private final Map<Double, Double> percentiles = new LinkedHashMap<>();

    public Double getAmplitude() {
        return amplitude;
    }

    public Double getKurtosis() {
        return kurtosis;
    }

    public Double getMax() {
        return max;
    }

    public Double getMean() {
        return mean;
    }

    public Double getMedian() {
        return median;
    }

    public Double getMin() {
        return min;
    }

    public Integer getMissingSize() {
        return missingSize;
    }

    public Double getMode() {
        return mode;
    }

    public Map<Double, Double> getPercentiles() {
        return percentiles;
    }

    public Integer getSize() {
        if (validSize != null) {
            if (missingSize == null) {
                return validSize;
            } else {
                return validSize + missingSize;
            }
        }
        return null;
    }

    public Double getSkewness() {
        return skewness;
    }

    public Double getStdDeviation() {
        return stdDeviation;
    }

    public Double getStdErrorKurtosis() {
        return stdErrorKurtosis;
    }

    public Double getStdErrorMean() {
        return stdErrorMean;
    }

    public Double getStdErrorSkewness() {
        return stdErrorSkewness;
    }

    public Integer getValidSize() {
        return validSize;
    }

    public Double getVariance() {
        return variance;
    }

    public void putPercentile(final Double percent, final Double value) {
        percentiles.put(percent, value);
    }

    public void setAmplitude(final Double amplitude) {
        this.amplitude = amplitude;
    }

    public void setKurtosis(final Double kurtosis) {
        this.kurtosis = kurtosis;
    }

    public void setMax(final Double max) {
        this.max = max;
    }

    public void setMean(final Double mean) {
        this.mean = mean;
    }

    public void setMedian(final Double median) {
        this.median = median;
    }

    public void setMin(final Double min) {
        this.min = min;
    }

    public void setMissingSize(final Integer missingSize) {
        this.missingSize = missingSize;
    }

    public void setMode(final Double mode) {
        this.mode = mode;
    }

    public void setSkewness(final Double skewness) {
        this.skewness = skewness;
    }

    public void setStdDeviation(final Double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public void setStdErrorKurtosis(final Double stdErrorKurtosis) {
        this.stdErrorKurtosis = stdErrorKurtosis;
    }

    public void setStdErrorMean(final Double stdErrorMean) {
        this.stdErrorMean = stdErrorMean;
    }

    public void setStdErrorSkewness(final Double stdErrorSkewness) {
        this.stdErrorSkewness = stdErrorSkewness;
    }

    public void setValidSize(final Integer validSize) {
        this.validSize = validSize;
    }

    public void setVariance(final Double variance) {
        this.variance = variance;
    }

}
