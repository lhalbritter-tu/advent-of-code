package AOC22.DaySevenChallenge.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory extends File {

  public static final Directory ROOT = new Directory("/");

  private Map<String, File> children;

  public Directory(String name) {
    super();
    this.setName(name);
    this.children = new HashMap<>();
  }

  public void add(File file) {
    this.children.put(file.getName(), file);
  }

  public void remove(File file) {
    this.children.remove(file);
  }

  public File tryNavigate(String name) {
    if (this.children.containsKey(name)) return this.children.get(name);
    if (name.equals("/")) return Directory.ROOT;
    if (name.equals("..")) return this.parent;
    return null;
  }

  public File navigateSequence(String path) {
    if (path.equals("/")) {
      return Directory.ROOT;
    }
    String[] dirs = path.split("/");
    if (dirs.length < 1) return null;
    Directory currentDir = this;

    for (String dir : dirs) {
      if (currentDir == null) return null;
      currentDir = (Directory) currentDir.tryNavigate(dir);
    }

    return currentDir;
  }

  @Override
  public long getSize() {
    long total = 0;

    for (File child : children.values()) {
      total += child.getSize();
    }

    this.setSize(total);
    return total;
  }

  @Override
  public String toString() {
    String result = "- " + this.getName() + "\n";
    for (File file : children.values()) {
      result += " " + file.toString() + "\n";
    }

    return result;
  }

  public void printChildren() {
    for (File file : children.values()) {
      System.out.println(file);
    }
  }

  public List<Directory> directories() {
    List<Directory> directories = new ArrayList<>();
    for (File dir : children.values()) {
      if (dir.getClass() == Directory.class) {
        directories.add((Directory) dir);
        directories.addAll(((Directory) dir).directories());
      }
    }

    return directories;
  }
}
