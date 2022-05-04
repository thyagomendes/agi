package br.com.agi.usecase;

import br.com.agi.domain.Client;
import br.com.agi.domain.Item;
import br.com.agi.domain.Sales;
import br.com.agi.domain.SalesMan;
import br.com.agi.service.ManagerInterfaceService;
import br.com.agi.service.ManagerUtilsFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ManagerUseCase implements ManagerInterfaceService {

    private ManagerUtilsFile managerUtilsFile =  new ManagerUtilsFile();

    public void managerProcess(String folderIn, String folderOut) {

        List<String> fill = new ArrayList<>();
        List<String> files = readerFolderIn(folderIn);

        for (String file : files) {
            fill = readerFileIn(folderIn + file);
            List<String> lineInfo = new ArrayList<>();
            List<SalesMan> salesManList = new ArrayList<>();
            List<Client> clientList = new ArrayList<>();
            List<Sales> salesList = new ArrayList<>();
            Long idMaxSales = 0L;
            String salesManName = "";

            for(String line : fill){
                if(line.contains("001ç")){
                    lineInfo = Arrays.asList(line.split("ç"));
                    salesManList.add(
                            new SalesMan(
                                    lineInfo.get(2),
                                    Long.parseLong(lineInfo.get(1)),
                                    Float.parseFloat(lineInfo.get(3))
                            )
                    );
                }
                if(line.contains("002ç")){
                    lineInfo = Arrays.asList(line.split("ç"));
                    clientList.add(
                            new Client(
                                    lineInfo.get(2),
                                    Long.parseLong(lineInfo.get(1)),
                                    lineInfo.get(3)
                            )
                    );
                }
                if(line.contains("003ç")){
                    lineInfo = Arrays.asList(line.split("ç"));
                    List<String> itemsInfo = Arrays.asList(
                            lineInfo.get(2).substring(1, lineInfo.get(2).length()-1).split(",")
                    );
                    List<Item> itemList = new ArrayList<>();
                    for(String item : itemsInfo){
                        List<String> itemInfo = Arrays.asList(item.split("-"));
                        itemList.add(
                                new Item(
                                        Long.parseLong(itemInfo.get(0)),
                                        Integer.parseInt(itemInfo.get(1)),
                                        Float.parseFloat(itemInfo.get(2))));
                    }

                    salesList.add(
                            new Sales(
                                    lineInfo.get(1),
                                    lineInfo.get(3),
                                    itemList));

                    idMaxSales = itemList.stream().max(Comparator.comparing(Item::getPrice)).get().getId();
                }
            }

            String fileOut = file.split(".dat")[0] + ".done.dat";

            Float value = Float.MAX_VALUE;
            Float valueSale = Float.MAX_VALUE;

            for (Sales sales : salesList) {
                valueSale = (float) sales.getItems().stream().mapToDouble(Item::getPrice).sum();
                if( valueSale < value ){
                    value = valueSale;
                    salesManName  = sales.getSalesManName();
                }
            }

            List<String> fillOut = new ArrayList<>();
            fillOut.add("Qntd de clientes: " + clientList.size());
            fillOut.add("Qntd de vendedor: " + salesManList.size());
            fillOut.add("Venda mais cara: " + idMaxSales);
            fillOut.add("Vendedor com menor desempenho: " + salesManName + ", total de vendas: " + value);

            writersFileOut(folderOut + fileOut, fillOut);
        }
    }

    @Override
    public List<String> readerFileIn(String file) {
        return managerUtilsFile.readerFile(file);
    }

    @Override
    public List<String> readerFolderIn(String file) {
        return managerUtilsFile.readerFolder(file);
    }

    @Override
    public void writersFileOut(String file, List<String> fill) {
        managerUtilsFile.writerFile(file, fill);
    }
}
