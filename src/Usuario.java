public class Usuario extends Thread{

    private int identificador;
    private Estacion salida;
    private Estacion llegada;


    public Usuario(int identificador,Estacion salida, Estacion llegada){
        this.identificador=identificador;
        this.llegada=llegada;
        this.salida=salida;
    }

    @Override
    public void run(){
        java.util.Random rnd=new java.util.Random();
        Bicicleta bicicleta = salida.alquila(this.identificador);
        if(bicicleta!=null){
            try{
                Thread.sleep(4000+rnd.nextInt(4000));
            }catch(Exception e){}
            llegada.devuelve(bicicleta,this.identificador);
        }
    }
}
