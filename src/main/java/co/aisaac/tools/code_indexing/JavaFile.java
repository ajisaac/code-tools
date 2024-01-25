package co.aisaac.tools.code_indexing;

import lombok.Getter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

@Getter
public class JavaFile {
    private final Path path;
    private final String application;
    private final String className;
    private final ComponentType componentType;
    private final ArrayList<String> nameSections;

    public JavaFile(Path path) {
        this.path = path;
        this.application = path.toString().split("/")[5];
        this.className = path.getFileName().toString().split("\\.")[0];
        this.componentType = ComponentType.fromClassName(className);
        this.nameSections = getSections(className);

        verifySections(className, this.nameSections);

    }

    private void verifySections(String className, ArrayList<String> sections) {
        StringBuilder tmp = new StringBuilder();
        for (String s : sections) {
            tmp.append(s);
        }
        if(!tmp.toString().equals(className)) {
            System.out.println("ERROR: " + className + " " + sections);
        }
    }

    private ArrayList<String> getSections(String className) {
        ArrayList<String> sections = new ArrayList<>();
        String[] split = className.split("(?=\\p{Upper})");
        Collections.addAll(sections, split);
        return sections;
    }

    @Override
    public String toString() {
        return className;
    }
}
