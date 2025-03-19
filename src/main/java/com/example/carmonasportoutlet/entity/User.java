//import entity.Cliente;
//import entity.Favoritos;
//import enumerados.Rol;
//import jakarta.persistence.*;
//import lombok.*;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode
//@Entity
//@Builder
//@Table(name = "usuario", schema = "carmonasportoutlet")
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "username", nullable = false, length = 100)
//    private String username;
//
//    @Column(name = "password", nullable = false, length = 600)
//    private String password;
//
//    @Column(name = "isverified", nullable = false)
//    private Boolean isVerified;
//
//    @Column(name = "verificationtoken", nullable = false, length = 600)
//    private String verificationToken;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "rol", nullable = false)
//    private Rol rol;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Cliente> clientes;
//
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Favoritos> favoritos;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(rol.name()));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
