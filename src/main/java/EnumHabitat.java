// TODO: Reconsiderar todo esto.
public enum EnumHabitat {
    MEADOW("Meadow", "/meadowHabitat.png", "/habitat.png", MeadowHabitat.class);
    private final String nombre;
    private final String path;
    private final String labelPath;
    private final Class<?> tipo;
    EnumHabitat(String nombre, String path, String labelPath, Class<?> tipo) {
        this.nombre = nombre;
        this.path = path;
        this.labelPath = labelPath;
        this.tipo = tipo;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLabelPath() {
        return labelPath;
    }
    public Habitat newInstance() {
        Habitat habitat = null;
        try {
            habitat = (Habitat) tipo.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return habitat;
    }
}
