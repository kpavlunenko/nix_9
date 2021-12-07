package ua.com.alevel;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.alevel.config.HibernateConfig;

@SpringBootTest(classes = { WebCrudApplicationForAngular.class, HibernateConfig.class })
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class WebCrudApplicationForAngularTests {

    @Test
    void contextLoads() {
    }

}
