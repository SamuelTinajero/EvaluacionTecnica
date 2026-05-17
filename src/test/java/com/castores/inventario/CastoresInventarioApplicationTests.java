package com.castores.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CastoresInventarioApplicationTests {
@Test
    void generarPassword() {
        //System.out.println("HASH: " + new BCryptPasswordEncoder().encode("almacen123"));
    }
}
