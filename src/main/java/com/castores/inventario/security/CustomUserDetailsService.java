package com.castores.inventario.security;

import com.castores.inventario.entity.Usuario;
import com.castores.inventario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoAndEstatus(correo, 1)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        return new CustomUserDetails(
                usuario.getIdUsuario(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getRol().getNombre(),
                usuario.getEstatus()
        );
    }
}
