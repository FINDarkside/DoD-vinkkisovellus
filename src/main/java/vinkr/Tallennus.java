package vinkr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tallennus {

    private final Path path;

    public Tallennus(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public String lataa() throws IOException {
        return Files.readString(this.path);
    }

    public void tallenna(String data) throws IOException {
        Files.writeString(path, data);
    }
}
