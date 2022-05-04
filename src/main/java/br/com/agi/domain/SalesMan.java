package br.com.agi.domain;

public class SalesMan {

    private String name;
    private Long cpf;
    private Float salary;

    public SalesMan(String name, Long cpf, Float salary) {
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getCpf() { return cpf; }
    public void setCpf(Long cpf) { this.cpf = cpf; }
    public Float getSalary() { return salary; }
    public void setSalary(Float salary) { this.salary = salary; }
}