package runners.junitRunners;
import com.intuit.karate.Results;
import com.intuit.karate.Runner.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExecucaoGeralTestRunner {

    @Test
    public void runExecucaoGeralParalela() {
        Builder builder = new Builder();
        builder.path("classpath:features");
        Results result = builder.parallel(5);
        Assertions.assertEquals(0, result.getFailCount());

        //somente para informações de log no console
        System.out.println("Total feature => "+ result.getFeaturesTotal());
        System.out.println("Total scenarios => "+ result.getScenariosTotal());
        System.out.println("Total feature passed => "+ result.getFeaturesPassed());
        System.out.println("Total scenarios passed => "+ result.getScenariosPassed());
        System.out.println("Total feature failed => "+ result.getFeaturesFailed());
        System.out.println("Total scenarios failed => "+ result.getScenariosFailed());
    }
}
