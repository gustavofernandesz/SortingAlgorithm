# Visualizador de Algoritmos de Sorting

Para fazer funcionar, mude o diretório na pasta Trace, no arquivo LoadFromFile no seguinte trecho para o path absoluto da pasta de arrays


    public static int[] loadArray(int arrayNumber) {
        //to be defined
        return load("C:\\User..." + arrayNumber + ".txt");
    }
