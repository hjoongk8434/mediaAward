package ma.web.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

@Component
public class EnvConfig implements InitializingBean {

    @Resource(name="external")
    private Properties external;

    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    public void afterPropertiesSet() throws Exception {
        environment.getPropertySources().addLast(new PropertiesPropertySource("external", external));
    }
}
