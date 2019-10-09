/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Jon Martinez - 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
    marca = queMarca;
    altura = 0;
    longitudZancada = 0;
    totalPasosLaborables = 0;
    totalPasosSabado = 0;
    totalPasosDomingo = 0;
    totalDistanciaSemana = 0;
    totalDistanciaFinSemana = 0;
    tiempo = 0;
    caminatasNoche = 0;
    sexo = MUJER;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
       return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public double configurar(double queAltura, char queSexo) {
        altura = queAltura / 100;
        sexo = queSexo;
        if(sexo == HOMBRE){
          longitudZancada = ZANCADA_HOMBRE * altura;
          return longitudZancada;
        }
        else{
            longitudZancada = ZANCADA_MUJER * altura;
            return longitudZancada;
        }
    }

     /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) {
        switch (dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: dia = 5;
            break;
            case 6: dia = 6;
            break;
            case 7: dia = 7;
            break;
        }
        if(dia < SABADO){
            totalPasosLaborables += pasos;
            if(horaInicio > 2100 && horaInicio < 2359 || horaFin > 2100 && horaFin < 2359){
                caminatasNoche ++;
            } 
        }
        else if(dia < DOMINGO){
            totalPasosSabado += pasos;
            if(horaInicio > 2100 && horaInicio < 2359|| horaFin > 2100 && horaFin < 2359){
                caminatasNoche ++;
            }
        }
        else{
            totalPasosDomingo += pasos;
            if(horaInicio > 2100 && horaInicio < 2359 || horaFin > 2100 && horaFin < 2359){
                caminatasNoche ++;
            }
        }
        tiempo += horaFin - horaInicio;
        totalDistanciaSemana += (totalPasosLaborables * longitudZancada) /1000;
        totalDistanciaFinSemana += ((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 1000;
    }
    
     /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
     System.out.println("configuracion del podometro" +
                        "\n*************************" +
                        "\nAltura:" + altura + "mtos" +
                        "\nSexo:" + sexo +
                        "\nLongitud zancada:" + longitudZancada + "mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
    System.out.println("Estadisticas" +
                       "\n***************************" +
                       "\nDistancia recorrida toda la semana:" + totalDistanciaSemana + "Km" +
                       "\nDistancia recorrida fin la semana:" + totalDistanciaFinSemana + "Km" +
                       "\n" +
                       "\nNºpasos dias laborables:" + totalPasosLaborables +
                       "\nNºpasos SABADO:" + totalPasosSabado +
                       "\nNºpasos DOMINGO:" + totalPasosDomingo +
                       "\n" +
                       "\nNºcaminatas realizadas a partir de las 21h.:" + caminatasNoche +
                       "\n" +
                       "\nTiempo total caminado en la semana:" + tiempo / 100 + "h." + tiempo % 100 + "min." +
                       "\nDia/s con mas pasos caminados:" + diaMayorNumeroPasos());
    }
    
    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if(totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo){
            return "LABORABLES";
        }else if(totalPasosSabado > totalPasosDomingo && totalPasosSabado > totalPasosLaborables){
                return "SABADO";
        }else{ 
                return "DOMINGO";
            }
        
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
    altura = 0;
    longitudZancada = 0;
    totalPasosLaborables = 0;
    totalPasosSabado = 0;
    totalPasosDomingo = 0;
    totalDistanciaSemana = 0;
    totalDistanciaFinSemana = 0;
    tiempo = 0;
    caminatasNoche = 0;
    sexo = MUJER;
     
        

    }

}
