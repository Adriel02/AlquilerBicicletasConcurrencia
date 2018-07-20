import java.util.Queue;
import java.util.LinkedList;


public class Estacion{

    private int capacidad;
    private int identificador;
    private Queue<Bicicleta> estacion;



    public Estacion(int identificador,int capacidad){
        this.identificador=identificador;
        this.capacidad=capacidad;
        estacion= new LinkedList<Bicicleta>();

        for (int i = 0; i < capacidad; i++) {
            estacion.add(new Bicicleta(this.identificador*1000+i));
        }
    }

    public int getId(){
        return this.identificador;
    }

    public synchronized Bicicleta alquila(int idUsuario){
        System.out.println("EL usuario "+idUsuario+" quiere alquilar una bicicleta en la estacion "+this.identificador);
        Bicicleta bici= estacion.poll();

        if(bici==null){
            System.out.println("No hay bicicletas disponibles en la estacion "+this.identificador+" y el usuario "+idUsuario+" no puede alquilar");
            try{
                wait(10000);
            }catch(Exception e){}
        }
        if(bici==null){
            System.out.println("ABANDONO");
            return null;
        }

        System.out.println("El usuario "+idUsuario+ " alquila una bicicleta en la estacion "+this.identificador);
        notifyAll();
        return bici;
    }

    public synchronized void devuelve(Bicicleta bicicleta, int idUsuario){
        System.out.println("El usuario "+ idUsuario+" va a devolver una bicicleta en la estacion "+this.identificador);

        if(estacion.size()==capacidad){
            System.out.println("La estacion  "+this.identificador +" esta llena y por tanto el usuario "+idUsuario+" no puede devolver la bicicleta");
            try{
                wait();
            }catch(Exception e){}
        }

        System.out.println("El usuario "+idUsuario+" devuelve la bicicleta");
        estacion.add(bicicleta);
        notifyAll();
    }

}