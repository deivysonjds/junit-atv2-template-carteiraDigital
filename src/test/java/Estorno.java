import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;



class Estorno {
    static Stream<Arguments> valoresEstorno() {
        return Stream.of(
            Arguments.of(100.0, 10.0, 110.0),
            Arguments.of(0.0,   5.0,   5.0),
            Arguments.of(50.0,  0.01, 50.01)
        );
    }

    @ParameterizedTest
    @MethodSource("valoresEstorno")
    void refundComCarteiraValida(double inicial, double valor, double saldoEsperado) {
        DigitalWallet digitalWallet = new DigitalWallet("Deivyson", inicial);
        digitalWallet.unlock();
        digitalWallet.verify();
        assumeTrue(digitalWallet.isLocked() == false & digitalWallet.isVerified() == true);
        digitalWallet.refund(valor);
        assertEquals(saldoEsperado, digitalWallet.getBalance());
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {-10,0,-0.1})
    void deveLancarExcecaoParaRefundInvalido(double valor) {
        DigitalWallet digitalWallet = new DigitalWallet("Deivyson", 100);
        digitalWallet.verify();
        digitalWallet.unlock();
        assumeTrue(digitalWallet.isLocked() == false & digitalWallet.isVerified() == true);
        assertThrows(IllegalArgumentException.class, ()->{
            digitalWallet.refund(valor);
        });
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet digitalWallet = new DigitalWallet("Deivyson", 100);

        assertThrows(IllegalStateException.class, ()->{
            digitalWallet.refund(10);
        });
    }
}
