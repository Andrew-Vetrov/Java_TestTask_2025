package task;

import constants.DataType;

import java.io.*;
import java.util.ArrayList;

public class Filter {
    public static void stringFilter(CommandLineParser commandLine) throws IOException {
        ArrayList<String> inputFiles = commandLine.getInputFiles();

        String path = commandLine.getPath();
        String prefix = commandLine.getPrefix();

        boolean rewrite = commandLine.isRewrite();
        boolean shortStat = commandLine.isShortStat();
        boolean fullStat = commandLine.isFullStat();

        FileWriter intFile = null;
        FileWriter realFile = null;
        FileWriter stringFile = null;

        Stats stat = new Stats(shortStat, fullStat);

        for (String filePath : inputFiles) {
            try (BufferedReader readFile = new BufferedReader(new FileReader(filePath))) {
                String line;
                DataType type;

                while ((line = readFile.readLine()) != null) {
                    type = DataParser.parse(line);

                    switch (type) {
                        case INT:
                            if (intFile == null) {
                                try {
                                    intFile = new FileWriter(path + prefix + "integers.txt", rewrite);
                                } catch (IOException e) {
                                    stat.printStat();
                                    throw new IOException("Error: could not open the file \"" + path + prefix + "integers.txt" + "\" due to an exception: " + e.getMessage(), e);
                                }
                            }

                            try (FileWriter intWriter = new FileWriter(path + prefix + "integers.txt", true)) {
                                intWriter.write(line + System.lineSeparator());
                            }

                            break;

                        case REAL:
                            if (realFile == null) {
                                try {
                                    realFile = new FileWriter(path + prefix + "floats.txt", rewrite);
                                } catch (IOException e) {
                                    stat.printStat();
                                    throw new IOException("Error: could not open the file \"" + path + prefix + "floats.txt" + "\" due to an exception: " + e.getMessage(), e);
                                }
                            }

                            try (FileWriter realWriter = new FileWriter(path + prefix + "floats.txt", true)) {
                                realWriter.write(line + System.lineSeparator());
                            }

                            break;

                        case STRING:
                            if (stringFile == null) {
                                try {
                                    stringFile = new FileWriter(path + prefix + "strings.txt", rewrite);
                                } catch (IOException e) {
                                    stat.printStat();
                                    throw new IOException("Error: could not open the file \"" + path + prefix + "strings.txt" + "\" due to an exception: " + e.getMessage(), e);
                                }
                            }

                            try (FileWriter stringWriter = new FileWriter(path + prefix + "strings.txt", true)) {
                                stringWriter.write(line + System.lineSeparator());
                            }
                    }

                    stat.update(type, line);
                }
            } catch (IOException e) {
                System.out.println("Warning: file \"" + filePath + "\" was missed due to an exception: " + e.getMessage());
            }
        }

        stat.printStat();
    }
}
