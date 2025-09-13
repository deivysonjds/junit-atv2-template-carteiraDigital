import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;

class SaldoInicial {
        @ParameterizedTest
        @ValueSource(doubles={10,0,20})
        void deveConfigurarSaldoInicialCorreto(double balance) {
            DigitalWallet digitalWallet = new DigitalWallet("Deivyson", balance);
            assertEquals(balance, digitalWallet.getBalance());
        }

        @ParameterizedTest
        @ValueSource(doubles={-10,-5,-20})
        void deveLancarExcecaoParaSaldoInicialNegativo(double balance) {
            assertThrows(IllegalArgumentException.class, ()->{
                DigitalWallet digitalWallet = new DigitalWallet("Deivyson", balance);
            });
        }
    }