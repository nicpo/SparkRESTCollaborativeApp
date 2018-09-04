package com.np;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

/**
 * Main class
 */
public class App
{
    public static void main( String[] args ) throws Exception
    {
        int c;
        String from=null; // file to process
        String to=null;   // target file
        String clazz=null;   // class

        if (args.length == 0) {
            System.out.println("USAGE: java -jar DataTool.jar <options>");
            System.out.println();
            System.out.println("Options:");
            System.out.println("   --from  <fileName>          : set source CSV local file name");
            System.out.println("   --to    <targetURL>         : set target Parquet file url");
            System.out.println("   --class <class>             : data file class");

            return;
        }

        LongOpt[] longopts = new LongOpt[3];
        longopts[0] = new LongOpt("from", LongOpt.REQUIRED_ARGUMENT, null, 'f');
        longopts[1] = new LongOpt("to", LongOpt.REQUIRED_ARGUMENT, null, 't');
        longopts[2] = new LongOpt("class", LongOpt.REQUIRED_ARGUMENT, null, 'c');

        Getopt g = new Getopt("DataTool", args, "f:t:c:", longopts);
        while ((c = g.getopt()) != -1)
            switch (c)
            {
                case 'f':
                    from = g.getOptarg();
                    break;
                case 't':
                    to = g.getOptarg();
                    break;
                case 'c':
                    clazz = g.getOptarg();
                    break;
            }

        if (from==null) {
            System.out.println("Source file name not specified");
            return;
        }

        if (to==null) {
            System.out.println("Target file name not specified");
            return;
        }

        if (clazz==null) {
            System.out.println("Class name not specified");
            return;
        }

        System.out.println("Converting CSV " + from + " to Parquet " + to + ", class = " + clazz);
        new Convert(from, to, Class.forName(clazz)).process();
    }

}
