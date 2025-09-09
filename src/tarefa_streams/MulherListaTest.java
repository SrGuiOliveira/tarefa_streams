package tarefa_streams;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MulherListaTest {
    @Test
    public void deveConterApenasMulheresNaLista() {
        List<Pessoa> lista = Arrays.asList(
            new Pessoa("Ana", "f"),
            new Pessoa("Beatriz", "F"),
            new Pessoa("Carla", "feminino")
        );

        
        List<Pessoa> mulheres = Main.filtrarPorSexo(lista, "f");

        
        boolean todasMulheres = mulheres.stream()
            .allMatch(p -> Main.normalizarSexo(p.getSexo()).equals("f"));

        assertTrue(todasMulheres);
    }

}
