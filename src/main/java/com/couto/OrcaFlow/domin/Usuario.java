package com.couto.OrcaFlow.domin;

import com.couto.OrcaFlow.Enum.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String googleId;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    @OneToMany(mappedBy = "usuario")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "usuario")
    private List<Orcamento> orçamentos;

    private String picture;

    boolean onboardingCompleted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;
}
