package br.com.agi.domain;

public class Client {

    private String name;
    private Long cnpj;
    private String branch;

    public Client(String name, Long cnpj, String branch) {
        this.name = name;
        this.cnpj = cnpj;
        this.branch = branch;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getCnpj() { return cnpj; }
    public void setCnpj(Long cnpj) { this.cnpj = cnpj; }
    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", cnpj=" + cnpj +
                ", branch='" + branch + '\'' +
                '}';
    }
}
