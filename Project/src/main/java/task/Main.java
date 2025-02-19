package task;

import exceptions.CommandLineException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = null;

        try {
            parser = new CommandLineParser(args);
        } catch (CommandLineException e) {
            System.out.println(e.getMessage());
        }

        if (parser != null) {
            try {
                Filter.stringFilter(parser);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        else {
            System.out.println("Parser is null.");
        }
    }
}