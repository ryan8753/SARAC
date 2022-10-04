package com.sarac.data;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {
    public static void main(String[] args) {
        int exitCode = -1;
        ProgramDriver pgd = new ProgramDriver();
        try {
            pgd.addClass("wordcount", Wordcount.class, "Count words from Description");

            pgd.driver(args);
            exitCode = 0;
        }
        catch(Throwable e) {
            e.printStackTrace();
        }

        System.exit(exitCode);
    }
}
