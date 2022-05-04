package br.com.agi.service;

import java.util.List;

public interface ManagerUtilsInterfaceService {

    public List<String> readerFile(String file);
    public List<String> readerFolder(String file);
    public void writerFile(String file, List<String> fill);
}
