package Utility;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InputManager {
    private BufferedReader currentInputStream;

    private FileWriter currentFileWriter;

    private File currentFile;

    public static InputManager INSTANCE = new InputManager();

    public final static String DEFAULT_INPUT_PATH = new File("").getAbsolutePath();

    private HttpsURLConnection currentConnection;

    private String SECRET;

    public static final String AOC_DEFAULT_URL = "https://adventofcode.com/*/day/?/input";

    private InputManager() {
    }

    private void initFileReader(String inputPath) {
        this.currentFile = new File(inputPath);
        try {
            this.currentInputStream = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean initFileWriter(String filePath) {
        this.currentFile = new File(filePath);
        if (this.currentFile.exists()) return false; // File already exist
        try {
            this.currentFileWriter = new FileWriter(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String[] getFile(String inputPath) {
        this.initFileReader(inputPath);
        return getLines();
    }

    public List<List<String>> getFileSeperated(String inputPath) {
        this.initFileReader(inputPath);
        return getSeperatedLines();
    }

    public List<String> getFileUntil(String inputPath, String until) {
        this.initFileReader(inputPath);
        return this.getLinesUntil(until);
    }

    public List<String> getFileFrom(String inputPath, String from) {
        this.initFileReader(inputPath);
        return this.getLinesFrom(from);
    }

    public List<List<String>> getFile2D(String s) {
        this.initFileReader(s);
        return this.getLines2D();
    }


    private int connect(String url) {
        if (SECRET == null)
            SECRET = getFile(DEFAULT_INPUT_PATH + "/.SECRET")[0];
        URL https = null;
        try {
            https = new URL(url);
            this.currentConnection = (HttpsURLConnection) https.openConnection();
            this.currentConnection.setRequestMethod("GET");
            this.currentConnection.setRequestProperty("cookie", SECRET);

            if (this.currentConnection.getResponseCode() == 200) {
                this.currentInputStream = new BufferedReader(new InputStreamReader(this.currentConnection.getInputStream()));
            }
            return this.currentConnection.getResponseCode();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isOk(int status) {
        return status == 200;
    }

    public String writeWebToFile(String url, String filePath) {
        if (this.initFileWriter(filePath) && isOk(this.connect(url))) {
            try {
                String line;
                while ((line = this.currentInputStream.readLine()) != null) {
                    this.currentFileWriter.write(line);
                    this.currentFileWriter.write("\n");
                    this.currentFileWriter.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return filePath;
        }
        return "error";
    }

    public List<String> getWeb(String url) {
        if (isOk(this.connect(url))) {
            return List.of(getLines());
        }
        return null;
    }

    public List<List<String>> getWebSeperated(String url) {
        if (isOk(this.connect(url))) {
            return getSeperatedLines();
        }
        return null;
    }

    public List<String> getWebFrom(String url, String from) {
        if (isOk(this.connect(url))) {
            return getLinesFrom(from);
        }
        return null;
    }

    public List<String> getWebUntil(String url, String until) {
        if (isOk(this.connect(url))) {
            return getLinesUntil(until);
        }
        return null;
    }

    public List<List<String>> getWeb2D(String url) {
        if (isOk(this.connect(url))) {
            return getLines2D();
        }
        return null;
    }

    public List<String> getAdventOfCodeInput(int day, int year)
    {
        return this.getWeb(AOC_DEFAULT_URL.replace("?", "" + day).replace("*", "" + year));
    }

    public List<List<String>> getAdventOfCodeInputSeperated(int day, int year) {
        return this.getWebSeperated(AOC_DEFAULT_URL.replace("?", "" + day).replace("*", "" + year));
    }

    public List<String> getAdventOfCodeInputUntil(int day, String until, int year) {
        return this.getWebUntil(AOC_DEFAULT_URL.replace("?", "" + day).replace("*", "" + year), until);
    }

    public List<String> getAdventOfCodeInputFrom(int day, String from, int year) {
        return this.getWebFrom(AOC_DEFAULT_URL.replace("?", "" + day).replace("*", "" + year), from);
    }

    public List<List<String>> getAdventOfCodeInput2D(int day, int year) {
        return this.getWeb2D(AOC_DEFAULT_URL.replace("?", "" + day).replace("*", "" + year));
    }

    private String[] getLines() {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = this.currentInputStream.readLine()) != null) {
                lines.add(line);
            }
            String[] ret = new String[lines.size()];
            return lines.toArray(ret);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<List<String>> getLines2D() {
        List<List<String>> lines = new ArrayList<>();
        try {
            String line;
            while ((line = currentInputStream.readLine()) != null) {
                lines.add(new ArrayList<>());
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    lines.get(lines.size() - 1).add("" + c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private List<List<String>> getSeperatedLines() {
        List<List<String>> lines = new ArrayList<>();
        lines.add(new ArrayList<>());
        try {
            String line;
            while ((line = this.currentInputStream.readLine()) != null) {
                if (line.strip().equals("")) {
                    lines.add(new ArrayList<>());
                    continue;
                }
                lines.get(lines.size() - 1).add(line);
            }

            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getLinesUntil(String until) {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = this.currentInputStream.readLine()) != null) {
                if (line.equals(until)) return lines;
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private List<String> getLinesFrom(String from) {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            boolean reached = false;
            while ((line = this.currentInputStream.readLine()) != null) {
                if (line.equals(from)) {
                    reached = true;
                    continue;
                }
                if (!reached) continue;
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
