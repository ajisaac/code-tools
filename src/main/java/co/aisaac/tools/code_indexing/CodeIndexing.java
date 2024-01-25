package co.aisaac.tools.code_indexing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CodeIndexing {
    /*
    A small web app to more easily search our code and index everything.
     */

    public static void main(String[] args) {
        CodeIndexing codeIndexing = new CodeIndexing();
        codeIndexing.listAllFiles();
    }

    private void listAllFiles() {
        String rootDirectory = "/Users/work/Code/backend";
        Path p = Path.of(rootDirectory);

        // need way to filter out certain directories, possibly using .gitignore

        try (Stream<Path> s = Files.walk(p)) {
            List<Path> ls = s.toList();
            System.out.println("Found " + ls.size() + " files.");

            int i = 0;
            for (Path path : ls) {
                i++;
                if (filter(path)) continue;
                System.out.println(i + "\t" + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Should we ignore this path in listing.
     */
    private boolean filter(Path p) {
        if (p.toString().contains("/target/")) return true;
        if (p.toString().contains("/.git/")) return true;
        if (p.toString().contains("/.idea")) return true;
        if (p.toString().contains("/.idea/")) return true;
        if (p.toString().contains("/src/test/")) return true;
        if (p.toString().contains("/sql/release")) return true;
        if (!p.toString().endsWith(".java")) return true;
        if (!Files.isRegularFile(p)) return true;
        return false;
    }

}

