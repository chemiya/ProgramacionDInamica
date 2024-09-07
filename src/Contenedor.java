public class Contenedor {
    private int id;
    private int peso;
    private int carga_a_soportar;

    public Contenedor(int id, int peso, int carga_a_soportar){
        this.id = id;
        this.peso = peso;
        this.carga_a_soportar = carga_a_soportar;
    }

    /**
     * @return int return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return int return the carga_a_soportar
     */
    public int getCarga_a_soportar() {
        return carga_a_soportar;
    }

    /**
     * @param carga_a_soportar the carga_a_soportar to set
     */
    public void setCarga_a_soportar(int carga_a_soportar) {
        this.carga_a_soportar = carga_a_soportar;
    }


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
