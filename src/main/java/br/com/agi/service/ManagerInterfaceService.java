package br.com.agi.service;

import java.util.List;

public interface ManagerInterfaceService {

    public List<String> readerFileIn(String file);
    public List<String> readerFolderIn(String file);
    public void writersFileOut(String file, List<String> fill);
}
