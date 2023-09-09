package Logica;

import java.util.Random;


public class TableroAux {
	private boolean[][] tablero;
	private Random random = new Random();
	/*Constructor*/
	public TableroAux(int tamanio)
	{	
		this.verificarTablero(tamanio);
		this.tablero = new boolean[tamanio][tamanio];
		this.llenarTablero();
	}

	//llena del tablero de booleans randoms
	private void llenarTablero()
	{
		for(int fila = 0; fila<tablero.length; fila++)
	    { 
	    	for(int columna = 0; columna<tablero[fila].length; columna++)
	    	{ 
				tablero[fila][columna] = this.random.nextBoolean();		
			}
		}
	}

	public void restartTablero(){
		this.llenarTablero();
	}
	/*
	 * metodo para mostrar una ayuda para finalizar el juego
	 * 
	public List<String> encontrarSecuenciaGanadora() {
        List<String> secuencia = new ArrayList<>();
        encontrarSecuenciaGanadoraDFS(0, 0, secuencia);
        return secuencia;
    }
    private void encontrarSecuenciaGanadoraDFS(int row, int col, List<String> secuencia) {
        // Caso base: si hemos explorado todas las luces
        if (row == tablero.length) {
            if (estaResuelto()) {
                return; // El juego está ganado
            }
        }

        // Realizar un clic en la luz actual
        cambiarEstado(row, col);
        secuencia.add("Clic en fila " + (row ) + ", columna " + (col));

        // Moverse a la siguiente luz
        int nextRow = row;
        int nextCol = col + 1;
        if (nextCol == tablero[row].length) {
            nextRow++;
            nextCol = 0;
        }

        // Continuar la búsqueda en profundidad
        if(col < tablero.length && row < tablero.length)
        	encontrarSecuenciaGanadoraDFS(row, col++, secuencia);

        // Deshacer el clic en la luz actual (backtracking)
        cambiarEstadoCelda(row, col);
        secuencia.remove(secuencia.size() - 1);

        // Continuar la búsqueda sin hacer clic en la luz actual
      	encontrarSecuenciaGanadoraDFS(row, col, secuencia);
    }
    
    */
	//verifica si el juego esta resuelto
	public boolean estaResuelto()
	{ 
		boolean ret = true;
		for(int fila = 0; fila < tablero.length; fila++)
	    { 
	    	for(int columna = 0; columna < tablero[fila].length; columna++)
	    	{
	    		ret = ret && tablero[fila][columna];
	    	}
	    }
	    return ret;
	}
	
	//cambiar el estado de la celda pasada por parametro(f, c) y sus vecinas
	public void cambiarEstado(int fila, int columna)
	{
		//verificamos que los datos sean correctos
		verificarFilaYcolumna(fila);
		verificarFilaYcolumna(columna);
		
		//modificamos el tablero
		cambiarEstadoColumna(columna);
		cambiarEstadoFila(fila);
		cambiarEstadoCelda(fila, columna);
//		cambiarCeldaArriba(fila, columna);
//		cambiarCeldaAbajo(fila, columna);
//		cambiarCeldaDerecha(fila, columna);
//		cambiarCeldaIzquierda(fila, columna);
	}
	private void cambiarEstadoFila(int fila) {
		for (int col = 0; col < tablero[0].length; col++) {
			tablero[fila][col] = !tablero[fila][col];
		} 
		
	}
	private void cambiarEstadoColumna(int col) {
		for (int fila = 0; fila < tablero.length; fila++) {
			tablero[fila][col] = !tablero[fila][col];
		}
	}
	
	//cambiar el estado de la celda pasada por parametro
	private void cambiarEstadoCelda(int fila, int columna)
	{
		tablero[fila][columna] = !tablero[fila][columna];
	}
	
//	private void cambiarCeldaArriba(int fila, int columna)
//	{	
//		if(fila > 0)
//			cambiarEstadoCelda(fila-1, columna);
//	}
//	
//	private void cambiarCeldaAbajo(int fila, int columna)
//	{
//		if(fila < tablero.length - 1)
//			cambiarEstadoCelda(fila+1, columna);
//	}
//	
//	private void cambiarCeldaDerecha(int fila, int columna)
//	{	
//		if(columna < tablero.length - 1)
//			cambiarEstadoCelda(fila, columna+1);
//	}
//	
//	private void cambiarCeldaIzquierda(int fila, int columna)
//	{	
//		if(columna > 0)
//			cambiarEstadoCelda(fila, columna-1);
//	}

	private void verificarFilaYcolumna(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El numero no puede ser negativo: " + i);
		
		if( i >= tablero.length )
			throw new IllegalArgumentException("El numero " + i + " deben estar entre 0 y " + (tablero.length -1));
	}
	
	private void verificarTablero(int tamanio)
	{
		if( !(tamanio >= 3) )
			throw new IllegalArgumentException("El tamanio del tablero debe ser >= 4 no, " + tamanio);
	}
	/*utilizo para determinar el color del tablero en la vista*/
	public boolean celdaEstaEncendida(int fila, int columna) {
		return this.tablero[fila][columna];
	}
	public boolean[][] getTablero()
	{
		return this.tablero;
	}
	
	@Override
	//imprime el tablero, '*' = luz prendida y '.' = luz apagada
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(int fila = 0; fila < tablero.length; fila++)
	    { 
	    	for(int columna = 0; columna < tablero[fila].length; columna++)
	    	{ 
	    		if(tablero[fila][columna] == true)
	    		{
	    			builder.append("*");
	    			builder.append(" ");
	    		}
	    		else
	    		{
	    			builder.append(".");
	    			builder.append(" ");
	    		}
	    	}
	    	builder.append("\n");
	    }
		builder.append("\n");
		return builder.toString();
	}

	public int tamanio() {
		// TODO Auto-generated method stub
		return this.tablero.length;
	}
	
}
