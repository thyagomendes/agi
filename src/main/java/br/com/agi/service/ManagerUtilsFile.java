package br.com.agi.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ManagerUtilsFile implements ManagerUtilsInterfaceService {

    public List<String> readerFile(String file) {

        List<String> line = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.ISO_8859_1)) {
            stream.forEach(line::add);
        } catch (IOException e) {
            System.out.println("Não foi possível manipular o arquivo: " + e.getMessage());
        }

        return line;
    }

    public List<String> readerFolder(String folder){

        List<String> files = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
            paths
                .filter(Files::isRegularFile)
                .filter(f -> f.getFileName().toString().split(".dat").length > 0)
                .forEach(f -> {
                    files.add(f.getFileName().toString());
                });
        }catch (IOException e) {
            System.out.println("Não foi possível manipula o diretorio: " + e.getMessage());
        }

        return files;
    }

    @Override
    public void writerFile(String file, List<String> fill) {
        try {
            FileWriter fw = new FileWriter(file);
            for (String s : fill) {
                fw.write(s + "\n");
            }

            fw.close();
        }
        catch (IOException e) {
            System.out.println("Ocorreu um erro na escrita do arquivo ("+file+")");
            e.printStackTrace();
        }
    }
}