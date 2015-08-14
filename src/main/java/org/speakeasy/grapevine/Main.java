package org.speakeasy.grapevine;

import java.io.File;

/**
 *
 * @author speakeasy
 */
public class Main implements Runnable {

    private static Main grapevine;
    private static String[] args;
    private static final File cwd = new File(System.getProperty("user.dir"));
    private static File database;
    private static File proxyList;
    private static File fimport;
    private static boolean running, isPyImport, doImport, useProxies = false;

    private int tickCount;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.grapevine = new Main();
        Main.args = args;
        grapevine.run();
    }

    private void init() {
        running = true;
        handleArgs();
    }

    private void handleArgs() {
        if (args != null) {
            int i = args.length - 1;
            int j = 0;
            while (j <= i) {
                if (args[j].startsWith("-")) {
                    if (j < i && args[j + 1].startsWith("-")) {
                        processArg(args[j]);
                    } else if (j < i && !(args[j + 1].startsWith("-"))) {
                        processArg(args[j], args[j + 1]);
                        j++;
                    } else if (j == i && args[j].startsWith("-")) {
                        processArg(args[j]);
                    }
                }
                j++;
            }
        }
    }

    private void processArg(String arga) {
        processArg(arga, null);
    }

    private void processArg(String arga, String argb) {
        if (argb != null && argb != "") {
            switch (arga) {
                case "-d": {
                    setDatabase(argb);
                    break;
                }
                case "--database": {
                    setDatabase(argb);
                    break;
                }
                case "-l": {
                    importProxies(argb);
                    break;
                }
                case "-proxies": {
                    importProxies(argb);
                    break;
                }
                case "-f": {
                    importFile(argb);
                    break;
                }
                case "--import": {
                    importFile(argb);
                    break;
                }
                case "--pyimport": {
                    importPyFile(argb);
                    break;
                }
                case "-i": {
                    break;
                }
                case "--interactive": {
                    break;
                }
                default: {
                    printHelp();
                }
            }

        } else {
            switch (arga) {
                case "-h": {
                    printHelp();
                    break;
                }
                case "--help": {
                    printHelp();
                    break;
                }
                default: {
                    printHelp();
                }
            }
        }
    }

    private void printHelp() {
        System.out.println("GrapeVine making sure a little birdie told you since 2015\nby speakeasy");
        System.out.println("Usage: java -jar grapevine.jar [options]\n");
        System.out.println("Options:");
        System.out.println("  -d, --database     : The database to use.");
        System.out.println("  -l, --proxies      : List of working proxies to use.");
        System.out.println("  -f, --import       : File to import bot from as email:password list.");
        System.out.println("      --pyimport     : File to import bot from python twitter bot.");
        System.out.println("  -i, --interactive  : Start in interactive mode (not implimented yet.)");
        System.out.println("  -h,   --help       : Print this help.");
        System.out.println("\nDone.\n");
        running = false;
    }

    private void setDatabase(String dbase) {
        ;
    }

    public void importProxies(String file) {
        if (proxyList != null) {
            System.out.println("Please specify only one proxy file.\nExiting.");
            quit();
            return;
        }
        if (file == null) {
            System.out.println("Please specify a proxy file.\nExiting.");
            quit();
            return;
        }
        proxyList = new File(cwd.getAbsolutePath() + file);
        if (!proxyList.canRead()) {
            System.out.println("Cannot read file: " + file + "\nExiting.");
            quit();
        }
        useProxies = true;
    }

    public void importFile(String file) {
        if (fimport != null) {
            System.out.println("Please specify only one import file.\nExiting.");
            quit();
            return;
        }
        if (file == null) {
            System.out.println("Please specify an import file.\nExiting.");
            quit();
            return;
        }
        fimport = new File(cwd.getAbsolutePath() + file);
        if (!fimport.canRead()) {
            System.out.println("Cannot read file: " + file + "\nExiting.");
            quit();
        }
        doImport = true;
    }

    public void importPyFile(String file) {
        if (fimport != null) {
            System.out.println("Please specify only one import file.\nExiting.");
            quit();
            return;
        }
        if (file == null) {
            System.out.println("Please specify an import file.\nExiting.");
            quit();
            return;
        }
        fimport = new File(cwd.getAbsolutePath() + file);
        if (!fimport.canRead()) {
            System.out.println("Cannot read file: " + file + "\nExiting.");
            quit();
        }
        isPyImport = doImport = true;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 100;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();

        init();

        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldProcess = true;
            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;
                shouldProcess = true;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldProcess) {
                frames++;
                processBots();
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " bots processed");
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick() {
        tickCount++;
    }

    private void processBots() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void quit() {
        running = false;
    }
}
