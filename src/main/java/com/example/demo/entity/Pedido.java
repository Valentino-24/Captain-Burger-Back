package com.example.demo.entity;

import com.example.demo.entity.enums.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class Pedido {
    public Long id;
    public LocalTime fecha;
    public Estado estado;
    public double total;
}
