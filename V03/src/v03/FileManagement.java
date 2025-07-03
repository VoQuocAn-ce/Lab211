/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v03;

import java.io.File;

/**
 * V03 - File program handling
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-18
 */
public class FileManagement {

    private FileHandler fh = new FileHandler();

    /**
     * Prompts the user to enter a file or directory path and then checks if the
     * entered path corresponds to an existing file or directory. The result of
     * the check (whether it's a file, directory, or doesn't exist) is handled
     * by the `fh.checkFileOrDirec` method, which presumably prints the outcome
     * or returns a string to be processed.
     */
    public void checkFile() {
        System.out.println("--------- Check Path ----------");
        String path = ValidInput.getPathForCheck("Enter Path:");
        fh.checkFileOrDirec(path);
    }

    /**
     * Prompts the user for a directory path and then lists all Java files
     * (`.java` extension) found within that directory. It also displays the
     * total number of such files found.
     */
    public void getFileJava() {
        System.out.println("------ Get file name with type java -----");
        String path = ValidInput.getPath("Enter Path:");
        String[] fileListJava = fh.filterFilesByExtension(path, ".java");
        System.out.println("Result " + fileListJava.length + " file!");

    }

    /**
     * Prompts the user for a minimum file size (in KB) and a directory path,
     * then displays the names of all files within that directory that are
     * larger than the specified size.
     */
    public void getFileWithSize() {
        System.out.println("----------- Get file with size greater than input -----------");
        int size = ValidInput.getSize("Enter Size(Integer):");
        String path = ValidInput.getPath("Enter Path:");
        File[] allFile = fh.getAllFile(path, size);
        // Iterates through each File object in the 'allFile' array.
        // 'e' represents the current File object in each iteration.
        for (File e : allFile) {
            System.out.println(e.getName());
        }
    }

    /**
     * Prompts the user for a file path, then reads the content of that file and
     * displays the total number of words found within it. This operation relies
     * on an external file handler (`fh`) to perform the actual word counting.
     */
    public void countWordInsideFile() {
        System.out.println("------ Read file an count characters ----");
        String path = ValidInput.getPath("Enter Path:");
        System.out.println("Total:" + fh.countWord(path));
    }

    /**
     * Prompts the user to enter content and a file path, then appends the
     * entered content to the specified file. This operation utilizes an
     * external file handler (`fh`) to perform the actual file writing.
     */
    public void addNewContent() {
        System.out.println("------ Write more content to file -----");
        String content = ValidInput.getValidContent("Enter Content:");
        String path = ValidInput.getPath("Enter Path:");
        fh.addContentIntoFile(path, content);
    }

}
