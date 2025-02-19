package task;

import exceptions.CommandLineException;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CommandLineParser {
    private ArrayList<String> inputFiles;

    private String path;
    private String prefix;

    private boolean pathNotEmpty;
    private boolean prefixNotEmpty;
    private boolean rewrite;
    private boolean shortStat;
    private boolean fullStat;

    public CommandLineParser(String[] args) throws CommandLineException {
        this.inputFiles = new ArrayList<>();
        this.path = this.prefix = "";
        this.pathNotEmpty = this.prefixNotEmpty = this.rewrite = this.shortStat = this.fullStat = false;

        for (int indx = 0; indx < args.length; indx++) {
            switch (args[indx]) {
                case "-o":
                    this.pathNotEmpty = true;
                    continue;
                case "-p":
                    this.prefixNotEmpty = true;
                    continue;
                case "-a":
                    this.rewrite = true;
                    continue;
                case "-s":
                    this.shortStat = true;
                    continue;
                case "-f":
                    this.fullStat = true;
                    continue;
            }

            if (indx > 0 && args[indx - 1].equals("-o")) {
                this.path = args[indx].substring(1) + "/";
            }

            else if (indx > 0 && args[indx - 1].equals("-p")) {
                this.prefix = args[indx];
            }

            else {
                inputFiles.add(args[indx]);
            }
        }

        if (pathNotEmpty && this.path.isEmpty()) {
            throw new CommandLineException("Error: there is no path to the output files after the -o flag.");
        }

        if (prefixNotEmpty && this.prefix.isEmpty()) {
            throw new CommandLineException("Error: there is no output file name prefix after the -p flag.");
        }
    }
}
