package runners.karateRunners;

import com.intuit.karate.junit5.Karate;

public class PostUsuariosTestRunner {
    @Karate.Test
    public Karate runPostRequest()
    {
        return Karate.run("classpath:features/PostUsuariosFeature.feature");
    }
}
