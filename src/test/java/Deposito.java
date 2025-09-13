import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;


class Deposito {

        DigitalWallet digitalWallet;
        @BeforeEach
        void instanceClass(){
            digitalWallet = new DigitalWallet("Deivyson", 0);
        }

        @ParameterizedTest
        @ValueSource(doubles = {10.0, 0.01, 999.99})
        void deveDepositarValoresValidos(double amount) {
            double beforeValue = digitalWallet.getBalance();
            digitalWallet.deposit(amount);

            assertEquals(amount + beforeValue, digitalWallet.getBalance());
        }

        @ParameterizedTest
        @ValueSource(doubles = {-10.0, -0.01, -999.99})
        void deveLancarExcecaoParaDepositoInvalido(double amount) {
            assertThrows(IllegalArgumentException.class, ()->{
                digitalWallet.deposit(amount);
            });
        }
    }