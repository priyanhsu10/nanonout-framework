package nanonout.ioc.container;

public class Descriptor {
    private Class<? extends Object> source;
    private Class<? extends Object> implemetor;
    private Scope schope;

    public Class<?> getSource() {
        return source;
    }

    public Class<?> getImplemetor() {
        return implemetor;
    }

    public Scope getSchope() {
        return schope;
    }

    public Descriptor(Class<? extends Object> source, Class<? extends Object> implemetor) {
        this.source = source;
        this.implemetor = implemetor;
        this.schope = Scope.Singleton;
    }

    public Descriptor(Class<? extends Object> source, Class<? extends Object> implemetor, Scope schope) {
        this.source = source;
        this.implemetor = implemetor;
        this.schope = schope;
    }
}
