package controllers;


import java.util.*;

import models.Maquina;
    
public class MaquinaController {
    
        
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
    Stack<Maquina> pila = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.getSubred() > umbral) {
                    pila.push(m);
            }
        }
        return pila;
    }
    
    
        public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
            TreeSet<Maquina> conjunto = new TreeSet<>(new Comparator<Maquina>() {
                public int compare(Maquina m1, Maquina m2) {
                    int cmp = Integer.compare(m2.getSubred(), m1.getSubred());
                    if (cmp == 0) {
                        cmp = m1.getNombre().compareTo(m2.getNombre());
                    }
                    return cmp;
                }
            });
    
            conjunto.addAll(pila);
            return conjunto;
        }
    
        
        public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
            TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();
    
            for (Maquina m : maquinas) {
                int riesgo = m.getRiesgo();
                mapa.putIfAbsent(riesgo, new LinkedList<>());
                mapa.get(riesgo).add(m);
            }
    
            return mapa;
        }
    
        
        public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
            int mayorCantidad = -1;
            int mayorRiesgo = -1;
    
            for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
                int riesgo = entry.getKey();
                int cantidad = entry.getValue().size();
    
                if (cantidad > mayorCantidad || (cantidad == mayorCantidad && riesgo > mayorRiesgo)) {
                    mayorCantidad = cantidad;
                    mayorRiesgo = riesgo;
                }
            }
    
            Queue<Maquina> grupoSeleccionado = mapa.get(mayorRiesgo);
            Stack<Maquina> resultado = new Stack<>();
            for (Maquina m : grupoSeleccionado) {
                resultado.push(m);
            }
    
            return resultado;
        }
    }
    


    

