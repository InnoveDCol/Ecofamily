package com.innovedcol.ecofamily.entities;

public class Transaction {

    private double monto;
    private String concepto;
    private String  tipoMovimiento;
    private Employee usuario;

    public Transaction(double monto, String concepto, String tipoMovimiento, Employee usuario) {
        this.monto = monto;
        this.concepto = concepto;
        this.tipoMovimiento = tipoMovimiento;
        this.usuario = usuario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Employee getUsuario() {
        return usuario;
    }

    public void setUsuario(Employee usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "monto=" + monto +
                ", concepto='" + concepto + '\'' +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", usuario='" + usuario.getNombre() + '\'' +
                '}';
    }
}
