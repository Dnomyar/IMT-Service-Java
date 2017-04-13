package fr.damienraymond.servicejava.tp2.serverRegister.infrastructure.jaxrs;

public enum StatutRFC6585 {

    PRECONDITION_REQUIRED(428);

    private int code;

    StatutRFC6585(int n) {
        this.code = n;
    }

    public int getCodeStatut() {
        return this.code;
    }
}
