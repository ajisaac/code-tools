package co.aisaac.tools.code_indexing;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a directory full of Java code.
 */
@Getter
public class CodeBase {
	private final String path;

	public CodeBase(String path) {
		this.path = path;
	}

	public void listAllJavaFiles() {
		listAllFiles();
	}

	private void listAllFiles() {
		String root = "/Users/aaron/Code/backend";

		List<JavaFile> files = getCodeFiles(root);
		Objects.requireNonNull(files);
		HashMap<ComponentType, List<JavaFile>> mappedFiles = new HashMap<>();
		for (JavaFile file : files) {
			mappedFiles.computeIfAbsent(file.getComponentType(), k -> new ArrayList<>()).add(file);
		}

		mappedFiles.get(ComponentType.UNKNOWN).stream().sorted((x, y) -> {
			var xSects = x.getNameSections().reversed();
			var ySects = y.getNameSections().reversed();

			// sorting by name sections
			for (int i = 0; i < xSects.size(); i++) {
				if (i >= ySects.size()) return 1;
				var xSect = xSects.get(i);
				var ySect = ySects.get(i);
				if (xSect.equals(ySect)) continue;
				return xSect.compareTo(ySect);
			}
			return 0;

		}).forEach(x -> {
			System.out.println("\t" + x.getComponentType() + "\t" + x.getClassName());
		});

//        mappedFiles.forEach((k, v) -> {
//            System.out.println(k);
//            v.forEach(x -> System.out.println("\t" + x.getComponentType() + "\t" + x.getClassName()));
//        });

		// need way to filter out certain directories, possibly using .gitignore

	}

	private List<JavaFile> getCodeFiles(String root) {
		Path p = Path.of(root);
		try (Stream<Path> s = Files.walk(p)) {
			return s.filter(this::filter)
					.map(JavaFile::new)
					.collect(Collectors.toCollection(ArrayList::new));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void listFiles(List<Path> files) {
		int i = 0;
		for (Path p : files) {
			i++;
			System.out.println(i + "\t" + p.getFileName());
		}
	}

	/**
	 * Should we ignore this path in listing
	 */
	private boolean filter(Path p) {
		if (p.toString().contains("/target/")) return false;
		if (p.toString().contains("/.git/")) return false;
		if (p.toString().contains("/.idea")) return false;
		if (p.toString().contains("/.idea/")) return false;
		if (p.toString().contains("/src/test/")) return false;
		if (p.toString().contains("/sql/release")) return false;
		if (!Files.isRegularFile(p)) return false;
		if (!p.toString().endsWith(".java")) return false;
		return true;
	}
}
