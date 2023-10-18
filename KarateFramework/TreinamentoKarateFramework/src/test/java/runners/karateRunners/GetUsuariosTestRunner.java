package runners.karateRunners;

import com.intuit.karate.junit5.Karate;

public class GetUsuariosTestRunner {

    @Karate.Test
    public Karate runGetRequest()
    {
        return Karate.run("classpath:features/GetUsuarios.feature");
    }

}
