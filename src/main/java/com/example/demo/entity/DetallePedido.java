package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DetallePedido {
    public Long id;
    public int cantidad;
    public double subtotal;
}
