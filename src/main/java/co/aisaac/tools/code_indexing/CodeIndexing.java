package co.aisaac.tools.code_indexing;


public class CodeIndexing {

	public static void main(String[] args) {
		CodeBase codeBase = new CodeBase("/Users/aaron/Code/backend");
		codeBase.listAllJavaFiles();
	}

}

