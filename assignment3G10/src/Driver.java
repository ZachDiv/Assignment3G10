import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        Login loginSystem = new Login("sstrange", "littlemonsterhealer");
        loginSystem.loginCheck();
    }
}
