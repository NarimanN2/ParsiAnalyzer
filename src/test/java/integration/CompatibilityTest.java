package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.fail;

public class CompatibilityTest {

    private final int ELASTICSEARCH_PORT = 9200;
    private final String ELASTICSEARCH_VERSION = "7.13.1";
    private final String ELASTICSEARCH_IMAGE = format("docker.elastic.co/elasticsearch/elasticsearch:%s", ELASTICSEARCH_VERSION);

    private ElasticsearchContainer container;

    @BeforeEach
    public void setup() {
        this.container = new ElasticsearchContainer(ELASTICSEARCH_IMAGE)
                .withExposedPorts(ELASTICSEARCH_PORT)
                .waitingFor(Wait.forLogMessage(".*Installed ParsiAnalyzer.*\\n", 1))
                .withCreateContainerCmdModifier(cmd ->
                        cmd.withCmd(
                                "bash",
                                "-c",
                                format("./bin/elasticsearch-plugin install https://www.dropbox.com/s/cr61dmnx95taivi/ParsiAnalyzer-%s.zip?dl=1", ELASTICSEARCH_VERSION)
                        )
                );
    }

    @AfterEach
    public void tearDown() {
        if (this.container != null)
            this.container.stop();
    }
    @Test
    public void shouldInstallPluginSuccessfully() {
        try {
            container.start();
        } catch (Exception e) {
            fail("Failed to start container", e);
        }
    }
}
