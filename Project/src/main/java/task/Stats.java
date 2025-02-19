package task;

import constants.DataType;

public class Stats {
    private final boolean shortStat, fullStat;

    private long intCount, realCount, stringCount, intSum;

    private int intMin, intMax;
    private double realMin, realMax, realSum;
    private int stringMinLength, stringMaxLength;

    public Stats(boolean shortStat, boolean fullStat) {
        this.shortStat = shortStat;
        this.fullStat = fullStat;

        this.intCount = this.realCount = this.stringCount = 0;

        this.intMin = Integer.MAX_VALUE;
        this.intMax = Integer.MIN_VALUE;
        this.intSum = 0;

        this.realMin = Double.MAX_VALUE;
        this.realMax = Double.MIN_VALUE;
        this.realSum = 0;

        this.stringMinLength = Integer.MAX_VALUE;
        this.stringMaxLength = 0;
    }

    public void update(DataType type, String value) {
        switch(type) {
            case INT:
                intCount++;
                if (fullStat) {
                    int nowIntValue = Integer.parseInt(value);
                    intSum += nowIntValue;
                    intMin = Math.min(intMin, nowIntValue);
                    intMax = Math.max(intMax, nowIntValue);
                }

                break;
            case REAL:
                realCount++;
                if (fullStat) {
                    double nowRealValue = Double.parseDouble(value);
                    realSum += nowRealValue;
                    realMin = Math.min(realMin, nowRealValue);
                    realMax = Math.max(realMax, nowRealValue);
                }

                break;
            case STRING:
                if (fullStat) {
                    stringMinLength = Math.min(stringMinLength, value.length());
                    stringMaxLength = Math.max(stringMaxLength, value.length());
                }

                stringCount++;
        }
    }

    public void printStat() {
        if (shortStat || fullStat) {
            System.out.println("Short Stat:");
            System.out.println("Integers: " + intCount);
            System.out.println("Floats: " + realCount);
            System.out.println("Strings: " + stringCount);
        }

        if (fullStat) {
            System.out.println(System.lineSeparator() + "Full Stat:");
            if (intCount > 0) {
                System.out.println("intMin: " + intMin);
                System.out.println("intMax: " + intMax);
                System.out.println("intSum: " + intSum);
                System.out.println("intMid: " + intSum / intCount);
            }

            if (realCount > 0) {
                System.out.println("realMin: " + realMin);
                System.out.println("realMax: " + realMax);
                System.out.println("realSum: " + realSum);
                System.out.println("realMid: " + realSum / realCount);
            }

            if (stringCount > 0) {
                System.out.println("stringMinLength: " + stringMinLength);
                System.out.println("stringMaxLength: " + stringMaxLength);
            }
        }
    }
}
