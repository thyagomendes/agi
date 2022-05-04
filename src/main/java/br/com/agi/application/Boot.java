package br.com.agi.application;

import br.com.agi.usecase.ManagerUseCase;

import java.io.IOException;
import java.nio.file.*;

public class Boot {

    public static void main(String[] args) throws IOException {

        final String HOMEPATH = System.getenv("HOMEPATH");
        final String folderIn = HOMEPATH + "/data/in/";
        final String folderOut = HOMEPATH + "/data/out/";
        final ManagerUseCase managerUseCase = new ManagerUseCase();

        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path folderListener = Paths.get(folderIn);

        folderListener.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey wk = null;

        managerUseCase.managerProcess(folderIn, folderOut);

        while (true){
            try {
                wk = watcher.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (WatchEvent<?> event : wk.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.OVERFLOW) continue;

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                Path source = folderListener.resolve(fileName);
                System.out.printf("Arquivo criado: %s\n", source);
            }
            managerUseCase.managerProcess(folderIn, folderOut);
            if (!wk.reset()) break;
        }
    }
}