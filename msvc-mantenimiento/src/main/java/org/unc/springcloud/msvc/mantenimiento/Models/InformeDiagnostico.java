package org.unc.springcloud.msvc.mantenimiento.Models;

public class InformeDiagnostico {

    private long id;
    private String codigoItd;
    private String TecnicoResponsable;
    private String Conclusion;

    public InformeDiagnostico(long id, String codigoItd, String tecnicoResponsable, String conclusion) {
        this.id = id;
        this.codigoItd = codigoItd;
        TecnicoResponsable = tecnicoResponsable;
        Conclusion = conclusion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoItd() {
        return codigoItd;
    }

    public void setCodigoItd(String codigoItd) {
        this.codigoItd = codigoItd;
    }

    public String getTecnicoResponsable() {
        return TecnicoResponsable;
    }

    public void setTecnicoResponsable(String tecnicoResponsable) {
        TecnicoResponsable = tecnicoResponsable;
    }

    public String getConclusion() {
        return Conclusion;
    }

    public void setConclusion(String conclusion) {
        Conclusion = conclusion;
    }
}
