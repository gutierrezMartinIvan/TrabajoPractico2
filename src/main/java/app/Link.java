package app;

import generadorMinimo.GrafoPrim;
import generadorMinimo.Kruskal;
import grafos.*;

import java.io.File;
import java.util.*;

public class Link {
    private GrafoVecinos grafo;
    private GrafoVecinos AGMKruskal;
    private GrafoLV grafoLV;
    private GrafoLV AGMPrimm;
    private Kruskal algoritmoKruskal;
    private GrafoPrim algoritmoPrimm;

    public void cargarGrafo(File nombreArchivoTxt){
        grafo = ManejoJSON.leerJSON(nombreArchivoTxt);
        this.grafoLV = new GrafoLV(grafo.tamano());
        this.algoritmoKruskal = new Kruskal(grafo);
        //--------------------------------------------------------------------------------------
        //Cargar grafo en la otra clase de GrafoLV
        ArrayList<HashMap<Integer, Integer>> aristas = grafo.obtenerAristas();
        for (int i = 0; i < aristas.size(); i++) {
            HashMap<Integer, Integer> verticeActualVecinos = aristas.get(i);
            Set<Map.Entry<Integer, Integer>> verticeActualVecinosIterable = verticeActualVecinos.entrySet();
            Iterator<Map.Entry<Integer, Integer>> vecinoConPesoActual = verticeActualVecinosIterable.iterator();
            while (vecinoConPesoActual.hasNext()){
                Map.Entry<Integer, Integer> vecinoConPeso = vecinoConPesoActual.next();
                grafoLV.agregarArista(i, vecinoConPeso.getKey(), vecinoConPeso.getValue());
            }
        }
        this.algoritmoPrimm = new GrafoPrim(grafoLV);
        //----------------------------------------------------------------------------------------
        ejecutarAlgoritmos();
    }

    public void agregarVetirce(int vertice, int vecino, int peso){

    }

    private void ejecutarAlgoritmos() {
        this.AGMKruskal = algoritmoKruskal.generarArbolMinimo();
        this.AGMPrimm = algoritmoPrimm.getGrafoPrim();
    }

    public String[][] ordenRecorridoAGMKruskal(){
        return this.algoritmoKruskal.toArray2D();
    }

    public String[][] ordenRecorridoAGMPrimm(){
        return this.algoritmoPrimm.toArray2D();
    }
}