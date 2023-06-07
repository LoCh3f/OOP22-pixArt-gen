package it.unibo.pixArt.model.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameLevels {

    SKULL("/games/Skull/Skull.json", "/games/Skull/Skull0.png"),
    SHREK("/games/Shrek/Shrek.json", "/games/Shrek/Shrek0.png");

    private String pathToFile;
    private String pathToImg;

    GameLevels(final String pathToFile, final String pathToImg) {
        this.pathToFile = pathToFile;
        this.pathToImg = pathToImg;
    }

    public String getPathToFile() throws IOException {
        return fileInput();
    }

    public String getPathToImg() {
        return this.pathToImg;
    }

    public static List<GameLevels> getAllLevels() {
        return Stream.of(values()).collect(Collectors.toList());
    }

    private String fileInput() throws IOException{
        InputStream inputStream = this.getClass().getResourceAsStream(this.pathToFile);
        File tempFile = File.createTempFile("temp", ".json");
        
        if(inputStream == null){
            System.out.println("inputstream null");
        }
        OutputStream outputStream = new FileOutputStream(tempFile);
        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();
        return tempFile.getAbsolutePath();
    }

    private void delete(String path) {
        new File(path).delete();

    }
}
