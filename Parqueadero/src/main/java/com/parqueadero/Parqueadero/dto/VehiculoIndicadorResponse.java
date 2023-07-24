package com.parqueadero.Parqueadero.dto;

public class VehiculoIndicadorResponse {
    private Long vehiculoId;
    private Long vecesRegistrado;

    // Constructor
    public VehiculoIndicadorResponse(Long vehiculoId, Long vecesRegistrado) {
        this.vehiculoId = vehiculoId;
        this.vecesRegistrado = vecesRegistrado;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Long getVecesRegistrado() {
        return vecesRegistrado;
    }

    public void setVecesRegistrado(Long vecesRegistrado) {
        this.vecesRegistrado = vecesRegistrado;
    }
}