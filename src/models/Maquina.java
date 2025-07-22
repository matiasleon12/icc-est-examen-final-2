package models;

import java.util.List;

import java.util.*;

public class Maquina {
    private String nombre;
    private String ip;
    private List<Integer> codigos;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        String[] partes = ip.split("\\.");
        return Integer.parseInt(partes[2]);
    }

    public int getRiesgo() {
        int sumaDiv5 = 0;
        for (int codigo : codigos) {
            if (codigo % 5 == 0) {
                sumaDiv5 += codigo;
            }
        }

        String sinEspacios = nombre.replace(" ", "");
        Set<Character> caracteresUnicos = new HashSet<>();
        for (char c : sinEspacios.toCharArray()) {
            caracteresUnicos.add(c);
        }

        return sumaDiv5 * caracteresUnicos.size();
    }

    @Override
    public String toString() {
        return nombre + " (" + ip + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Maquina)) return false;
        Maquina otra = (Maquina) o;
        return this.nombre.equals(otra.nombre) && this.getSubred() == otra.getSubred();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, getSubred());
    }
}
