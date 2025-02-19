package task;

import constants.DataType;

public class DataParser {
    public static DataType parse(String arg){
        if (arg.matches("^(0|-?[1-9]\\d*)$")) {
            return DataType.INT;
        }

        if (arg.matches("^(0|-?[1-9]\\d*(\\.\\d+)?([eE][-+]?\\d+)?)$")) {
            return DataType.REAL;
        }

        return DataType.STRING;
    }
}
